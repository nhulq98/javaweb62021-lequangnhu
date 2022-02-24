package com.laptrinhjavaweb.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseResponse {
    protected Long id;
    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;
    private int maxPageItems = 10;
    private int page = 1;
    private int totalItems = 0;
    private String tableId = "tableList";
    private Integer limit;
    private Integer totalPage;
    private Integer totalItem;
    private String searchValue;
}
