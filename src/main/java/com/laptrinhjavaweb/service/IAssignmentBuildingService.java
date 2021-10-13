package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.StaffRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;

import java.util.List;

public interface IAssignmentBuildingService {
    //get Data
    List<StaffBuildingResponse> getStaffsAssignment(Long buildingId);
    List<StaffBuildingResponse> findAllStaff();

    //Change Data
    void updateAssignment(StaffRequest request);
    //void removeDuplicate(List<AssignmentBuildingEntity> staffsOld, List<AssignmentBuildingEntity> staffsFromRequest);


    // logic
    boolean testSpecialCases(List<AssignmentBuildingEntity> rentAreaFromView, List<AssignmentBuildingEntity> rentAreasOld);
    List<AssignmentBuildingEntity> createStaffs(Long buildingId, List<Long> idStaffs);
}
