package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.input.BuildingRequestDTO;
import com.laptrinhjavaweb.dto.output.BuildingResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.jdbc.impl.BuildingJDBCImpl;
import com.laptrinhjavaweb.service.IBuildingService;

@Service
public class BuildingService implements IBuildingService {

	@Autowired
	private BuildingConverter buildingConverter;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Override
	public Map<String, String> getDistricts() {
		Map<String, String> districts = new HashMap<>();
		for (DistrictsEnum item: DistrictsEnum.values()) {
			districts.put(item.toString(), item.getDistrictValue());
		}
		return districts;
	}

	@Override
	public Map<String, String> getBuildingTypes() {
		Map<String, String> buildingTypes = new HashMap<>();
		for (BuildingTypesEnum item: BuildingTypesEnum.values()) {
			buildingTypes.put(item.toString(), item.getBuildingTypeValue());
		}
		return buildingTypes;
	}

	@Override
	@Transactional
	public BuildingDTO save(BuildingDTO newBuilding) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(newBuilding);
		return buildingConverter.convertToDTO(buildingRepository.save(buildingEntity));
	}
	
	@Override
	public List<BuildingResponseDTO> findByCondition(BuildingRequestDTO buildingRequest) {
		BuildingJDBCImpl buildingimpl = new BuildingJDBCImpl();
		List<BuildingResponseDTO> result = new ArrayList<>();
		BuildingConverter converter = new BuildingConverter();
		
		// call repo 
		for(BuildingEntity buildingEntity: buildingimpl.findByCondition(buildingRequest)){
			// convert entity to dto
			BuildingDTO buildingDTO = converter.convertToDTO(buildingEntity);
			BuildingResponseDTO ResponseDTO = new BuildingResponseDTO();
			ResponseDTO.setName(buildingDTO.getName());
			ResponseDTO.setAddress(buildingDTO.getAddress());
			ResponseDTO.setManagerName(buildingDTO.getManagerName());
			ResponseDTO.setManagerPhone(buildingDTO.getName());
			ResponseDTO.setFloorArea(buildingDTO.getFloorArea());
			ResponseDTO.setRentPrice(buildingDTO.getRentPrice());
			ResponseDTO.setServiceFee(buildingDTO.getServiceFee());
			result.add(ResponseDTO);
		}
		return result;
	}

}
