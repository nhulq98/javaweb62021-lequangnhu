package com.laptrinhjavaweb.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.builder.BuildingSearch;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
//@Qualifier("BuildingConverter") ==> the same with component("BuildingConverter")
public class BuildingConverter extends AbstractConverter<BuildingDTO, BuildingEntity> {

    @Override
    public BuildingDTO convertEntityToDTO(BuildingEntity entity) {
        BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);
        try {
            dto.setAddress(entity.getStreet() + ", " + entity.getWard() + ", " + DistrictsEnum.valueOf(entity.getDistrict()).getDistrictValue());

            // Convert from List<RentAreaEntity> to rentAreaStrs with format: 200,300,400...
            List<RentAreaEntity> rentAreas = entity.getRentAreas();
            List<String> rentAreaStrs = rentAreas.stream().
                    map(item -> String.valueOf(item.getValue())).collect(Collectors.toList());
            dto.setRentAreas(String.join(", ", rentAreaStrs));

            //Convert from rentType(String) with format: TANG_TRET,NGUYEN_CAN become to List<String>
            if (entity.getRentType() == null) {
                throw new NullPointerException("Rentype of buildingName: "+entity.getName()+" is Null, please check it!");
            }
            List<String> types = Arrays.stream(entity.getRentType().split(",")).collect(Collectors.toList());
            dto.setRentTypes(types);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return dto;
    }

    @Override
    public BuildingEntity convertDTOToEntity(BuildingDTO buildingDTO) throws RuntimeException {
        BuildingEntity entity = modelMapper.map(buildingDTO, BuildingEntity.class);
        List<String> typeList = buildingDTO.getRentTypes();
        entity.setRentType(typeList.stream().collect(Collectors.joining(",")));
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
