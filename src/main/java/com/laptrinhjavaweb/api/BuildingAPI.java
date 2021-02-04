package com.laptrinhjavaweb.api;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.IBuildingService;
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
}
