package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.dto.response.DistrictResponse;
import com.laptrinhjavaweb.dto.response.TypesResponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // declare module. and said IoC container you have to create 1 only object for this module (singleton pattern)
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

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
    public List<TypesResponse> getBuildingTypes(List<String> rentypes) {
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
    }

    @Override
    public BuildingDTO getOne(Long id) {
        BuildingEntity entity = buildingRepository.findOne(id);

        Optional.ofNullable(entity)
                .orElseThrow(() -> new NotFoundException(MessageUtils.getMSNotFound("building")));

        return buildingConverter.convertEntityToDTO(entity);
    }

    @Override
    public List<BuildingResponse> findByCondition(Map<String, Object> requestParam) {
        List<BuildingEntity> entities = buildingRepository.findByCondition(requestParam);

        if (entities == null || entities.size() == 0) {
            throw new NotFoundException(MessageUtils.getMSNotFound("building"));
        }

        List<BuildingResponse> result = entities.stream().map(BuildingResponse::new)
                .collect(Collectors.toList());

        return result;
    }

    @Override
    @Transactional
    public void updateOrSave(BuildingDTO newBuilding) throws RuntimeException {
        // apply cascade
        Long buildingId = newBuilding.getId();

        if (buildingId != null && buildingId > 0) {
            Optional.ofNullable(buildingRepository.getOne(buildingId))
                    .orElseThrow(() -> new NotFoundException(MessageUtils.getMSNotFound("building")));
        }

        buildingRepository.save(buildingConverter.convertDTOToEntity(newBuilding));
    }

    @Override
    public void deleteById(Long id) {
        buildingRepository.delete(id);
    }
}
