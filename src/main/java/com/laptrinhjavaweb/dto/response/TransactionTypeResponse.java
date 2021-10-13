package com.laptrinhjavaweb.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TransactionTypeResponse extends BaseResponse{
    List<TransactionResponse> transactionResponses = new ArrayList<>();
    private String name;
}
