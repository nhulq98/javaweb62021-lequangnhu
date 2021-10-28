package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;

import java.util.List;

public interface AssignmentBuildingRepositoryCustom {
    List<AssignmentBuildingEntity> findAllByBuildingId(Long id);
}
