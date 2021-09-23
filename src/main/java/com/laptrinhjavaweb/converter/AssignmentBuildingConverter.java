package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component// là bảo IoC container tạo một object duy nhất (singleton)
public class AssignmentBuildingConverter extends AbstractConverter<AssignmentBuildingDTO, AssignmentBuildingEntity> {
    /**
     * Reverts {@code destination} to an instance of type {@code S}.
     *
     * @param entity
     */
    @Override
    public AssignmentBuildingDTO convertEntityToDTO(AssignmentBuildingEntity entity) {
        return null;
    }

    /**
     * Converts {@code source} to an instance of type {@code D}.
     *
     * @param assignmentBuildingDTO
     */
    @Override
    public AssignmentBuildingEntity convertDTOToEntity(AssignmentBuildingDTO assignmentBuildingDTO) {
        return null;
    }

    public StaffBuildingResponse convertEntityToResponse(AssignmentBuildingEntity entity) {
        StaffBuildingResponse dto = new StaffBuildingResponse();
        UserEntity userEntity = entity.getUser();
        dto.setId(userEntity.getId());
        dto.setFullName(userEntity.getFullName());
        return dto;
    }
}
