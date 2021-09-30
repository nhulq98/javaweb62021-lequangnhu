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
        try {
            requestParam.put("buildingTypes", listType);
            return ResponseEntity.status(HttpStatus.OK).body(buildingService.findByCondition(requestParam));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
        try {
            return ResponseEntity.status(HttpStatus.OK).body(assignmentBuildingService.getStaffsAssignment(id));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //SCOPE FOR CHANGE DATA

    @PostMapping
    public ResponseEntity<BuildingDTO> createBuilding(@RequestBody BuildingDTO newBuilding) {
        try {
            buildingService.save(newBuilding);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<List<StaffBuildingResponse>> deleteBuilding(@PathVariable Long id) {
        try {
            buildingService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public
    ResponseEntity<List<StaffBuildingResponse>> updateBuilding(@RequestBody BuildingDTO dto) {
        try {
            buildingService.update(dto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/assignmentbuilding")
    public ResponseEntity<Void> updateAssignmentBuilding(@RequestBody StaffBuildingRequest request) {
        try {
            assignmentBuildingService.updateAssignment(request);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
