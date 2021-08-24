package com.laptrinhjavaweb.dto.response;

public class StaffBuildingResponse extends BaseResponse {
    private String checked = "";
    private String fullName;

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
