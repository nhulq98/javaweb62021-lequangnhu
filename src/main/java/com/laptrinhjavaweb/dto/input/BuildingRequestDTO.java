package com.laptrinhjavaweb.dto.input;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	private ArrayList<String> listType;

}
