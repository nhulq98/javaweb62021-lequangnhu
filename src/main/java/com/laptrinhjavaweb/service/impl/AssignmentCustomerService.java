package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.request.StaffRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.entity.view.StaffEntity;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.AssignmentCustomerRepository;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentCustomerService implements IAssignmentCustomerService {

    @Autowired
    private AssignmentCustomerRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<StaffBuildingResponse> findAllStaffsByCusId(Long customerId) {
        List<StaffEntity> staffsAll = Optional.ofNullable(repository.findAllCustom(customerId))
                .orElseThrow(() -> new NotFoundException("staff not found!"));

        List<StaffBuildingResponse> result = staffsAll.stream()
                .map(StaffBuildingResponse::new).collect(Collectors.toList());

        return result;
    }

    private void setStaffsForCustomer(CustomerEntity customerEntity, LinkedList<Long> staffIds){
        int size = customerRepository.countByIdIn(staffIds);
        if(size != staffIds.size()){
            throw new NotFoundException("Staffs not found!");
        }

        List<UserEntity> staffs = userRepository.findByIdIn(staffIds);
        customerEntity.setStaffs(staffs);
    }

    @Override
    @Transactional
    public void updateCustomerManagementStaffs(StaffRequest request) {
        // apply cascade
        Long cusId = request.getId();
        LinkedList<Long> staffIds = request.getStaffIds();

        CustomerEntity customerEntity = Optional.ofNullable(customerRepository.findOne(cusId))
                    .orElseThrow(()-> new NotFoundException("customer not found!"));

        setStaffsForCustomer(customerEntity, staffIds);

        customerRepository.save(customerEntity);
    }
}
