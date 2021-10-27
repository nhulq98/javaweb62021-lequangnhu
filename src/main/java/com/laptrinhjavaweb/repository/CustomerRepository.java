package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends CustomerRepositoryCustom, JpaRepository<CustomerEntity, Long> {
    void deleteByIdIn(List<Long> ids);

    Long countByIdIn(List<Long> ids);
}
