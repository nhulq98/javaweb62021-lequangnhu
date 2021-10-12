package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.BuildingSearch;
import com.laptrinhjavaweb.dto.MyUserDetail;
import com.laptrinhjavaweb.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepositoryCustom {
    // Get data
    List<BuildingEntity> findByCondition(Map<String, Object> requestParam);

    // Logic
    void authorization(StringBuilder sql, Long staffId);

    /**
     * buildQueryForSearchBuilding to concat all clauses to complete sql final
     *
     * @param buildingSearch the building from search form
     * @return sql String final
     */
    StringBuilder buildQueryForBuildingSearch(BuildingSearch buildingSearch);

    //void buildJoinSQLClause(BuildingSearch buildingSearch, StringBuilder sql);

    void buildWhereSQLClause(BuildingSearch buildingSearch, StringBuilder sql);

    StringBuilder createConditionForStringByLike(String fieldName, String value);

    StringBuilder createConditionForNumber(String fieldName, Number value);

    void buildConditionForBuildingType(BuildingSearch buildingSearch, StringBuilder sql);

    void buildBetweenStatement(String fieldName, Integer from, Integer to, StringBuilder sql);
}
