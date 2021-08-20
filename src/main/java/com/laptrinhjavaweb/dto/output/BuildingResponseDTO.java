package com.laptrinhjavaweb.dto.output;

import com.laptrinhjavaweb.dto.UserDTO;

import java.util.List;

public class BuildingResponseDTO{

	private Long id;
	private String name;
	private String address;
	private String managerName;
	private String managerPhone;
	private Long floorArea;
	private Long rentPrice;
	private Integer serviceFee;
	private List<UserDTO> staffs;

	public List<UserDTO> getStaffs() {
		return staffs;
	}
	public void setStaffs(List<UserDTO> staffs) {
		this.staffs = staffs;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Long getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}
	public Long getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Long rentPrice) {
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
