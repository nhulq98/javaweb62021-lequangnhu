package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.BuildingResponseConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.exception.DivideByZeroException;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private BuildingResponseConverter buildingResponseConverter;

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public Map<String, String> getDistricts() {
        Map<String, String> districts = new HashMap<>();
        for (DistrictsEnum item: DistrictsEnum.values()) {
            districts.put(item.toString(), item.getDistrictValue());
        }
        return districts;
    }

    @Override
    public Map<String, String> getBuildingTypes() {
        Map<String, String> buildingTypes = new HashMap<>();
        for (BuildingTypesEnum item: BuildingTypesEnum.values()) {
            buildingTypes.put(item.toString(), item.getBuildingTypeValue());
        }
        return buildingTypes;
    }

    @Override
    public BuildingDTO getOne(Long id) {
        try{
            BuildingEntity entity = buildingRepository.findById(id).get();
            return buildingConverter.convertEntityToDTO(entity);
        }catch (NoSuchElementException e){
            return new BuildingDTO();
        }
    }


    @Override
    public List<BuildingDTO> findAll() {
        List<BuildingEntity> entities = buildingRepository.findAll();
        List<BuildingDTO> result = new ArrayList<>();
        for(BuildingEntity item: entities){
            result.add(buildingConverter.convertEntityToDTO(item));
        }
        return result;
    }

    @Override
    public List<BuildingResponse> findByCondition(BuildingRequest buildingRequest) {
        List<BuildingEntity> entities = buildingRepository.findByCondition(buildingRequest);
        List<BuildingResponse> result = new ArrayList<>();
        for(BuildingEntity entity: entities){
            result.add(buildingResponseConverter.convertEntityToResponse(entity));
        }
        return result;
    }

    @Override
    public BuildingDTO save(BuildingDTO newBuilding) {
        return null;
    }

    /*@Override
    public List<BuildingResponse> findByCondition(BuildingRequest buildingRequest) {

        BuildingJDBCImpl buildingimpl = new BuildingJDBCImpl();
        List<BuildingResponse> result = new ArrayList<>();
        List<BuildingEntity> entities = buildingimpl.findByCondition(buildingRequest);

        // convert BuildingEntity to BuildingResponseDTO
        for (BuildingEntity buildingEntity : entities) {
            BuildingResponse responseDTO = buildingResponseConverter.convertEntityToResponse(buildingEntity);
            result.add(responseDTO);
        }
        return result;
    }

    @Override
    public BuildingDTO getOne(Long id){
        return buildingConverter.convertEntityToDTO(buildingRepository.getOne(id));
    }

    @Override
    public List<BuildingDTO> findAll() {
        List<BuildingDTO> result = new ArrayList<>();
        List<BuildingEntity> entities = buildingRepository.findAll();
        for (BuildingEntity entity : entities) {
            result.add(buildingConverter.convertEntityToDTO(entity));
        }
        return result;
    }
    
    // for change data
    @Override
    @Transactional
    public BuildingDTO save(BuildingDTO newBuilding) {
*//*        int a = 10, b = 2;
        if (a != 0 && b == 0) {
            throw new DivideByZeroException("Không thể nào chia cho 0");
        }*//*
        BuildingEntity buildingEntity = buildingConverter.convertDTOToEntity(newBuilding);
        return buildingConverter.convertEntityToDTO(buildingRepository.save(buildingEntity));
    }*/



}
