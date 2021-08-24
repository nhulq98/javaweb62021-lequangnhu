package com.laptrinhjavaweb.entity;

import javax.persistence.Column;

public class AssignmentBuildingEntity extends BaseEntity {

    @Column(name = "staffid")
    private Long staffId;

    @Column(name = "buildingid")
    private Long buildingId;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
