package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.dto.response.DistrictResponse;
import com.laptrinhjavaweb.dto.response.TypesResponse;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    //get Data
    List<DistrictResponse> getDistricts();

    List<TypesResponse> getBuildingTypes();

    List<TypesResponse> getBuildingTypes(List<String> rentypes);

    List<BuildingResponse> findByCondition(Map<String, Object> requestParam);

    BuildingDTO getOne(Long id);

    //Change Data
    void save(BuildingDTO newBuilding);

    void update(BuildingDTO newBuilding);



    void deleteById(Long id);
    //==============================================
}
