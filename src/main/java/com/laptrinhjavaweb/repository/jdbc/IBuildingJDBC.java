package com.laptrinhjavaweb.repository.jdbc;

import java.sql.ResultSet;
import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.input.BuildingRequest;
import com.laptrinhjavaweb.dto.output.BuildingResponse;

public interface IBuildingJDBC{
	List<BuildingResponse> findByCondition(BuildingRequest buildingRequest);
	String buildQueryV2(BuildingRequest buildingRequest);
	
	//Build Clauses
	String buildJoinSQLClause(BuildingRequest buildingRequest);
	String buildWhereSQLClause(BuildingRequest buildingRequest);
	String buildConditionForBuildingType(List<String> buildingType);
	String buildBetweenStatement(String sqlWhere, Long from, Long to);
	//String buildFromSQLClause(String select, String from);
	
	// Build condition
	String checkExistenceOfConditionV2(String prefix, String suffix, Object parameter);
	String checkExistenceOfJoinSQLClause(String[] joinStr, Object...parameters);
	boolean isNull(Object value);
	boolean isBlank(Object value);
	
	
	// For first version
	BuildingResponse convertResultSetToBuildingResponse(ResultSet resultSet);
	String buildQuery(BuildingRequest buildingRequest);
	String checkExistenceOfCondition(String conditionStr, Object parameter);
	StringBuilder checkAndKeyword(boolean temp, StringBuilder string);
}
