package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "renttype")
public class RentTypeEntity extends OnlyIDEntity{

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    // relationship
    @OneToMany(mappedBy = "rentType", fetch = FetchType.LAZY)
    List<BuildingRentTypeEntity> buildingRentTypes = new ArrayList<>();

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<BuildingRentTypeEntity> getBuildingRentTypes() {
        return buildingRentTypes;
    }
    public void setBuildingRentTypes(List<BuildingRentTypeEntity> buildingRentTypes) {
        this.buildingRentTypes = buildingRentTypes;
    }
}
