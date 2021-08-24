package com.laptrinhjavaweb.dto.response;

public class BuildingResponse extends BaseResponse{
	private String name;
	private String address;
	private String managerName;
	private String managerPhone;
	private Integer floorArea;
	private Integer rentPrice;
	private Integer serviceFee;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
	public Integer getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}
	public Integer getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(Integer serviceFee) {
		this.serviceFee = serviceFee;
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
