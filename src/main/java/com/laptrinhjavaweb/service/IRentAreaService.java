package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.RentAreaEntity;

import java.util.List;

public interface IRentAreaService {
    void updateRentArea(BuildingDTO newBuilding);

    List<RentAreaEntity> loadRentAreaFromRequest(String rentAreas, BuildingDTO newBuilding);
}
