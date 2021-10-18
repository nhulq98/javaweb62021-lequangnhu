package com.laptrinhjavaweb.dto.request;

import com.laptrinhjavaweb.dto.AbstractDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuildingRequest extends AbstractDTO<BuildingResponse> {
    private String name;
    private String street;
    private String ward;
    private String district;
    private Integer floorArea;
    private Integer numberOfBasement;
    private String direction;
    private List<String> rentTypes;
    private String level;
    private Integer rentPriceFrom;
    private Integer rentPriceTo;
    private Integer rentAreaFrom;
    private Integer rentAreaTo;
    private String managerName;
    private String managerPhone;
    private Long staffId;
    private Long userId;
    private List<Long> ids;
}
