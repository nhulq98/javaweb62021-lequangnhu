package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.RentTypeDTO;
import com.laptrinhjavaweb.entity.RentTypeEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentTypeConverter {

    @Autowired
    private ModelMapper modelMapper;

    public RentTypeDTO convertToDTO(RentTypeEntity entity) {
        RentTypeDTO dto = modelMapper.map(entity, RentTypeDTO.class);
        return dto;
    }

    public RentTypeEntity convertToEntity(RentTypeDTO dto) {
        RentTypeEntity entity = modelMapper.map(dto, RentTypeEntity.class);
        return entity;
    }
}
