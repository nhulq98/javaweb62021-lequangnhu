package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.AssignmentCustomerEntity;
import com.laptrinhjavaweb.repository.custom.AssignmentCustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentCustomerRepository extends AssignmentCustomerRepositoryCustom, JpaRepository<AssignmentCustomerEntity, Long> {
}
