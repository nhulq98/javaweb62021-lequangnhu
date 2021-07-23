package com.laptrinhjavaweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingDTO extends AbstractDTO<BuildingDTO>{

	private Long id;
	private String name;
	private String street;
	private String ward;
	private Long districtID;
	private String district;
	private String structure;
	private Long numberOfBasement;
	private Long floorArea;
	private String direction;
	private String level;
	private Long rentPrice;
	private String rentPriceDescription;
	private String serviceFee;
	private String carFee;
	private String motorbikeFee;
	private String overtimeFee;
	private String waterFee;
	private String electricityFee;
	private String deposit;
	private String payment;
	private String rentTime;
	private String decoratorTime;
	private String brokerageFee;
	private String note;
	private String linkofbuilding;
	private String map;
	private String image;
	private String managerName;
	private String managerPhone;
	private String address;

//	private String name;
//	private String numberOfBasement;
//	private String buildingArea;
//	private String street;
//	private String ward;
//	private String district;
//	private String structure;
//	private Integer costRent;
//	private String costDescription;
//	private String serviceCost;
//	private String carCost;
//	private String motorbikeCost;
//	private String overtimeCost;
//	private String[] buildingTypes = new String[] {};
//	private String costRentFrom;
//	private String costRentTo;
//	private String areaRentFrom;
//	private String areaRentTo;
//	private String staffId;
//	private String electricityCost;
//	private String deposit;
//	private String payment;
//	private String timeRent;
//	private String timeDecorator;
//	private String managerName;
//	private String managerPhone;
//	private String areaRent;
//	private String address;
//	private String type;

}
