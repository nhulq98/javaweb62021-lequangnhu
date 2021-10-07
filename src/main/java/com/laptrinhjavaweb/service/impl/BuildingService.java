package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import com.laptrinhjavaweb.dto.response.DistrictResponse;
import com.laptrinhjavaweb.dto.response.TypesResponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IRentAreaService;
import com.laptrinhjavaweb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service // nó hiểu đây là 1 module. và bảo IoC container tạo một object duy nhất cho nó (singleton)
public class BuildingService implements IBuildingService {

    @Autowired// là tìm module tương ứng (tạo từ trước) và inject vào đó.
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private IRentAreaService IRentAreaService;


    @Autowired// là tìm module tương ứng (tạo từ trước) và inject vào đó.
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
        try {
            BuildingEntity entity = buildingRepository.findById(id);
            return buildingConverter.convertEntityToDTO(entity);
        } catch (RuntimeException e) {
            return new BuildingDTO();
        }
    }

    @Override
    public List<BuildingResponse> findByCondition(Map<String, Object> requestParam) {
        //mapper.
        List<BuildingEntity> entities = buildingRepository.findByCondition(requestParam);
        List<BuildingResponse> result = entities.stream().map(BuildingResponse::new)
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public void save(BuildingDTO newBuilding) {
        // add rentArea to Building

        // save building
        BuildingEntity entity = buildingRepository.save(buildingConverter.convertDTOToEntity(newBuilding));

        // save RentArea
        String[] rentAreaStrs = newBuilding.getRentAreas().split(",");
        for (String item : rentAreaStrs) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            if (item.trim().matches(SystemConstant.ISNUMBER)) {
                rentAreaEntity.setValue(Integer.parseInt(item.trim()));
                rentAreaEntity.setBuilding(entity);

                rentAreaRepository.save(rentAreaEntity);
            }
            Utils.customGC(rentAreaEntity);
        }
        Utils.customGC(entity);
    }

    @Override
    @Transactional
    public void update(BuildingDTO newBuilding) {
        buildingRepository.save(buildingConverter.convertDTOToEntity(newBuilding));

        IRentAreaService.updateRentArea(newBuilding);
}

    @Override
    public void deleteById(Long id) {
        buildingRepository.delete(id);
    }
}
