package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.request.StaffRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.view.StaffEntity;
import com.laptrinhjavaweb.repository.AssignmentCustomerRepository;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        List<StaffEntity> staffsAll = repository.findAllCustom(customerId);
        List<StaffBuildingResponse> result = staffsAll.stream()
                .map(StaffBuildingResponse::new).collect(Collectors.toList());

        return result;
    }

    @Override
    @Transactional
    public void updateAssignment(StaffRequest request) {
        // apply cascade
        CustomerEntity customerEntity = customerRepository.findOne(request.getId());
        customerEntity.setStaffs(userRepository.findByIdIn(request.getStaffIds()));

        customerRepository.save(customerEntity);
    }
}
