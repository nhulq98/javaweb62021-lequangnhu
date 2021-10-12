package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.response.TransactionTypeResponse;

import java.util.Collection;
import java.util.List;

public interface ITransactionService {
    List<TransactionTypeResponse> getAllTranSactions();
}
