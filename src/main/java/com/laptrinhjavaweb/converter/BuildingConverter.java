package com.laptrinhjavaweb.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.builder.BuildingSearch;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
//@Qualifier("BuildingConverter") ==> the same with component("BuildingConverter")
public class BuildingConverter extends AbstractConverter<BuildingDTO, BuildingEntity> {

    @Override
    public BuildingDTO convertEntityToDTO(BuildingEntity entity) {
        BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);

        dto.setAddress(entity.getStreet() + ", " + entity.getWard() + ", " + DistrictsEnum.valueOf(entity.getDistrict()).getDistrictValue());

        // convert List<RentAreaEntity> to rentAreaStrs with format: 200,300,400...
        List<RentAreaEntity> rentAreas = entity.getRentAreas();
        List<String> rentAreaStrs = new ArrayList<>();
        for (RentAreaEntity item : rentAreas) {
            rentAreaStrs.add(String.valueOf(item.getValue()));
        }
        dto.setRentAreas(String.join(", ", rentAreaStrs));

        return dto;
    }

    @Override
    public BuildingEntity convertDTOToEntity(BuildingDTO buildingDTO) {
        BuildingEntity entity = modelMapper.map(buildingDTO, BuildingEntity.class);

        return entity;
    }

    public BuildingSearch convertMapToBuider(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        BuildingSearch.Builder result = mapper.convertValue(map, BuildingSearch.Builder.class);
        return new BuildingSearch(result);
    }

    public Map<String, Object> convertRequestToMap(BuildingRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = mapper.convertValue(request, Map.class);
        return result;
    }
}
