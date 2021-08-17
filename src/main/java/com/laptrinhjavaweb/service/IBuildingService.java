package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.input.BuildingRequestDTO;
import com.laptrinhjavaweb.dto.output.BuildingResponseDTO;

public interface IBuildingService {
	Map<String, String> getDistricts();
	Map<String, String> getBuildingTypes();
	BuildingDTO save(BuildingDTO newBuilding);
	List<BuildingResponseDTO> findByCondition(BuildingRequestDTO buildingRequest);

	List<BuildingDTO> findAll();
}
