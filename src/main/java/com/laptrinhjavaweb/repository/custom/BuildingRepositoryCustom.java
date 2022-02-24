package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.BuildingSearch;
import com.laptrinhjavaweb.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepositoryCustom {
    // Get data
    List<BuildingEntity> findByCondition(BuildingSearch searchBuilder);
}
