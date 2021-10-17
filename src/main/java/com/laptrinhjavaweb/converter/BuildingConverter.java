package com.laptrinhjavaweb.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.builder.BuildingSearch;
import com.laptrinhjavaweb.constant.MessageConstant;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingRequest;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
//@Qualifier("BuildingConverter") ==> the same with component("BuildingConverter")
public class BuildingConverter extends AbstractConverter<BuildingDTO, BuildingEntity> {

    public StringBuilder getAddress(BuildingEntity entity) {
        StringBuilder address = new StringBuilder("");
        String district = DistrictsEnum.valueOf(entity.getDistrict()).getDistrictValue();

        if (!StringUtils.isEmpty(entity.getStreet()))
            address.append(entity.getStreet());
        if (!StringUtils.isEmpty(entity.getWard()))
            address.append("," + entity.getStreet());
        if (!StringUtils.isEmpty(district))
            address.append("," + district);

        return address;
    }

    @Override
    public BuildingDTO convertEntityToDTO(BuildingEntity entity) {
        Optional.ofNullable(entity)
                .orElseThrow(() -> new NullPointerException("BuildingEntity null!"));

        BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);

        dto.setAddress(getAddress(entity).toString());
        dto.setRentAreas(convertRentAreaListToStringList(entity.getRentAreas()));
        dto.setRentTypes(convertTypeStrToTypeList(entity.getRentType()));

        return dto;

    }

    @Override
    public BuildingEntity convertDTOToEntity(BuildingDTO buildingDTO) {
        Optional.ofNullable(buildingDTO)
                .orElseThrow(() -> new NullPointerException("BuildingDTO null!"));

        BuildingEntity entity = modelMapper.map(buildingDTO, BuildingEntity.class);

        entity.setRentAreas(convertRentAreaFormatStringToEntities(buildingDTO.getRentAreas(), entity));
        entity.setRentType(convertTypeListToString(buildingDTO.getRentTypes()));

        return entity;
    }

    public BuildingSearch convertMapToBuider(Map<String, Object> map) {
        Optional.ofNullable(map)
                .orElseThrow(() -> new NullPointerException("map null!"));

        ObjectMapper mapper = new ObjectMapper();
        BuildingSearch.Builder result = mapper.convertValue(map, BuildingSearch.Builder.class);

        return new BuildingSearch(result);
    }

    public Map<String, Object> convertRequestToMap(BuildingRequest request) {
        Optional.ofNullable(request)
                .orElseThrow(() -> new NullPointerException("BuildingRequest null!"));

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = mapper.convertValue(request, Map.class);

        return result;
    }

    /**
     * convert with format:[tang_tret, nguyen_can] to String tang_tret, nguyen_can
     *
     * @param typeList
     * @return
     */
    public String convertTypeListToString (List<String> typeList) {
        Optional.ofNullable(typeList)
                .orElseThrow(() -> new NullPointerException("typeList null!"));

        return typeList.stream().collect(Collectors.joining(","));
    }

    /**
     * Convert from rentType(String) with format: TANG_TRET,NGUYEN_CAN become to List<String>
     *
     * @param typeStr
     * @return
     */
    public List<String> convertTypeStrToTypeList(String typeStr) {
        Optional.ofNullable(typeStr)
                .orElseThrow(() -> new NullPointerException("typeStr null"));

        return Arrays.stream(typeStr.split(",")).collect(Collectors.toList());
    }

    /**
     * Convert from List<RentAreaEntity> to rentAreaStrs with format: 200,300,400...
     *
     * @param rentArea
     * @return
     */
    public String convertRentAreaListToStringList(List<RentAreaEntity> rentArea) {
        Optional.ofNullable(rentArea)
                .orElseThrow(() -> new NullPointerException("List rentArea null!"));

        List<String> rentAreaList = rentArea.stream()
                .map(item -> String.valueOf(item.getValue()))
                .collect(Collectors.toList());

        return String.join(", ", rentAreaList);
    }

    /**
     * convert String with format: 100, 200 to List<RentAreaEntity>
     *
     * @param rentAreas
     * @return List<RentAreaEntity>
     */
    public static List<RentAreaEntity> convertRentAreaFormatStringToEntities(String rentAreas, BuildingEntity entity) {
        Optional.ofNullable(rentAreas)
                .orElseThrow(() -> new NullPointerException(MessageConstant.NULL104));

        List<RentAreaEntity> rentAreaFromRequest = new LinkedList<>();

        String[] rentAreaStrs = rentAreas.split(",");
        for (String item : rentAreaStrs) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            if (item.trim().matches(SystemConstant.ISNUMBER)) {// is number
                rentAreaEntity.setValue(Integer.parseInt(item.trim()));
                rentAreaEntity.setBuilding(entity);

                rentAreaFromRequest.add(rentAreaEntity);
            }
        }

        return rentAreaFromRequest;
    }
}
