package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.StaffBuildingRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;

import java.util.List;

public interface IAssignmentBuildingService {
    //get Data
    List<StaffBuildingResponse> getStaffsAssignment(Long buildingId);

    //Change Data
    void saveAssignmentStaffs(List<AssignmentBuildingEntity> entities);

    void updateAssignment(StaffBuildingRequest request);

    void deleteAssignmentStaffs(List<AssignmentBuildingEntity> entities);
}
