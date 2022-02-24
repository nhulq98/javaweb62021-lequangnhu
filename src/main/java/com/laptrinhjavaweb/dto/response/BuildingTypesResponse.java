package com.laptrinhjavaweb.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingTypesResponse extends BaseResponse {
    private String checked = "";
    private String code;
    private String value;

//    public BuildingTypesResponse() {}

//    public BuildingTypesResponse(StaffEntity entity) {
//        this.id = entity.getId();
//        this.fullName = entity.getFullName();
//        this.checked = entity.getChecked();
//    }
}
