package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.response.TransactionTypeResponse;
import com.laptrinhjavaweb.enums.TransactionTypeEnum;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Override
    public List<TransactionTypeResponse> getAllTranSactions() {
        try {
            List<TransactionTypeResponse> listDistrict = new ArrayList<>();
            for (TransactionTypeEnum item : TransactionTypeEnum.values()) {
                TransactionTypeResponse transactionTypeResponse = new TransactionTypeResponse();
                transactionTypeResponse.setName(item.getDistrictValue());

                listDistrict.add(transactionTypeResponse);
            }
            return listDistrict;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
