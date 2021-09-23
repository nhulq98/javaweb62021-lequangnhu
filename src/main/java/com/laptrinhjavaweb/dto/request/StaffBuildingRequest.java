package com.laptrinhjavaweb.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class StaffBuildingRequest {
    private LinkedList<Long> staffIds;
    private Long buildingId;

}
