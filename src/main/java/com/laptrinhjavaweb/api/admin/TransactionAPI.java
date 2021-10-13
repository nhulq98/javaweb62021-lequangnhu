package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.request.TransactionRequest;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer/transaction")
public class TransactionAPI {

    @Autowired
    private ITransactionService service;

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        service.save(transactionRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
