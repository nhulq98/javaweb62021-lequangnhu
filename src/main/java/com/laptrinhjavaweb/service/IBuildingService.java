package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.input.BuildingRequest;
import com.laptrinhjavaweb.dto.output.BuildingResponse;

public interface IBuildingService {
	Map<String, String> getDistricts();
	Map<String, String> getBuildingTypes();
	BuildingDTO save(BuildingDTO newBuilding);
	List<BuildingResponse> findByCondition(BuildingRequest buildingRequest);
}
