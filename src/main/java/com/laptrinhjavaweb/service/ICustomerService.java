package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.dto.response.CustomerResponse;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;

import java.util.List;
import java.util.Map;

public interface ICustomerService {
    //get Data
    List<CustomerResponse> findByCondition(CustomerRequest customerSearchModel);
    List<StaffBuildingResponse> getStaffsAssignment(Long buildingId);

    //Change Data
    void save(CustomerDTO newCustomer);

    void deleteById(Long id);
}
