package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.view.StaffEntity;

import java.util.List;

public interface AssignmentCustomerRepositoryCustom {
    List<StaffEntity> findAllCustom(Long buildingId);
}
