package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.DistrictDTO;
import com.laptrinhjavaweb.entity.DistrictEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistrictConverter {

    @Autowired
    private ModelMapper modelMapper;

    public DistrictDTO convertEntityToDTO(DistrictEntity entity) {
        DistrictDTO dto = modelMapper.map(entity, DistrictDTO.class);
        return dto;
    }

    public DistrictEntity convertDTOToEntity(DistrictDTO dto) {
        DistrictEntity entity = modelMapper.map(dto, DistrictEntity.class);
        return entity;
    }

}
