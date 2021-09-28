package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.RentAreaEntity;

import java.util.List;

public interface RentAreaRepositoryCustom {
    List<RentAreaEntity> findAllByBuildingId(Long id);

}
