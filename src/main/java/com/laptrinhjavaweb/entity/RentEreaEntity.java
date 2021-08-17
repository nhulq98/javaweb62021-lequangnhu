package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "rentarea")
public class RentEreaEntity extends BaseEntity{

    @Column(name = "value")
    private Integer value;

//    @ManyToOne
//    @JoinColumn(name = "buildingid", nullable = false)
//    private BuildingEntity building;

//    public BuildingEntity getBuilding() {
//        return building;
//    }
//
//    public void setBuilding(BuildingEntity building) {
//        this.building = building;
//    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
