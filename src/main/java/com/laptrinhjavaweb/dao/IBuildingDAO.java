package com.laptrinhjavaweb.dao;

import java.sql.ResultSet;
import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.condition.BuildingCondition;

public interface IBuildingDAO {
	List<BuildingDTO> findAll();
	List<BuildingDTO> findByCondition(BuildingCondition condition);
	BuildingDTO convertToBuildingDTO(ResultSet resultSet);
	String BuildQuery(BuildingCondition condition);
	StringBuilder checkAndKeyword(boolean temp, StringBuilder string);
}
