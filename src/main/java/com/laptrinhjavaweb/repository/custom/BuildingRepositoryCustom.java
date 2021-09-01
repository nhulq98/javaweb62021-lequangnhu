package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepositoryCustom {
    // Get data
    List<BuildingEntity> findByCondition(BuildingRequest buildingRequest);

    //JPA-demo
//    List<BuildingEntity> findAll();
//    BuildingEntity findOne(Long id);

    // Logic
    String buildQueryForSearchBuilding(BuildingRequest buildingRequest);
    String buildJoinSQLClause(BuildingRequest buildingRequest);
    String buildWhereSQLClause(BuildingRequest buildingRequest);
    String buildConditionForBuildingType(List<String> buildingType);
    String buildBetweenStatement(String sqlWhere, Integer from, Integer to);
    String checkExistenceOfCondition(String prefix, String suffix, Object parameter);
    String checkExistenceOfJoinSQLClause(String[] joinStr, Object...parameters);
}
