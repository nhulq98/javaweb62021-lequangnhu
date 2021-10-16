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

import java.util.*;
import java.util.stream.Collectors;

@Component
//@Qualifier("BuildingConverter") ==> the same with component("BuildingConverter")
public class BuildingConverter extends AbstractConverter<BuildingDTO, BuildingEntity> {

    @Override
    public BuildingDTO convertEntityToDTO(BuildingEntity entity) {
        try {
            BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);

            dto.setAddress(entity.getStreet() + ", " + entity.getWard() + ", " + DistrictsEnum.valueOf(entity.getDistrict()).getDistrictValue());
            dto.setRentAreas(convertRentAreaListToStringList(entity.getRentAreas()));
            dto.setRentTypes(convertTypeStrToTypeList(entity.getRentType()));

            return dto;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new BuildingDTO();
        }
    }

    @Override
    public BuildingEntity convertDTOToEntity(BuildingDTO buildingDTO) {
        try {
            BuildingEntity entity = modelMapper.map(buildingDTO, BuildingEntity.class);

            entity.setRentAreas(convertRentAreaFormatStringToEntities(buildingDTO.getRentAreas(), entity));
            entity.setRentType(convertTypeListToString(buildingDTO.getRentTypes()));

            return entity;
        }catch (RuntimeException e){
            e.printStackTrace();
            return new BuildingEntity();
        }

    }

    public BuildingSearch convertMapToBuider(Map<String, Object> map) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            BuildingSearch.Builder result = mapper.convertValue(map, BuildingSearch.Builder.class);

            return new BuildingSearch(result);
        }catch (RuntimeException e){
            e.printStackTrace();
            return new BuildingSearch(new BuildingSearch.Builder());
        }
    }

    public Map<String, Object> convertRequestToMap(BuildingRequest request) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> result = mapper.convertValue(request, Map.class);
            return result;
        }catch (RuntimeException e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * convert with format:[tang_tret, nguyen_can] to String tang_tret, nguyen_can
     * @param typeList
     * @return
     */
    public String convertTypeListToString(List<String> typeList){
        if(typeList == null) throw  new NullPointerException(MessageConstant.NULL103);

        return typeList.stream().collect(Collectors.joining(","));
    }

    /**
     * Convert from rentType(String) with format: TANG_TRET,NGUYEN_CAN become to List<String>
     * @param typeStr
     * @return
     */
    public List<String> convertTypeStrToTypeList(String typeStr){
        if (typeStr == null) throw new NullPointerException(MessageConstant.NULL102);

        return Arrays.stream(typeStr.split(",")).collect(Collectors.toList());
    }

    /**
     * Convert from List<RentAreaEntity> to rentAreaStrs with format: 200,300,400...
     *  @param rentArea
     * @return
     */
    public String convertRentAreaListToStringList(List<RentAreaEntity> rentArea){
        if(rentArea == null) throw new NullPointerException(MessageConstant.NULL101);

        List<String> rentAreaList = rentArea.stream()
                .map(item -> String.valueOf(item.getValue()))
                .collect(Collectors.toList());

        return String.join(", ", rentAreaList);
    }

    /**
     * convert String with format: 100, 200 to List<RentAreaEntity>
     * @param rentAreas
     * @return List<RentAreaEntity>
     */
    public static List<RentAreaEntity> convertRentAreaFormatStringToEntities(String rentAreas, BuildingEntity entity){
        List<RentAreaEntity> rentAreaFromRequest = new LinkedList<>();
        if(rentAreas == null) throw new NullPointerException(MessageConstant.NULL104);

        String[] rentAreaStrs = rentAreas.split(",");
        for (String item : rentAreaStrs) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            if(item.trim().matches(SystemConstant.ISNUMBER)){// is number
                rentAreaEntity.setValue(Integer.parseInt(item.trim()));
                rentAreaEntity.setBuilding(entity);

                rentAreaFromRequest.add(rentAreaEntity);
            }
        }
        return rentAreaFromRequest;
    }
}
