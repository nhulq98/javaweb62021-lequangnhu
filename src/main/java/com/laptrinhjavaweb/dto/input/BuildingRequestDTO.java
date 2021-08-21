package com.laptrinhjavaweb.dto.input;

public class BuildingRequestDTO {
	private String name;
	private String street;
	private String ward;
	private Long districtID;
	private Long floorArea;
	private Long numberOfBasement;
	private String direction;
	private String level;
	private Long rentPriceFrom;
	private Long rentPriceTo;
	private Long rentEreaFrom;
	private Long rentEreaTo;
	private String managerName;
	private String managerPhone;
	private Long userID;
	private String[] buildingTypeList;
	private String listType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}

	public Long getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
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

	public Long getRentPriceFrom() {
		return rentPriceFrom;
	}

	public void setRentPriceFrom(Long rentPriceFrom) {
		this.rentPriceFrom = rentPriceFrom;
	}

	public Long getRentPriceTo() {
		return rentPriceTo;
	}

	public void setRentPriceTo(Long rentPriceTo) {
		this.rentPriceTo = rentPriceTo;
	}

	public Long getRentEreaFrom() {
		return rentEreaFrom;
	}

	public void setRentEreaFrom(Long rentEreaFrom) {
		this.rentEreaFrom = rentEreaFrom;
	}

	public Long getRentEreaTo() {
		return rentEreaTo;
	}

	public void setRentEreaTo(Long rentEreaTo) {
		this.rentEreaTo = rentEreaTo;
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

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String[] getBuildingTypeList() {
		return buildingTypeList;
	}

	public void setBuildingTypeList(String[] buildingTypeList) {
		this.buildingTypeList = buildingTypeList;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}
}
