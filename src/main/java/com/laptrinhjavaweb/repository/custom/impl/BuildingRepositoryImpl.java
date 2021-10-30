package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingSearch;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.Utils;
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

    private StringBuilder buildFromSQLClause(BuildingSearch buildingSearch) {
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
    private StringBuilder buildQueryForBuildingSearch(BuildingSearch buildingSearch) {
        StringBuilder sql = buildFromSQLClause(buildingSearch);
        this.buildWhereSQLClause(buildingSearch, sql);
        return sql;
    }

    /**
     * Add condition into Where clause if value != null
     *
     * @param buildingSearch
     * @return Where statement String
     */
    private void buildWhereSQLClause(BuildingSearch buildingSearch, StringBuilder sql) {
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
                        sql.append(Utils.createConditionForStringByLike(name.toLowerCase(), String.valueOf(objectValue)));

                    } else if ((item.getType().getTypeName().contains("Integer")
                            || item.getType().getTypeName().contains("Long")) && objectValue != null) {
                        sql.append(Utils.createConditionForNumber(name.toLowerCase(), (Number) objectValue));
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

    private void buildBetweenStatement(String fieldName, Integer from, Integer to, StringBuilder sql) {
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
    private void buildConditionForBuildingType(BuildingSearch buildingSearch, StringBuilder sql) {
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