package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuildingConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO convertEntityToDTO(BuildingEntity entity) {
		BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);

		dto.setAddress(dto.getStreet() +", "+ dto.getWard() +", "+entity.getDistrict().getName());

		// convert List<RentAreaEntity> to rentAreaStrs with format: 200,300,400...
		List<RentAreaEntity> rentAreas = entity.getRentAreas();
		List<String> rentAreaStrs = new ArrayList<>();
		for(RentAreaEntity item: rentAreas){
			rentAreaStrs.add(String.valueOf(item.getValue()));
		}
		dto.setRentAreas(String.join(", ", rentAreaStrs));

		return dto;
	}
	
	public BuildingEntity convertDTOToEntity(BuildingDTO dto) {
		BuildingEntity entity = modelMapper.map(dto, BuildingEntity.class);

		// convert rentAreaStrs to List<RentAreaEntity>
		String[] rentAreaStrs = dto.getRentAreas().split(",");
		List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
		for(String item: rentAreaStrs){
			RentAreaEntity rentAreaEntity = new RentAreaEntity();
			rentAreaEntity.setValue(Integer.parseInt(item));
			rentAreaEntities.add(rentAreaEntity);
		}
		entity.setRentAreas(rentAreaEntities);

		return entity;
	}

}
