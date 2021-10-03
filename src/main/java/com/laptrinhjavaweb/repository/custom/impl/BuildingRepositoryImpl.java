package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingSearch;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.MyUserDetail;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository// nó hiểu đây là 1 module. và bảo IoC container tạo một object duy nhất (singleton)
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @Autowired
    private BuildingConverter buildingConverter;

    @PersistenceContext// initializer for EntityManager
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findByCondition(Map<String, Object> requestParam) {
        BuildingSearch searchBuilder = buildingConverter.convertMapToBuider(requestParam);
        StringBuilder sql = this.buildQueryForBuildingSearch(searchBuilder);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    /**
     * buildQueryForSearchBuilding to concat all clauses to complete sql final
     *
     * @param buildingSearch the building from search form
     * @return sql String final
     */
    @Override
    public StringBuilder buildQueryForBuildingSearch(BuildingSearch buildingSearch) {
        //SELECT BD.id, BD.name, BD.street, BD.ward, DT.name, BD.managername, BD.managerphone, BD.floorarea, BD.rentprice, BD.servicefee"
        StringBuilder sql = new StringBuilder("SELECT BD.*")
                .append(" FROM building BD ");
        if (buildingSearch.getRentAreaFrom() != null || buildingSearch.getRentAreaTo() != null) {
            sql.append(" JOIN rentarea RE ON RE.buildingid = BD.id ");
        }
        this.buildWhereSQLClause(buildingSearch, sql);
        sql.append(" GROUP BY BD.id ");
        return sql;
    }

    @Override
    public StringBuilder buildBuildingSearchPart1(BuildingSearch buildingSearch) {
        Field[] fields = BuildingSearch.class.getDeclaredFields();

//        for(Field item: fields){
//            //if(item.get())
//            item.getModifiers()
//        }

        return null;
    }

    @Override
    public StringBuilder buildBuildingSearchPart2(BuildingSearch buildingSearch) {
        return null;
    }

    /**
     * Add condition into Where clause if value != null
     *
     * @param buildingSearch
     * @return Where statement String
     */
    @Override
    public void buildWhereSQLClause(BuildingSearch buildingSearch, StringBuilder sql) {
        authorization(sql, buildingSearch.getStaffId());
        buildBetweenStatement("RE.value", buildingSearch.getRentAreaFrom(), buildingSearch.getRentAreaTo(), sql);
        buildBetweenStatement("rentprice", buildingSearch.getRentPriceFrom(), buildingSearch.getRentPriceTo(), sql);
        buildConditionForBuildingType(buildingSearch, sql);
        Field[] fields = BuildingSearch.class.getDeclaredFields();
        for (Field item : fields) {
            item.setAccessible(true);
            String name = item.getName();
            if (!name.equals("buildingTypes")
                    && !name.startsWith("rentArea") && !name.startsWith("rentPrice")) {
                try {
                    Object objectValue = item.get(buildingSearch); // reflect chay vo then building va scan cai field do xong no boc tach du lieu ra
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
    }

    /**
     * // This code below to process authorization when user logged search
     * Has 3 Case:
     *    case 1: current user has role is staff ==> search by userID(current user)
     *    case 2: current user has role is manager ==> search normal(not by userID(current user))
     *    case 3: if current user has role is manager and it's searching by staffId ==> search by staffID
     *
     * @param sql
     * @param staffId
     */
    public void authorization(StringBuilder sql, Long staffId){

        boolean temp = false; // user Logged has role: MANAGER
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetail userDetails = (MyUserDetail) authentication.getPrincipal();
        //case 1:
        for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
            if (grantedAuthority.getAuthority().contains(SystemConstant.ROLE_STAFF)) {// default: search value follow by current logged user
                sql.append(" JOIN assignmentbuilding ASB on  ASB.buildingid = BD.id ");
                sql.append(" WHERE 1=1 ");
                sql.append(" AND ASB.staffid =" + userDetails.getId());
                temp = true; // user Logged has role: STAFF
            }
        }
        //case 2:
        if (temp == false && staffId != null) {// User Logged has role: MANAGER and exists by "staffId" search condition
            sql.append(" JOIN assignmentbuilding ASB on  ASB.buildingid = BD.id ");
            sql.append(" WHERE 1=1 ");
        } else if (temp == false) {//case 3: User Logged has role: MANAGER
            sql.append(" WHERE 1=1 ");
        }
    }

    /**
     * generic method to create condition clause with values have type is String become like this.
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
     * Generic method to create condition with values have type is Integer become like this.
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
     * solution 1: AND RT.code = 'tang-tret' OR RT.code = 'nguyen-can'
     * solution 2: AND (RT)
     *
     * @param buildingSearch
     * @param sql
     */
    @Override
    public void buildConditionForBuildingType(BuildingSearch buildingSearch, StringBuilder sql) {
        List<String> types = buildingSearch.getRentTypes();
        if (types != null && types.size() > 0) {
            sql.append(" AND (");
            // java 7
        /*
        List<String> buildingTypes = buildingSearch.getBuildingTypes();
        for(int i = 0; i < buildingTypes.size(); i++){
            buildingTypes.set(i, " BD.code LIKE '%" +buildingTypes.get(i)+"%'");
        }
        String typesSQL = String.join(" or ", buildingTypes);
        sql.append(typesSQL);
*/
            // java 8
            String typeStr = Arrays.stream(buildingSearch.getRentTypes().toArray())
                    .map((item) -> "BD.type LIKE '%" + item + "%'") // dùng lambda để thực hiện hành động cộng chuỗi và trả về giá trị
                    .collect(Collectors.joining(" or "));
            sql.append(typeStr);

            sql.append(")");
        }
    }
}
