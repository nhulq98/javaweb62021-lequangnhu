package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.StaffBuildingRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/building")
//@Comp
public class BuildingAPI {

    @Autowired // là tìm module tương ứng (tạo từ trước) và inject vào đó.
    private IBuildingService buildingService;

    @Autowired// là tìm module tương ứng (tạo từ trước) và inject vào đó.
    private IAssignmentBuildingService assignmentBuildingService;

    //SCOPE FOR GET DATA

    @PostMapping("/{id}")
    public ResponseEntity<BuildingDTO> getOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(buildingService.getOne(id));

    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<BuildingResponse>> findByCondition(@RequestParam Map<String, Object> requestParam,
                                                           @RequestParam(value = "listType", required = false) List<String> listType) {
        requestParam.put("buildingTypes", listType);
        return ResponseEntity.status(HttpStatus.OK).body(buildingService.findByCondition(requestParam));
    }

    /**
     * Get ALl staffs available and staffs is managing building with buildingId from request param.
     * If staffs is managing building ==> set value "status"="checked" for those staffs
     *
     * @param id
     * @return Staff List
     */
    @GetMapping("/{id}/staffs")
    public @ResponseBody
    ResponseEntity<List<StaffBuildingResponse>> getStaffsOfBuilding(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(assignmentBuildingService.getStaffsAssignment(id));

    }

    //SCOPE FOR CHANGE DATA

    @PostMapping
    public ResponseEntity<BuildingDTO> createBuilding(@RequestBody BuildingDTO newBuilding) {

        buildingService.save(newBuilding);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public @ResponseBody
    ResponseEntity<List<StaffBuildingResponse>> deleteBuilding(@PathVariable Long id) {

        buildingService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<List<StaffBuildingResponse>> updateBuilding(@RequestBody BuildingDTO dto) {

        buildingService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PostMapping("/assignmentbuilding")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> updateAssignmentBuilding(@RequestBody StaffBuildingRequest request) {

        assignmentBuildingService.updateAssignment(request);
        return ResponseEntity.ok().build();

    }
}
