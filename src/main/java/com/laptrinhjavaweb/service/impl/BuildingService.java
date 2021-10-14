package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service // nó hiểu đây là 1 module. và bảo IoC container tạo một object duy nhất cho nó (singleton pattern)
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Override
    public List<DistrictResponse> getDistricts() {
        try {
            List<DistrictResponse> listDistrict = new ArrayList<>();
            for (DistrictsEnum item : DistrictsEnum.values()) {
                DistrictResponse districtResponse = new DistrictResponse();
                districtResponse.setCode(item.toString());
                districtResponse.setValue(item.getDistrictValue());

                listDistrict.add(districtResponse);
            }
            return listDistrict;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<TypesResponse> getBuildingTypes() {
        try {
            List<TypesResponse> listTypes = new ArrayList<>();
            for (BuildingTypesEnum item : BuildingTypesEnum.values()) {
                TypesResponse typesResponse = new TypesResponse();
                typesResponse.setCode(item.toString());
                typesResponse.setValue(item.getBuildingTypeValue());

                listTypes.add(typesResponse);
            }
            return listTypes;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<TypesResponse> getBuildingTypes(List<String> rentypes) {
        try {
            List<TypesResponse> typeList = getBuildingTypes();
            if (rentypes == null) return typeList;
            for (TypesResponse item : typeList) {
                for (String item2 : rentypes) {
                    if (item.getCode().equals(item2)) {
                        item.setChecked("checked");
                    }
                }
            }
            return typeList;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public BuildingDTO getOne(Long id) {
        try {
            BuildingEntity entity = buildingRepository.findById(id);
            return buildingConverter.convertEntityToDTO(entity);
        } catch (RuntimeException e) {
            return new BuildingDTO();
        }
    }

    @Override
    public List<BuildingResponse> findByCondition(Map<String, Object> requestParam) {
        try {
            List<BuildingEntity> entities = buildingRepository.findByCondition(requestParam);
            List<BuildingResponse> result = entities.stream().map(BuildingResponse::new)
                    .collect(Collectors.toList());

            return result;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void updateOrSave(BuildingDTO newBuilding) throws RuntimeException {
        // apply cascade
        buildingRepository.save(buildingConverter.convertDTOToEntity(newBuilding));
    }

    @Override
    public void deleteById(Long id) {
        buildingRepository.delete(id);
    }
}
