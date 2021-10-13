package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.request.StaffRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customer/assignment")
public class AssignmentCustomerAPI {

    @Autowired// find bean is created and inject into.
    private IAssignmentCustomerService assignmentCustomerService;

    /**
     * Get ALl staffs available and staffs is managing building with buildingId from request param.
     * If staffs is managing building ==> set value "status"="checked" for those staffs
     *
     * @param id
     * @return Staff List
     */
    @GetMapping("/{id}/staffs")
    public @ResponseBody
    ResponseEntity<List<StaffBuildingResponse>> getStaffsOfCustomer(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(assignmentCustomerService.findAllStaffsByCusId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> updateAssignmentCustomer(@RequestBody StaffRequest request) {

        assignmentCustomerService.updateAssignment(request);
//        return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
