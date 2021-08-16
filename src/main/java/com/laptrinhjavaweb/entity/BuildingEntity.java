package com.laptrinhjavaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {

    @Column(name = "name")
    private String name;
    
    @Column(name = "numberofbasement")
    private Integer numberOfBasement;
	
//    @Column(name = "street")
//    private String street;
//    
//    @Column(name = "ward")
//	private String ward;
//    
//    @Column(name = "districtID")
//	private Long districtID;
//    
//    
//    @Column(name = "floorarea")
//    private Long floorArea;
//	
//    @Column(name = "rentPrice")
//	private Long rentPrice;
//	
//    @Column(name = "managerName")
//	private String managerName;
//    
//    @Column(name = "managerPhone")
//	private String managerPhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

//	public String getStreet() {
//		return street;
//	}
//
//	public void setStreet(String street) {
//		this.street = street;
//	}
//
//	public String getWard() {
//		return ward;
//	}
//
//	public void setWard(String ward) {
//		this.ward = ward;
//	}
//
//	public Long getDistrictID() {
//		return districtID;
//	}
//
//	public void setDistrictID(Long districtID) {
//		this.districtID = districtID;
//	}
//
//	public String getManagerName() {
//		return managerName;
//	}
//
//	public void setManagerName(String managerName) {
//		this.managerName = managerName;
//	}
//
//	public String getManagerPhone() {
//		return managerPhone;
//	}
//
//	public void setManagerPhone(String managerPhone) {
//		this.managerPhone = managerPhone;
//	}

}
