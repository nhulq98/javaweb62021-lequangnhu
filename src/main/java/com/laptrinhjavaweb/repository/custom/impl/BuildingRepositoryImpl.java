package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingSearch;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.MyUserDetail;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository// it understands this is a module and tells the IOC container to create a single object (singleton)
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @Autowired
    private BuildingConverter buildingConverter;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findByCondition(Map<String, Object> requestParam) {
        BuildingSearch searchBuilder = buildingConverter.convertMapToBuider(requestParam);

        StringBuilder sql = this.buildQueryForBuildingSearch(searchBuilder);

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);

        return query.getResultList();
    }

    public StringBuilder buildFromSQLClause(BuildingSearch buildingSearch) {
        StringBuilder sql = new StringBuilder("SELECT BD.*")
                .append(" FROM building BD ");

        if (buildingSearch.getRentAreaFrom() != null || buildingSearch.getRentAreaTo() != null) {
            sql.append(" JOIN rentarea RE ON RE.buildingid = BD.id ");
        }

        return sql;
    }

    /**
     * buildQueryForSearchBuilding to concat all clauses to complete sql final
     *
     * @param buildingSearch the building from search form
     * @return sql String final
     */
    @Override
    public StringBuilder buildQueryForBuildingSearch(BuildingSearch buildingSearch) {
        StringBuilder sql = buildFromSQLClause(buildingSearch);
        authorization(sql, buildingSearch.getStaffId());
        this.buildWhereSQLClause(buildingSearch, sql);

        return sql;
    }

    /**
     * Add condition into Where clause if value != null
     *
     * @param buildingSearch
     * @return Where statement String
     */
    @Override
    public void buildWhereSQLClause(BuildingSearch buildingSearch, StringBuilder sql) {
        // specials cases
        buildBetweenStatement("RE.value", buildingSearch.getRentAreaFrom(), buildingSearch.getRentAreaTo(), sql);
        buildBetweenStatement("rentprice", buildingSearch.getRentPriceFrom(), buildingSearch.getRentPriceTo(), sql);
        buildConditionForBuildingType(buildingSearch, sql);

        // common cases
        Field[] fields = BuildingSearch.class.getDeclaredFields();
        for (Field item : fields) {
            item.setAccessible(true);
            String name = item.getName();
            if (!name.equals("buildingTypes")
                    && !name.startsWith("rentArea") && !name.startsWith("rentPrice")) {
                try {
                    Object objectValue = item.get(buildingSearch);// can throw IllegalAccessException
                    if (item.getType().getTypeName().contains("String")
                            && objectValue != null && String.valueOf(objectValue).length() != 0) {
                        sql.append(createConditionForStringByLike(name.toLowerCase(), String.valueOf(objectValue)));

                    } else if ((item.getType().getTypeName().contains("Integer")
                            || item.getType().getTypeName().contains("Long")) && objectValue != null) {
                        sql.append(createConditionForNumber(name.toLowerCase(), (Number) objectValue));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }
        sql.append(" GROUP BY BD.id ");
    }

    /**
     * // this code below handles authorization when users log in and use search
     * Has 3 Case:
     * case 1: Current user has role is staff ==> search by userID(current user). the mean show only the buildings it's managing
     * case 2: Current user has role is manager ==> search normal(not by userID(current user)).the mean can see full building
     * case 3: If current user has role is manager and it's searching by staffId ==> search by staffID
     *
     * @param sql
     * @param staffId
     */
    @Override
    public void authorization(StringBuilder sql, Long staffId) {
        MyUserDetail userDetails = SecurityUtils.getMyUserDetail();

        boolean temp = SecurityUtils.isRole(SystemConstant.ROLE_STAFF, userDetails);
        if (temp == true) {
            sql.append(" JOIN assignmentbuilding ASB on  ASB.buildingid = BD.id ");
            sql.append(" WHERE 1=1 ");
            sql.append(" AND ASB.staffid =" + userDetails.getId());
        } else if (staffId != null) {// User Logged has role: MANAGER and exists by "staffId" search condition
            sql.append(" JOIN assignmentbuilding ASB on  ASB.buildingid = BD.id ");
            sql.append(" WHERE 1=1 ");
        } else {
            sql.append(" WHERE 1=1 ");
        }
    }

    /**
     * Generic method to create condition clause with values have typed is String become like this.
     * Example: " AND name LIKE '%value%' "
     *
     * @param fieldName
     * @param value
     * @return
     */
    @Override
    public StringBuilder createConditionForStringByLike(String fieldName, String value) {
        if (StringUtils.isNotEmpty(value)) {
            return new StringBuilder(" AND ")
                    .append(fieldName)
                    .append(" LIKE '%")
                    .append(value)
                    .append("%' ");
        }
        return new StringBuilder();
    }

    /**
     * Generic method to create a condition with values have typed is Integer become like this.
     * Example: " AND age = 23 "
     *
     * @param fieldName
     * @param value
     * @return
     */
    @Override
    public StringBuilder createConditionForNumber(String fieldName, Number value) {
        if (value != null) {
            return new StringBuilder(" AND ")
                    .append(fieldName)
                    .append("=")
                    .append(value).append(" ");
        }
        return new StringBuilder();
    }

    @Override
    public void buildBetweenStatement(String fieldName, Integer from, Integer to, StringBuilder sql) {
        if (from != null || to != null) {
            if (from != null && to != null) {
                sql.append(" AND " + fieldName + " BETWEEN " + from + " AND " + to + " ");
            } else if (from != null && to == null) {
                sql.append(" AND " + fieldName + " >= " + from + " ");
            } else {
                sql.append(" AND " + fieldName + " <= " + to + " ");
            }
        }
    }

    /**
     * final result: AND RT.code = 'tang-tret' OR RT.code = 'nguyen-can'
     *
     * @param buildingSearch
     * @param sql
     */
    @Override
    public void buildConditionForBuildingType(BuildingSearch buildingSearch, StringBuilder sql) {
        List<String> types = buildingSearch.getRentTypes();
        if (types != null && types.size() > 0) {
            sql.append(" AND (");

            // java 8
            String typeStr = Arrays.stream(types.toArray())
                    .map(item -> "BD.type LIKE '%" + item + "%'")
                    .collect(Collectors.joining(" or "));
            sql.append(typeStr);

            sql.append(")");
        }
    }
}
