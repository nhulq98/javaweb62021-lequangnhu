package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.StaffBuildingRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;

import java.util.List;

public interface IAssignmentBuildingService {
    //get Data
    List<StaffBuildingResponse> getStaffsAssignment(Long buildingId);
    List<StaffBuildingResponse> findAllStaff();

    //Change Data

    void updateAssignment(StaffBuildingRequest request);
    void removeDuplicate(List<AssignmentBuildingEntity> staffsOld, List<AssignmentBuildingEntity> staffsFromRequest);


    // logic
    boolean testSpecialCases(List<AssignmentBuildingEntity> rentAreaFromView, List<AssignmentBuildingEntity> rentAreasOld);
}
