package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.laptrinhjavaweb.dto.output.BuildingResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

public class BuildingResponseConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingResponseDTO convertToDTO(BuildingEntity entity) {
		//error at this line
		BuildingResponseDTO buildingResponseDTO = modelMapper.map(entity, BuildingResponseDTO.class);
		buildingResponseDTO.setAddress(buildingResponseDTO.getStreet() +", "+ buildingResponseDTO.getWard() +", "+buildingResponseDTO.getDirection());
		return buildingResponseDTO;
	}
	
	public BuildingEntity convertToEntity(BuildingResponseDTO buildingResponseDTO) {
		BuildingEntity entity = modelMapper.map(buildingResponseDTO, BuildingEntity.class);
		return entity;
	}
}
