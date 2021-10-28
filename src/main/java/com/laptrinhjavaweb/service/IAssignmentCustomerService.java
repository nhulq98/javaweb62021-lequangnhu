package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.StaffRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;

import java.util.List;

public interface IAssignmentCustomerService {
    List<StaffBuildingResponse> findAllStaffsByCusId(Long customerId);
    void updateAssignmentStaffs(StaffRequest request);
}
