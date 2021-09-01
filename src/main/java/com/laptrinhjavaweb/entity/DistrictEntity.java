package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "district")
public class DistrictEntity extends OnlyIDEntity{

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    // Quan hệ 1-n với đối tượng ở dưới (building) (1 địa điểm có nhiều building)
    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY) // khai báo kiểu quan hệ và tên đối tượng sẽ liên kết với bảng này
    private List<BuildingEntity> buildings = new ArrayList<>();// khai báo đối tượng quan hệ

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

    public List<BuildingEntity> getBuildings() {
        return buildings;
    }
    public void setBuildings(List<BuildingEntity> buildings) {
        this.buildings = buildings;
    }

}
