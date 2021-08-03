package com.laptrinhjavaweb.repository.jdbc;

import java.sql.ResultSet;
import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.input.BuildingRequest;

public interface IBuildingJDBC{
	List<BuildingDTO> findAll();
	List<BuildingDTO> findByCondition(BuildingRequest buildingRequest);
	BuildingDTO convertToBuildingDTO(ResultSet resultSet);
	String buildQuery(BuildingRequest buildingRequest);
	String buildQuery_V2(BuildingRequest buildingRequest);
	StringBuilder checkAndKeyword(boolean temp, StringBuilder string);
	boolean isNull(Object value);
	boolean isBlank(Object value);
	String checkExistenceOfCondition(String sql, Object parameter);
	StringBuilder buildBetweenStatement(String sqlWhere, Object from, Object to);
}
