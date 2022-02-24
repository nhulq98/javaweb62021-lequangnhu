package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearch;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        if(id == null) throw new NullPointerException("BuildingID is NULL!");

        BuildingEntity entity = Optional.ofNullable(buildingRepository.findOne(id))
                .orElseThrow(()-> new NotFoundException("Building not found!"));

        return buildingConverter.convertEntityToDTO(entity);
    }

    @Override
    public List<BuildingResponse> findByCondition(Map<String, Object> requestParam) {
        BuildingSearch searchBuilder = buildingConverter.convertMapToBuider(requestParam);

        List<BuildingEntity> entities = Optional.ofNullable(buildingRepository.findByCondition(searchBuilder))
                .orElseThrow(() -> new NotFoundException("Buildings not found"));

        List<BuildingResponse> result = new ArrayList<>();
        entities.forEach(item-> result.add(buildingConverter.convertEntityToResponse(item))  );

        return result;
    }

    @Override
    @Transactional
    public void updateOrSave(BuildingDTO newBuilding) throws RuntimeException {
        // apply cascade
        Long buildingId = newBuilding.getId();

        BuildingEntity buildingEntity = buildingConverter.convertDTOToEntity(newBuilding);

        if (buildingId != null && buildingId > 0) {
            BuildingEntity entity = Optional.ofNullable(buildingRepository.getOne(buildingId))
                    .orElseThrow(()-> new NotFoundException("Building not FOUND!"));

            buildingEntity.setStaffs(entity.getStaffs());
        }
        buildingRepository.save(buildingEntity);
    }

    @Override
    @Transactional
    public void deleteByListId(List<Long> ids) {
        int size = buildingRepository.countByIdIn(ids);
        if(size != ids.size()){
            throw new NotFoundException("Building not found!");
        }

        buildingRepository.deleteByIdIn(ids);
    }
}
