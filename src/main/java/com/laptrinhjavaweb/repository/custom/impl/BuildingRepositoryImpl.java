package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom{

    @PersistenceContext// giúp khởi tạo đối tượng EntityManager
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findByCondition(BuildingRequest buildingRequestDTO) {
        try {
            String sql = this.buildQueryForSearchBuilding(buildingRequestDTO);
            Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
            return query.getResultList();
        }catch (RuntimeException e){
            return new ArrayList<BuildingEntity>();
        }
    }

//    @Override
//    public List<BuildingEntity> findAll() {
//        //native SQL
//        String sqlNative = "SELECT * FROM building";
//        Query query = entityManager.createNativeQuery(sqlNative, BuildingEntity.class);
//
//        //cách viết của JPQL
////        String sqlJPQL = "FROM BuildingEntity"; //FROM + Class Entity
////        Query query = entityManager.createQuery(sqlJPQL, BuildingEntity.class);
//
//        return query.getResultList();
//    }
//
//    @Override
//    public BuildingEntity findOne(Long id) {
//        try {
//            //cách viết của JPQL: ta phải sử dụng các tên của java (tên class, tên biến)
//            String sqlJPQL = " FROM BuildingEntity WHERE id = " + id; //FROM + Class Entity  WHERE variable of Class Entity
//            Query query = entityManager.createQuery(sqlJPQL, BuildingEntity.class);
//            return (BuildingEntity) query.getSingleResult();
//        }catch (RuntimeException e){
//            return new BuildingEntity();
//        }
//    }

    /**
     * buildQueryForSearchBuilding to concat all clauses to complete sql final
     * @return sql final
     * @param buildingRequest the building from search form
     */
    @Override
    public String buildQueryForSearchBuilding(BuildingRequest buildingRequest) {
        //SELECT BD.id, BD.name, BD.street, BD.ward, DT.name, BD.managername, BD.managerphone, BD.floorarea, BD.rentprice, BD.servicefee"
        try {
            return new StringBuilder("SELECT BD.*")
                    .append(" FROM building BD ")
                    .append(this.buildJoinSQLClause(buildingRequest))
                    .append(this.buildWhereSQLClause(buildingRequest))
                    .append(" GROUP BY BD.id ").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String buildBetweenStatement(String whereSQLClause, Integer from, Integer to) {
        if(!StringUtils.isNull(from) || !StringUtils.isNull(to)) {
            if(!StringUtils.isNull(from) && !StringUtils.isNull(to)) {
                return (" AND "+ whereSQLClause +" BETWEEN " + from + " AND " + to + " ");
            }else if(!StringUtils.isNull(from) && StringUtils.isNull(to)) {
                return (" AND "+ whereSQLClause +" >= " + from + " ");
            }else {
                return (" AND "+ whereSQLClause +" <= " + to + " ");
            }
        }
        return "";
    }

    @Override
    public String buildWhereSQLClause(BuildingRequest buildingRequest) {
        StringBuilder whereSQLClause = new StringBuilder(" WHERE 1=1 ");// Use StringBuilder with purpose is saved memory

        whereSQLClause
                .append(this.buildConditionForBuildingType(buildingRequest.getBuildingTypeList()))
                .append(this.checkExistenceOfCondition (" AND BD.name LIKE '%", "%' ", buildingRequest.getName()))
                .append(this.checkExistenceOfCondition (" AND BD.street LIKE '%", "%' ", buildingRequest.getStreet()))
                .append(this.checkExistenceOfCondition (" AND BD.ward LIKE '%", "%' ", buildingRequest.getWard()))
                .append(this.checkExistenceOfCondition (" AND DT.code = ", " ", "\"" + buildingRequest.getDistrictCode() + "\""))
                .append(this.checkExistenceOfCondition (" AND BD.floorarea = ", " ", buildingRequest.getFloorArea()))
                .append(this.checkExistenceOfCondition (" AND BD.numberOfBasement = ", " ", buildingRequest.getNumberOfBasement()))
                .append(this.checkExistenceOfCondition (" AND BD.direction  LIKE '%", "%' ", buildingRequest.getDirection()))
                .append(this.checkExistenceOfCondition (" AND BD.Level LIKE '%", "%' ", buildingRequest.getLevel()))
                .append(this.checkExistenceOfCondition (" AND BD.managername LIKE '%", "%' ", buildingRequest.getManagerName()))
                .append(this.checkExistenceOfCondition (" AND BD.managerphone LIKE '%", "%' ", buildingRequest.getManagerPhone()))
                .append(this.checkExistenceOfCondition (" AND ASB.staffid = ", " ", buildingRequest.getUserID()))
                .append(this.buildBetweenStatement("BD.rentprice", buildingRequest.getRentPriceFrom(), buildingRequest.getRentPriceTo()))
                .append(this.buildBetweenStatement("RE.value", buildingRequest.getRentEreaFrom(), buildingRequest.getRentEreaTo()));

        return whereSQLClause.toString();
    }

    public void appendString(StringBuilder stringResult, String str){
        stringResult.append(str);
    }

    @Override
    public String buildConditionForBuildingType(List<String> buildingType) {
        StringBuilder conditionForBuildingType = new StringBuilder("");
        if (!StringUtils.isNull(buildingType)) {
            appendString(conditionForBuildingType, checkExistenceOfCondition(" AND RT.code = \"", "\" ", buildingType.get(0)));
            //conditionForBuildingType.append(checkExistenceOfCondition(" AND RT.code = \"", "\" ", buildingType.get(0)));
            if (buildingType.size() > 1) {
                for (int i = 1; i < buildingType.size(); i++) {
                    //conditionForBuildingType.append(checkExistenceOfCondition(" OR RT.code = \"", "\" ", buildingType.get(i)));
                    appendString(conditionForBuildingType, checkExistenceOfCondition(" OR RT.code = \"", "\" ", buildingType.get(i)));
                }
            }
        }
        return conditionForBuildingType.toString();
    }

    @Override
    public String buildJoinSQLClause(BuildingRequest buildingRequest) {
        String[] assignmentbuilding = {" JOIN assignmentbuilding ASB on  ASB.buildingid = BD.id "};
        String[] rentarea = {" JOIN rentarea RE ON RE.buildingid = BD.id "};
        String[] buildingrenttype = {" JOIN buildingrenttype BRT ON BRT.buildingid = BD.id ",
                " JOIN renttype RT ON RT.id = BRT.renttypeid "};

        StringBuilder joinSQLClause = new StringBuilder(" JOIN district DT on DT.id = BD.districtid ")
                .append(this.checkExistenceOfJoinSQLClause(assignmentbuilding, buildingRequest.getUserID()))
                .append(this.checkExistenceOfJoinSQLClause(rentarea, buildingRequest.getRentEreaFrom(), buildingRequest.getRentEreaTo()))
                .append(this.checkExistenceOfJoinSQLClause(buildingrenttype, buildingRequest.getBuildingTypeList()));

        return joinSQLClause.toString();
    }

    @Override
    public String checkExistenceOfCondition(String prefix, String suffix, Object parameter) {
        if(!StringUtils.isNullOrEmpty(parameter) && !parameter.equals("\"" + null +"\"")) {
            return (prefix + parameter + suffix);
        }
        return "";
    }

    /**
     * checkExistenceOfJoinSQLClause to check existence of this join
     * @return JoinSQLClause
     * @param joinStr, parameters
     */
    @Override
    public String checkExistenceOfJoinSQLClause(String[] joinStr, Object...parameters) { // optimal
        StringBuilder joinClauseStr = new StringBuilder("");
        boolean temp = false;
        for(Object obj: parameters) {
            if(!StringUtils.isNullOrEmpty(obj)) {
                temp = true;
                break;
            }
        }
        if(temp){
            for(String str: joinStr) {
                joinClauseStr.append(str);
            }
            return joinClauseStr.toString();
        }
        return "";
    }
}