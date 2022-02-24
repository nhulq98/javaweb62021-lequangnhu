package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.CustomerSearch;
import com.laptrinhjavaweb.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepositoryCustom {

    // Scope for get data
    List<CustomerEntity> findByCondition(CustomerSearch customerSearch);
}
