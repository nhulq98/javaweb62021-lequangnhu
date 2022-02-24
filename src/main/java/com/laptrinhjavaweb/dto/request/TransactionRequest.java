package com.laptrinhjavaweb.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest extends BaseRequest{
    private Long customerId;
    private String code;//transaction type
    private String note;
}
