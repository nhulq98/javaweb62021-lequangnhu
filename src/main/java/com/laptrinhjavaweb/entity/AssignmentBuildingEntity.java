package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "assignmentbuilding")
public class AssignmentBuildingEntity extends BaseEntity {

    @Column(name = "staffid", nullable = false)
    private Long staffId;

    @Column(name = "buildingid", nullable = false)
    private Long buildingId;

    //relationship
    @ManyToOne(fetch = FetchType.LAZY)// khai báo kiểu quan hệ
    @JoinColumn(name = "buildingid", nullable = false, referencedColumnName="id", insertable=false, updatable=false)// Khai báo TÊN khóa ngoại của bảng này sẽ liên kết với đối tượng bên dưới
    private BuildingEntity building = new BuildingEntity(); // khai báo đối tượng quan hệ

    @ManyToOne(fetch = FetchType.LAZY)// khai báo kiểu quan hệ
    @JoinColumn(name = "staffid", nullable = false, referencedColumnName="id", insertable=false, updatable=false)// Khai báo TÊN khóa ngoại của bảng này sẽ liên kết với đối tượng bên dưới
    private UserEntity user = new UserEntity(); // khai báo đối tượng quan hệ

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

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

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }
}
