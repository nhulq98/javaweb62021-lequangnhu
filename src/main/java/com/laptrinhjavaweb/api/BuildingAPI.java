package com.laptrinhjavaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.input.BuildingRequest;
import com.laptrinhjavaweb.service.IBuildingService;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

	@Autowired
	private IBuildingService buildingService;

	@PostMapping
	public BuildingDTO createBuilding(@RequestBody BuildingDTO newBuilding) {
		return buildingService.save(newBuilding);
	}

//	@GetMapping
//	public @ResponseBody List<BuildingDTO> findByCondition(@RequestBody BuildingCondition buildingCondition) {
//		return buildingService.findByCondition(buildingCondition);
//	}

	@GetMapping
	public @ResponseBody List<BuildingDTO> findByCondition(@RequestParam Map<String, String> requestParam) {
		ObjectMapper mapper = new ObjectMapper();
		BuildingRequest buildingRequest = mapper.convertValue(requestParam, BuildingRequest.class);
		
		return buildingService.findByCondition(buildingRequest);
	}
}
