package com.laptrinhjavaweb.api.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.input.BuildingRequestDTO;
import com.laptrinhjavaweb.dto.output.BuildingResponseDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IDistrictService;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

	@Autowired
	private IBuildingService buildingService;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IDistrictService districtService;
	
	@PostMapping
	public BuildingDTO create(@RequestBody BuildingDTO newBuilding) {
		return buildingService.save(newBuilding);
	}

	@GetMapping
	public @ResponseBody List<BuildingResponseDTO> findByCondition(@RequestParam Map<String, String> requestParam) {
		ObjectMapper mapper = new ObjectMapper();
		BuildingRequestDTO buildingRequest = mapper.convertValue(requestParam, BuildingRequestDTO.class);

		return buildingService.findByCondition(buildingRequest);
	}

	@GetMapping("/{buildingid}/staffs")
	public @ResponseBody List<UserDTO> loadStaff(@PathVariable Long buildingid) {

		List<UserDTO> staffsAssignmentBuilding = new ArrayList<>();
		List<UserDTO> staffAll = userService.getStaffs();
		staffsAssignmentBuilding = userService.getStaffsManagementBuildingById(buildingid);

		for(UserDTO userDTO: staffsAssignmentBuilding){
			for(int i = 0; i < staffAll.size(); i++){
				if(userDTO.getId() == staffAll.get(i).getId()){
					staffAll.get(i).setChecked("checked");
					break;// exit for outside loop
				}
			}
		}

		return staffAll;
	}

	@GetMapping("/{buildingid}")
	public @ResponseBody BuildingDTO getOne(@RequestParam Long buildingid){

		return null;
	}
}
