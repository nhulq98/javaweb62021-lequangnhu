package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.request.TransactionRequest;
import com.laptrinhjavaweb.dto.response.TransactionResponse;
import com.laptrinhjavaweb.dto.response.TransactionTypeResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.enums.TransactionTypeEnum;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.service.ITransactionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transationRepository;

    @Override
    public List<TransactionTypeResponse> getAllTranSactions() {
        List<TransactionTypeResponse> result = new ArrayList<>();
        for (TransactionTypeEnum item : TransactionTypeEnum.values()) {
            TransactionTypeResponse transactionTypeResponse = new TransactionTypeResponse();
            transactionTypeResponse.setName(item.getDistrictValue());
            transactionTypeResponse.setCode(item.toString());
            result.add(transactionTypeResponse);
        }
        String a = "";
        return result;
    }

    @Override
    @Transactional
    public void save(TransactionRequest transactionRequest) {
        TransactionEntity transaction = new TransactionEntity();

        String code = transactionRequest.getCode();
        String note = transactionRequest.getNote();
        CustomerEntity customer = Optional.ofNullable(customerRepository.findOne(transactionRequest.getCustomerId()))
                .orElseThrow(() -> new NotFoundException("Customer not found!"));

        transaction.setCustomer(customer);

        if(StringUtils.isNotBlank(code))
            transaction.setCode(code);
        if(StringUtils.isNotBlank(note))
            transaction.setNote(note);

        transationRepository.save(transaction);
    }
}
