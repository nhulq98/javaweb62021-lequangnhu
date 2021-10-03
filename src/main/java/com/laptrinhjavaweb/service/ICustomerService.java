package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.response.CustomerResponse;

import java.util.List;
import java.util.Map;

public interface ICustomerService {
    //get Data
    List<CustomerResponse> findByCondition(Map<String, Object> requestParam);

    //Change Data
    void save(CustomerDTO newCustomer);

    void deleteById(Long id);
}
