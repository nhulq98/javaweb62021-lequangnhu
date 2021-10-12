package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.response.StaffBuildingResponse;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.entity.view.StaffEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentCustomerService implements IAssignmentCustomerService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<StaffBuildingResponse> findAllStaffsByCusId(Long customerId) {
//        List<UserEntity> Allstaffs = userRepository.getStaffs();
//        List<StaffBuildingResponse> result = Allstaffs.stream()
//                .map(StaffBuildingResponse::new).collect(Collectors.toList());
//
//        return result;
        return new ArrayList<StaffBuildingResponse>();
    }
}
