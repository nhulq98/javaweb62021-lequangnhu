package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.entity.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
//@Qualifier("BuildingConverter") ==> the same with component("BuildingConverter")
public class RoleConverter extends AbstractConverter<RoleDTO, RoleEntity> {

    /**
     * Reverts {@code destination} to an instance of type {@code S}.
     *
     * @param roleEntity
     */
    @Override
    public RoleDTO convertEntityToDTO(RoleEntity roleEntity) {
        Optional.ofNullable(roleEntity)
                .orElseThrow(() -> new NullPointerException("RoleEntity null!"));

        RoleDTO dto = modelMapper.map(roleEntity, RoleDTO.class);
        return dto;
    }

    /**
     * Converts {@code source} to an instance of type {@code D}.
     *
     * @param roleDTO
     */
    @Override
    public RoleEntity convertDTOToEntity(RoleDTO roleDTO) {
        Optional.ofNullable(roleDTO)
                .orElseThrow(() -> new NullPointerException("RoleDTO null!"));

        RoleEntity entity = modelMapper.map(roleDTO, RoleEntity.class);
        return entity;
    }
}
