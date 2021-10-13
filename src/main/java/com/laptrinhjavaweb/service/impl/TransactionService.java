package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.request.TransactionRequest;
import com.laptrinhjavaweb.dto.response.TransactionResponse;
import com.laptrinhjavaweb.dto.response.TransactionTypeResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.entity.TransactionTypeEntity;
import com.laptrinhjavaweb.enums.TransactionTypeEnum;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionTypeRepository;
import com.laptrinhjavaweb.repository.TransationRepository;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransationRepository transationRepository;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Override
    public List<TransactionTypeResponse> getAllTranSactions() {
        List<TransactionTypeResponse> result = new ArrayList<>();
        for (TransactionTypeEnum item : TransactionTypeEnum.values()) {
            TransactionTypeResponse transactionTypeResponse = new TransactionTypeResponse();
            transactionTypeResponse.setName(item.getDistrictValue());
            transactionTypeResponse.setCode(item.toString());
            result.add(transactionTypeResponse);
        }
        return result;
    }

    @Override
    public List<TransactionResponse> getTransactionsOfCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findOne(id);
        List<TransactionTypeEntity> transactionTypes = customerEntity.getTransactionTypes();

        // TransactionType has Transaction list
        // output: list transaction ==> have to get list transaction from TransactionType
        //1 transactionType ==> has many transaction
        List<TransactionEntity> transactions = new ArrayList<>();
        for(TransactionTypeEntity item: transactionTypes){
            for(TransactionEntity t: item.getTransaction()){
                transactions.add(t);
            }
        }

        List<TransactionResponse> result = transactions.stream().map(TransactionResponse::new)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public void save(TransactionRequest transactionRequest) {
        CustomerEntity customerEntity = customerRepository.findOne(transactionRequest.getCustomerId());

        TransactionTypeEntity transactionTypes = new TransactionTypeEntity();
        transactionTypes.setCode(transactionRequest.getCode());
        transactionTypes.setCustomer(customerEntity);

        transactionTypeRepository.save(transactionTypes);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionType(transactionTypes);
        transaction.setNote(transactionRequest.getNote());
        transationRepository.save(transaction);
    }
}
