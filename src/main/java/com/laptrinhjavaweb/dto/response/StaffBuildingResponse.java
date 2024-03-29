package com.laptrinhjavaweb.dto.response;

import com.laptrinhjavaweb.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffBuildingResponse extends BaseResponse {
    private String checked = "";
    private String fullName;

    public StaffBuildingResponse(){};

    public StaffBuildingResponse(Long staffId, String fullName, String checked) {
        this.id = staffId;
        this.fullName = fullName;
        this.checked = checked;
    }

    public StaffBuildingResponse(UserEntity entity) {
        this.id = entity.getId();
        this.fullName = entity.getFullName();
        this.checked = "NULL";
    }
}
