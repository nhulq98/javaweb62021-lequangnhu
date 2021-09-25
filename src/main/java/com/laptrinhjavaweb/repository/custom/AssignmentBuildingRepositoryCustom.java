package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.view.StaffEntity;

import java.util.List;

public interface AssignmentBuildingRepositoryCustom {
    List<AssignmentBuildingEntity> findAllByBuildingId(Long id);
    List<StaffEntity> findAllCustom(Long buildingId);
}
