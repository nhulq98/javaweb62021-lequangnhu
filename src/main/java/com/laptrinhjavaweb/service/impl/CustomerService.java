package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.dto.response.CustomerResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository repository;

    @Autowired
    CustomerConverter converter;

    @Override
    public List<CustomerResponse> findByCondition(CustomerRequest customerSearchModel) {
        //mapper.
        List<CustomerEntity> entities = repository.findByCondition(customerSearchModel);
        List<CustomerResponse> result = new ArrayList<>();
        entities.forEach(item -> result.add(converter.convertEntityToResponse(item)));

        return result;
    }

    @Override
    public void save(CustomerDTO newCustomer) {
        repository.save(converter.convertDTOToEntity(newCustomer));
    }

    @Override
    public void deleteById(Long id) {
        repository.delete(id);
    }
}
