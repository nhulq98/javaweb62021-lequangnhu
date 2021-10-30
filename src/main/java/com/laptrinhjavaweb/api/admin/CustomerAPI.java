package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.dto.request.StaffRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/customer")
@RestController
public class CustomerAPI {

    @Autowired
    private ICustomerService service;

    @Autowired// find bean is created and inject into.
    private IAssignmentCustomerService assignmentCustomerService;

    @PostMapping
    public ResponseEntity<Void> CreateOne(@RequestBody CustomerDTO newDto){

        service.createOrUpdate(newDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody CustomerRequest customerRequest){

        service.deleteByListId(customerRequest.getCustomerIds());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CustomerDTO newDto){

        service.createOrUpdate(newDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Get ALl staffs available and staffs is managing building with buildingId from request param.
     * If staffs is managing building ==> set value "status"="checked" for those staffs
     *
     * @param id
     * @return Staff List
     */
    @GetMapping("/{id}/staffs")
    public ResponseEntity<List<StaffBuildingResponse>> getStaffsOfCustomer(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(assignmentCustomerService.findAllStaffsByCusId(id));
    }

    @PutMapping("/assignment")
    public ResponseEntity<Void> updateCustomerManagementStaffs(@RequestBody StaffRequest request) {

        assignmentCustomerService.updateCustomerManagementStaffs(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}