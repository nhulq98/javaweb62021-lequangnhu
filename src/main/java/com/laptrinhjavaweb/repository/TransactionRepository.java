package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {

    @Query(value = "select T.* from transaction T join transactiontype TT on T.typeid = TT.id where TT.customerid = ?1", nativeQuery = true)
    List<TransactionEntity> getTransactionByCustomerId(Long customerId);
}
