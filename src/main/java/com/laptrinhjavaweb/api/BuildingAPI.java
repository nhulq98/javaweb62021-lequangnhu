package com.laptrinhjavaweb.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.dto.request.StaffBuildingRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
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

	//scope for get data
    @GetMapping
    public @ResponseBody
    ResponseEntity<List<BuildingResponse>> findByCondition(@RequestParam Map<String, Object> requestParam,
                                                           @RequestParam(value = "listType", required = false) List<String> listType) {
        ObjectMapper mapper = new ObjectMapper();
        requestParam.put("buildingTypeList", listType);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);// avoid error when listType value is single
        BuildingRequest buildingRequest = mapper.convertValue(requestParam, BuildingRequest.class);

        return ResponseEntity.status(HttpStatus.OK).body(buildingService.findByCondition(buildingRequest));
    }

    @GetMapping("/{buildingid}/staffs")
    public @ResponseBody ResponseEntity<List<StaffBuildingResponse>> getStaffsOfBuilding(@PathVariable Long buildingid) {
        return ResponseEntity.status(HttpStatus.OK).body(assignmentBuildingService.getStaffsAssignment(buildingid));
    }

    //scope for change data
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
