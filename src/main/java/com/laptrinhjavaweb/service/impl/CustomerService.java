package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.CustomerSearch;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.dto.response.CustomerResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerConverter converter;

    @Override
    public List<CustomerResponse> findByCondition(CustomerRequest customerSearchModel) {
        //mapper.
        // convert dto -> builder
        CustomerSearch builder = converter.convertDTOToBuilder(customerSearchModel);

        List<CustomerEntity> entities = customerRepository.findByCondition(builder);

        if(entities != null && entities.size() > 0){
            Optional.ofNullable(entities)
                    .orElseThrow(() -> new NotFoundException(MessageUtils.getMSNotFound("customer")));
        }
        List<CustomerResponse> result = new ArrayList<>();
        entities.forEach(item -> result.add(converter.convertEntityToResponse(item)));

        return result;
    }

    @Override
    @Transactional
    public void save(CustomerDTO customer) {
        Long cusId = customer.getId();

        if(cusId != null && cusId > 0){
            CustomerEntity entity = customerRepository.getOne(cusId);

            Optional.ofNullable(customerRepository.findOne(cusId))
                    .orElseThrow(()-> new NotFoundException(MessageUtils.getMSNotFound("customer")));

            CustomerEntity customerUpdate = converter.convertDTOToEntity(customer);
            customerUpdate.setStaffs(entity.getStaffs());

            customerRepository.save(customerUpdate);
        }else{
            customerRepository.save(converter.convertDTOToEntity(customer));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        Optional.ofNullable(customerRepository.findOne(id))
                .orElseThrow(()-> new NotFoundException(MessageUtils.getMSNotFound("customer")));

        customerRepository.delete(id);
    }

    @Override
    @Transactional
    public void deleteByListId(List<Long> ids) {
        customerRepository.deleteByIdIn(ids);
    }
}
