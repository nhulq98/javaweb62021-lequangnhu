package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.request.StaffBuildingRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/assignment")
public class AssignmentCustomerAPI {

    @Autowired// là tìm module tương ứng (tạo từ trước, nằm trong IOC container) và inject vào đó.
    private IAssignmentBuildingService assignmentBuildingService;

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

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PostMapping("/assignmentbuilding")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> updateAssignmentBuilding(@RequestBody StaffBuildingRequest request) {

        assignmentBuildingService.updateAssignment(request);
        return ResponseEntity.ok().build();
    }

}
