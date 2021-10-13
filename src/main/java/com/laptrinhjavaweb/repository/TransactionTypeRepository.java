package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.TransactionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionTypeEntity, Long> {
}
