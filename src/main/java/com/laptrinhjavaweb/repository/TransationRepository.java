package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransationRepository extends JpaRepository<TransactionEntity, Long> {
}
