package com.laptrinhjavaweb.api;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.condition.BuildingCondition;
import com.laptrinhjavaweb.service.IBuildingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
	
	@Autowired
	private IBuildingService buildingService;
	
	@PostMapping
	public BuildingDTO createBuilding(@RequestBody BuildingDTO newBuilding) {
		return buildingService.save(newBuilding);
	}
	
	@GetMapping
	public @ResponseBody List<BuildingDTO> findByCondition(@RequestBody BuildingCondition buildingCondition) {
		return buildingService.findByCondition(buildingCondition);
	}
}
