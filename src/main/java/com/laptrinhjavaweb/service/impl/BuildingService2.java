package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuildingService2 implements IBuildingService {


    @Override
    public List<BuildingDTO> findAll() {
        return null;
    }

    @Override
    public List<BuildingResponse> findByCondition(BuildingRequest buildingRequest) {
        return null;
    }

    @Override
    public BuildingDTO getOne(Long id) {
        return null;
    }

    @Override
    public BuildingDTO save(BuildingDTO newBuilding) {
        return null;
    }

    @Override
    public Map<String, String> getDistricts() {
        return null;
    }

    @Override
    public Map<String, String> getBuildingTypes() {
        return null;
    }
}
