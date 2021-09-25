package com.laptrinhjavaweb.dto.response;

import com.laptrinhjavaweb.entity.view.StaffEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffBuildingResponse extends BaseResponse {
    private String checked = "";
    private String fullName;
    
    public StaffBuildingResponse() {}
    
    public StaffBuildingResponse(StaffEntity entity) {
    	this.id = entity.getId();
    	this.fullName = entity.getFullName();
    	this.checked = entity.getChecked();
    }
}
