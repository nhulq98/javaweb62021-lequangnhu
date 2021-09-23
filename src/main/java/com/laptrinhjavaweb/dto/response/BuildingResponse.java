package com.laptrinhjavaweb.dto.response;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingResponse extends BaseResponse {
    private String name;
    private String address;
    private String managerName;
    private String managerPhone;
    private Integer floorArea;
    private Integer rentPrice;
    private String serviceFee;

    public BuildingResponse(BuildingEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getStreet() + ", " + entity.getWard() + ", " + DistrictsEnum.valueOf(entity.getDistrict()).getDistrictValue();
        this.managerName = entity.getManagerName();
        this.managerPhone = entity.getManagerPhone();
        this.floorArea = entity.getFloorArea();
        this.rentPrice = entity.getRentPrice();
        this.serviceFee = entity.getServiceFee();
    }
}
