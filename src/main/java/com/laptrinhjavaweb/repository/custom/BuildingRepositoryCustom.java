package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepositoryCustom {
    // Get data
    List<BuildingEntity> findByCondition(Map<String, Object> requestParam);
}
