package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.StaffRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;

import java.util.List;

public interface IAssignmentBuildingService {
    //get Data
    List<StaffBuildingResponse> findAllStaffsByBuildingId(Long buildingId);
    List<StaffBuildingResponse> findAllStaffs();

    //Change Data
    void updateBuildingManagementStaffs(StaffRequest request);
}
