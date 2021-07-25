package com.laptrinhjavaweb.repository.jdbc;

import java.sql.ResultSet;
import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.condition.BuildingCondition;

public interface IBuildingJDBC {
	List<BuildingDTO> findAll();
	List<BuildingDTO> findByCondition(BuildingCondition condition);
	BuildingDTO convertToBuildingDTO(ResultSet resultSet);
	String buildQuery(BuildingCondition condition);
	String buildQuery_V2(BuildingCondition condition);
	StringBuilder checkAndKeyword(boolean temp, StringBuilder string);
}
