package com.laptrinhjavaweb.dto;

public class RentEreaDTO extends AbstractDTO<RentEreaDTO>{
	private String value;
	private Long buildingid;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getBuildingid() {
		return buildingid;
	}

	public void setBuildingid(Long buildingid) {
		this.buildingid = buildingid;
	}
}
