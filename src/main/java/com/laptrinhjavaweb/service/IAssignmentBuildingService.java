package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.StaffBuildingRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;

import java.util.List;

public interface IAssignmentBuildingService {
    //get Data
    List<StaffBuildingResponse> getStaffsAssignment(Long buildingId);

    //Change Data

    void updateAssignment(StaffBuildingRequest request);
}
