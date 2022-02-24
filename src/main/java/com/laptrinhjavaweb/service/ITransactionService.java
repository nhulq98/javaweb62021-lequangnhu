package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.TransactionRequest;
import com.laptrinhjavaweb.dto.response.TransactionResponse;
import com.laptrinhjavaweb.dto.response.TransactionTypeResponse;

import java.util.List;

public interface ITransactionService {
    List<TransactionTypeResponse> getAllTranSactions();

    void save(TransactionRequest transactionRequest);
}
