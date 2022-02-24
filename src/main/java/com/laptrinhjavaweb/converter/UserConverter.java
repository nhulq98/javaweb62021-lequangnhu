package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConverter<UserDTO, UserEntity> {
    /**
     * Reverts {@code destination} to an instance of type {@code S}.
     *
     * @param entity
     */
    @Override
    public UserDTO convertEntityToDTO(UserEntity entity) {
        UserDTO dto = modelMapper.map(entity, UserDTO.class);
        return dto;
    }

    /**
     * Converts {@code source} to an instance of type {@code D}.
     *
     * @param userDTO
     */
    @Override
    public UserEntity convertDTOToEntity(UserDTO userDTO) {
        UserEntity entity = modelMapper.map(userDTO, UserEntity.class);
        return entity;
    }
}
