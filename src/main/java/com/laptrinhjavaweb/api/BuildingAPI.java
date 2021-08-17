package com.laptrinhjavaweb.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.input.BuildingRequestDTO;
import com.laptrinhjavaweb.dto.output.BuildingResponseDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
	public @ResponseBody List<BuildingResponseDTO> findByCondition(@RequestParam Map<String, Object> requestParam) {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(requestParam);
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		BuildingRequestDTO buildingRequest = mapper.convertValue(requestParam, BuildingRequestDTO.class);
		return buildingService.findByCondition(buildingRequest);
	}
}
