package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.dto.response.CustomerResponse;

import java.util.List;

public interface ICustomerService {
    //get Data
    List<CustomerResponse> findByCondition(CustomerRequest customerSearchModel);

    //Change Data
    void save(CustomerDTO newCustomer);

    void deleteById(Long id);
}
