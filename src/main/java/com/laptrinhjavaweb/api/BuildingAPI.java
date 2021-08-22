package com.laptrinhjavaweb.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequestDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponseDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

	@Autowired
	private IBuildingService buildingService;

	@PostMapping
	public ResponseEntity<BuildingDTO> createBuilding(@RequestBody BuildingDTO newBuilding) {

		return ResponseEntity.status(HttpStatus.OK).body(buildingService.save(newBuilding));
	}

	@GetMapping
	public @ResponseBody
	ResponseEntity<List<BuildingResponseDTO>> findByCondition(@RequestParam Map<String, Object> requestParam,
														@RequestParam(value = "listType", required = false) List<String> listType) {
		ObjectMapper mapper = new ObjectMapper();
		requestParam.put("buildingTypeList", listType);
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		BuildingRequestDTO buildingRequest = mapper.convertValue(requestParam, BuildingRequestDTO.class);

		return ResponseEntity.status(HttpStatus.OK).body(buildingService.findByCondition(buildingRequest));
	}


	@GetMapping("/getone")
	public @ResponseBody
	ResponseEntity<BuildingDTO> getOne(@RequestParam(value = "buildingId") Long buildingId) throws SQLException {
		return ResponseEntity.status(HttpStatus.OK).body(buildingService.getOne(buildingId));
	}

}
