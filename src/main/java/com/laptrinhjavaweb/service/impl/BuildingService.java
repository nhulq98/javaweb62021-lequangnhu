package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.BuildingResponseConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.jdbc.impl.BuildingJDBCImpl;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private BuildingResponseConverter buildingResponseConverter;

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
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
//        int a = 10, b = 2;
//        if (a != 0 && b == 0) {
//            throw new DivideByZeroException("Không thể nào chia cho 0");
//        }
        BuildingEntity buildingEntity = buildingConverter.convertDTOToEntity(newBuilding);
        return buildingConverter.convertEntityToDTO(buildingRepository.save(buildingEntity));
    }

}
