package com.laptrinhjavaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;
    
    @Column(name = "ward")
	private String ward;
    
    @Column(name = "districtID")
	private Long districtID;
    
    @Column(name = "structure")
	private String structure;
    
    @Column(name = "numberofbasement")
    private Integer numberOfBasement;
	
    @Column(name = "floorarea")
    private Long floorArea;
	
    @Column(name = "direction")
	private String direction;
	
    @Column(name = "level")
    private String level;
	
    @Column(name = "rentPrice")
	private Long rentPrice;
	
    @Column(name = "rentPriceDescription")
    private String rentPriceDescription;
	
    @Column(name = "serviceFee")
    private String serviceFee;
	
    @Column(name = "carFee")
    private String carFee;
    
    @Column(name = "motorbikeFee")
	private String motorbikeFee;
    
    @Column(name = "overtimeFee")
	private String overtimeFee;
    
    @Column(name = "waterfee")
	private String waterFee;
    
    @Column(name = "electricityfee")
	private String electricityFee;
    
    @Column(name = "deposit")
	private String deposit;
    
    @Column(name = "payment")
	private String payment;
    
    @Column(name = "renttime")
	private String rentTime;
    
    @Column(name = "decorationtime")
	private String decoratorTime;
    
    @Column(name = "brokeragefee")
	private String brokerageFee;
    
    @Column(name = "note")
	private String note;
    
    @Column(name = "linkofbuilding")
	private String linkofbuilding;
    
    @Column(name = "map")
	private String map;
    
    @Column(name = "image")
	private String image;
    
    @Column(name = "managerName")
	private String managerName;
    
    @Column(name = "managerPhone")
	private String managerPhone;

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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public Long getDistrictID() {
		return districtID;
	}

	public void setDistrictID(Long districtID) {
		this.districtID = districtID;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public Long getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getRentPriceDescription() {
		return rentPriceDescription;
	}

	public void setRentPriceDescription(String rentPriceDescription) {
		this.rentPriceDescription = rentPriceDescription;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getCarFee() {
		return carFee;
	}

	public void setCarFee(String carFee) {
		this.carFee = carFee;
	}

	public String getMotorbikeFee() {
		return motorbikeFee;
	}

	public void setMotorbikeFee(String motorbikeFee) {
		this.motorbikeFee = motorbikeFee;
	}

	public String getOvertimeFee() {
		return overtimeFee;
	}

	public void setOvertimeFee(String overtimeFee) {
		this.overtimeFee = overtimeFee;
	}

	public String getWaterFee() {
		return waterFee;
	}

	public void setWaterFee(String waterFee) {
		this.waterFee = waterFee;
	}

	public String getElectricityFee() {
		return electricityFee;
	}

	public void setElectricityFee(String electricityFee) {
		this.electricityFee = electricityFee;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getRentTime() {
		return rentTime;
	}

	public void setRentTime(String rentTime) {
		this.rentTime = rentTime;
	}

	public String getDecoratorTime() {
		return decoratorTime;
	}

	public void setDecoratorTime(String decoratorTime) {
		this.decoratorTime = decoratorTime;
	}

	public String getBrokerageFee() {
		return brokerageFee;
	}

	public void setBrokerageFee(String brokerageFee) {
		this.brokerageFee = brokerageFee;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getLinkofbuilding() {
		return linkofbuilding;
	}

	public void setLinkofbuilding(String linkofbuilding) {
		this.linkofbuilding = linkofbuilding;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

}
