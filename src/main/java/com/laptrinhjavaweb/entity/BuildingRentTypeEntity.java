package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "buildingrenttype")
public class BuildingRentTypeEntity extends OnlyIDEntity{

	@Column(name = "buildingid", nullable = false)
	private Long buildingId;

	@Column(name = "renttypeid", nullable = false)
	private Long rentTypeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "buildingid", nullable = false, referencedColumnName="id", insertable=false, updatable=false)// Khai báo TÊN khóa ngoại của bảng này sẽ liên kết với đối tượng bên dưới
	private BuildingEntity building = new BuildingEntity(); // khai báo đối tượng quan hệ

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "renttypeid", nullable = false, referencedColumnName="id", insertable=false, updatable=false)// Khai báo TÊN khóa ngoại của bảng này sẽ liên kết với đối tượng bên dưới
	private RentTypeEntity rentType = new RentTypeEntity(); // khai báo đối tượng quan hệ

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public Long getRentTypeId() {
		return rentTypeId;
	}

	public void setRentTypeId(Long rentTypeId) {
		this.rentTypeId = rentTypeId;
	}

	public BuildingEntity getBuilding() {
		return building;
	}

	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}

	public RentTypeEntity getRentType() {
		return rentType;
	}

	public void setRentType(RentTypeEntity rentType) {
		this.rentType = rentType;
	}
}
