package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.request.StaffRequest;
import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.AssignmentCustomerRepository;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentCustomerService implements IAssignmentCustomerService {

    @Autowired
    private AssignmentCustomerRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    private List<StaffBuildingResponse> setCheckedForStaffs(CustomerEntity customerEntity) {

        List<UserEntity> customerManagementStaffs = customerEntity.getStaffs();
        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(SystemConstant.ACTIVE_STATUS, SystemConstant.ROLE_STAFF);
        List<StaffBuildingResponse> result = new ArrayList<>();

        allStaffs.forEach(
                item -> {
                    String checked = SystemConstant.EMPTY_STRING;
                    if (customerManagementStaffs.contains(item)) {
                        checked = "checked";
                    }
                    result.add(new StaffBuildingResponse(item.getId(), item.getFullName(), checked));
                }
        );
        return result;
    }

    @Override
    public List<StaffBuildingResponse> findAllStaffsByCusId(Long customerId) {

        CustomerEntity customerEntity = Optional.ofNullable(customerRepository.findOne(customerId))
                .orElseThrow(() -> new NotFoundException("Customer not found!"));

        return setCheckedForStaffs(customerEntity);
    }

    private void setStaffsForCustomer(CustomerEntity customerEntity, LinkedList<Long> staffIds) {
        int size = customerRepository.countByIdIn(staffIds);
        if (size != staffIds.size()) {
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
                .orElseThrow(() -> new NotFoundException("customer not found!"));

        setStaffsForCustomer(customerEntity, staffIds);

        customerRepository.save(customerEntity);
    }
}
