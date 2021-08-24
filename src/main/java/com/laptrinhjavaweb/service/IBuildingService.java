package com.laptrinhjavaweb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.exception.DivideByZeroException;

public interface IBuildingService {
	//get Data
	List<BuildingDTO> findAll();
	List<BuildingResponse> findByCondition(BuildingRequest buildingRequest);
	BuildingDTO getOne(Long id) throws DivideByZeroException, SQLException;

	//Change Data
	BuildingDTO save(BuildingDTO newBuilding);
}
