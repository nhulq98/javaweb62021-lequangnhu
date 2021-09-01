package com.laptrinhjavaweb.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.dto.request.StaffBuildingRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.repository.jdbc.IBuildingJDBC;
import com.laptrinhjavaweb.repository.jdbc.impl.BuildingJDBCImpl;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

	@Autowired
	private IBuildingService buildingService;

	@Autowired
	private IAssignmentBuildingService assignmentBuildingService;


	//SCOPE FOR GET DATA

    @PostMapping("/{id}")
    public ResponseEntity<BuildingDTO> getOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(buildingService.getOne(id));
    }

//    @GetMapping
//    public @ResponseBody
//    ResponseEntity<List<BuildingResponse>> findByCondition(@RequestParam Map<String, Object> requestParam,
//                                                           @RequestParam(value = "listType", required = false) List<String> listType) {
//        ObjectMapper mapper = new ObjectMapper();
//        requestParam.put("buildingTypeList", listType);
//        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);// avoid error when listType value is single
//        BuildingRequest buildingRequest = mapper.convertValue(requestParam, BuildingRequest.class);
//
//        return ResponseEntity.status(HttpStatus.OK).body(buildingService.findByCondition(buildingRequest));
//    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<BuildingDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(buildingService.findAll());
    }

    @GetMapping("/{id}/staffs")
    public @ResponseBody ResponseEntity<List<StaffBuildingResponse>> getStaffsOfBuilding(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(assignmentBuildingService.getStaffsAssignment(id));
    }


    //SCOPE FOR CHANGE DATA

    @PostMapping
	public ResponseEntity<BuildingDTO> createBuilding(@RequestBody BuildingDTO newBuilding) {

		return ResponseEntity.status(HttpStatus.OK).body(buildingService.save(newBuilding));
	}

	@PostMapping("/assignmentbuilding")
	public ResponseEntity<Void> updateAssignmentBuilding(@RequestBody StaffBuildingRequest request) {
		assignmentBuildingService.updateAssignment(request);
 		return ResponseEntity.ok().build();
	}

}
