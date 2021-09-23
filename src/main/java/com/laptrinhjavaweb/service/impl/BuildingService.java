package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.DistrictConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.dto.response.DistrictResponse;
import com.laptrinhjavaweb.dto.response.TypesResponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service // nó hiểu đây là 1 module. và bảo IoC container tạo một object duy nhất cho nó (singleton)
public class BuildingService implements IBuildingService {

	@Autowired// là tìm module tương ứng (tạo từ trước) và inject vào đó.
	private BuildingRepository buildingRepository;

	@Autowired// là tìm module tương ứng (tạo từ trước) và inject vào đó.
	private BuildingConverter buildingConverter;

	@Autowired
	private DistrictConverter districtConverter;

	@Override
	public List<DistrictResponse> getDistricts() {
		List<DistrictResponse> listDistrict = new ArrayList<>();
		for (DistrictsEnum item : DistrictsEnum.values()) {
			DistrictResponse districtResponse = new DistrictResponse();
			districtResponse.setCode(item.toString());
			districtResponse.setValue(item.getDistrictValue());

			listDistrict.add(districtResponse);
		}
		return listDistrict;
	}

	@Override
	public List<TypesResponse> getBuildingTypes() {
		List<TypesResponse> listTypes = new ArrayList<>();
		for (BuildingTypesEnum item : BuildingTypesEnum.values()) {
			TypesResponse typesResponse = new TypesResponse();
			typesResponse.setCode(item.toString());
			typesResponse.setValue(item.getBuildingTypeValue());

			listTypes.add(typesResponse);
		}
		return listTypes;
	}

	@Override
	public BuildingDTO getOne(Long id) {
		try {
			BuildingEntity entity = buildingRepository.findById(id);
			return buildingConverter.convertEntityToDTO(entity);
		} catch (NoSuchElementException e) {
			return new BuildingDTO();
		}
	}

	@Override
	public List<BuildingResponse> findByCondition(Map<String, Object> requestParam) {
		//mapper.
		List<BuildingEntity> entities = buildingRepository.findByCondition(requestParam);
		List<BuildingResponse> result = entities.stream().map(BuildingResponse::new)
				.collect(Collectors.toList());

		//List<BuildingResponse> result = new ArrayList<>();
//		for (BuildingEntity entity : entities) {
//			BuildingDTO dto = buildingConverter.convertEntityToDTO(entity);
//			result.add(buildingConverter.convertSpecial(dto, new BuildingResponse()));
//		}
		return result;
	}

	@Override
	public BuildingDTO save(BuildingDTO newBuilding) {
		return buildingConverter.convertEntityToDTO(
				buildingRepository.save(
						buildingConverter.convertSpecial(newBuilding, new BuildingEntity())
				)
		);
	}

	@Override
	public void deleteById(Long id) {
		buildingRepository.delete(id);
	}

}
