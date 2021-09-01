package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.jdbc.impl.DistrictJDBCImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuildingResponseConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingResponse convertEntityToResponse(BuildingEntity entity) {
        BuildingResponse dto = modelMapper.map(entity, BuildingResponse.class);
        dto.setAddress(entity.getStreet() +", "+ entity.getWard() +", "+entity.getDistrict().getName());

        return dto;
    }

    public BuildingDTO convertResponseToDTO(BuildingResponse response) {
        BuildingDTO dto = modelMapper.map(response, BuildingDTO.class);
        return dto;
    }
}
