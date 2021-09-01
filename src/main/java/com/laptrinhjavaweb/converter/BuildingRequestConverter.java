package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.entity.BuildingEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingRequestConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingRequest convertDTOToRequest(BuildingDTO buildingDTO) {
        BuildingRequest requestDTO = modelMapper.map(buildingDTO, BuildingRequest.class);
        return requestDTO;
    }

    public BuildingEntity convertToEntity(BuildingDTO dto) {
        BuildingEntity entity = modelMapper.map(dto, BuildingEntity.class);
        return entity;
    }
}
