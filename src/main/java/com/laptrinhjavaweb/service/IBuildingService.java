package com.laptrinhjavaweb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequestDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponseDTO;
import com.laptrinhjavaweb.exceptioncustom.DivideByZeroException;

public interface IBuildingService {
	Map<String, String> getDistricts();
	Map<String, String> getBuildingTypes();
	BuildingDTO save(BuildingDTO newBuilding);
	List<BuildingResponseDTO> findByCondition(BuildingRequestDTO buildingRequest);
	BuildingDTO getOne(Long id) throws DivideByZeroException, SQLException;

	List<BuildingDTO> findAll();

}
