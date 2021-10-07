package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.RentAreaEntity;

import java.util.List;

public interface IRentAreaService {
    List<RentAreaEntity> loadRentAreaFromRequest(String rentAreas, BuildingDTO newBuilding);

    void updateRentArea(BuildingDTO newBuilding);


    void removeDuplicate(List<RentAreaEntity> rentAreasOld, List<RentAreaEntity> rentAreaFromView);

    //logic
    boolean testSpecialCases(List<RentAreaEntity> rentAreaFromView, List<RentAreaEntity> rentAreasOld);
}
