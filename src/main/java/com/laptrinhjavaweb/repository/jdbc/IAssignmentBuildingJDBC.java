package com.laptrinhjavaweb.repository.jdbc;

import com.laptrinhjavaweb.entity.UserEntity;

import java.util.List;

public interface IAssignmentBuildingJDBC {
    //Get data
    List<UserEntity> getAllStaffs();
    List<UserEntity> findStaffById(Long buildingId);

    // for change data
    void deleteStaffById(Long buildingId, Long staffId);
    void insertStaffById(Long buildingId, Long staffId);
}
