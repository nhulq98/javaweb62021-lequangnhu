package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.request.StaffRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import com.laptrinhjavaweb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentCustomerService implements IAssignmentCustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<StaffBuildingResponse> findAllStaffsByCusId(Long customerId) {

        CustomerEntity customerEntity = Optional.ofNullable(customerRepository.findOne(customerId))
                .orElseThrow(() -> new NotFoundException("Customer not found!"));

        List<UserEntity> customerManagementStaffs = customerEntity.getStaffs();

        return Utils.setCheckedForStaffs(customerManagementStaffs);
    }

    @Override
    @Transactional
    public void updateCustomerManagementStaffs(StaffRequest request) {
        // apply cascade
        LinkedList<Long> staffIds = request.getStaffIds();
        int size = userRepository.countByIdIn(staffIds);

        CustomerEntity customerEntity = Optional.ofNullable(customerRepository.findOne(request.getId()))
                .orElseThrow(() -> new NotFoundException("customer not found!"));

        if(size != staffIds.size()){
            throw new NotFoundException("staff not found!");
        }

        customerEntity.setStaffs(userRepository.findByIdIn(staffIds));

        customerRepository.save(customerEntity);
    }
}
