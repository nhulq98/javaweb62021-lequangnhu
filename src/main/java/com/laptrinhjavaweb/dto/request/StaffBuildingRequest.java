package com.laptrinhjavaweb.dto.request;

import java.util.LinkedList;
import java.util.List;

public class StaffBuildingRequest {
    private LinkedList<Long> staffIds;
    private Long buildingId;

    public LinkedList<Long> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(LinkedList<Long> staffIds) {
        this.staffIds = staffIds;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
