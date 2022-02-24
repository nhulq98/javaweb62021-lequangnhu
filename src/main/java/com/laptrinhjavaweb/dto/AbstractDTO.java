package com.laptrinhjavaweb.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AbstractDTO<T> implements Serializable {
    private Long id;
    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;
    private int maxPageItems = 10;
    private int page = 1;
    private List<T> listResult = new ArrayList<>();
    private int totalItems = 0;
    private String tableId = "tableList";
    private Integer limit;
    private Integer totalPage;
    private Integer totalItem;
    private String searchValue;


    // finalize method is called on object once
    // before garbage collecting it
    @Override
    protected void finalize() {
        id = null;
        createdDate = null;
        createdBy = null;
        modifiedDate = null;
        modifiedBy = null;
        maxPageItems = 0;
        page = 0;
        listResult = null;
        totalItems = 0;
        tableId = null;
        limit = null;
        totalPage = null;
        totalItem = null;
        searchValue = null;
    }
}
