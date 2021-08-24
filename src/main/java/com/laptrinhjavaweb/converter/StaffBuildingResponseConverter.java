package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaffBuildingResponseConverter {

    @Autowired
    private ModelMapper modelMapper;

    public StaffBuildingResponse convertDTOToResponse (UserDTO dto){
        StaffBuildingResponse result = modelMapper.map(dto, StaffBuildingResponse.class);
        return result;
    }

    public StaffBuildingResponse convertEntityToResponse (UserEntity entity){
        StaffBuildingResponse result = modelMapper.map(entity, StaffBuildingResponse.class);
        return result;
    }
}
