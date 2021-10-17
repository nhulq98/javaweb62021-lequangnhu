package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.BuildingSearch;
import com.laptrinhjavaweb.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepositoryCustom {
    // Get data
    List<BuildingEntity> findByCondition(Map<String, Object> requestParam);

    // Logic
    void authorization(StringBuilder sql, Long staffId);

    StringBuilder buildFromSQLClause(BuildingSearch buildingSearch);

    StringBuilder buildQueryForBuildingSearch(BuildingSearch buildingSearch);

    void buildWhereSQLClause(BuildingSearch buildingSearch, StringBuilder sql);

    void buildConditionForBuildingType(BuildingSearch buildingSearch, StringBuilder sql);

    void buildBetweenStatement(String fieldName, Integer from, Integer to, StringBuilder sql);

    //void buildJoinSQLClause(BuildingSearch buildingSearch, StringBuilder sql);
}
