package com.laptrinhjavaweb.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomerResponse extends BaseResponse {
    List<TransactionTypeResponse> transactionTypelist = new ArrayList<>();
    private String fullName;
    private String phone;
    private String email;
    private String managerName;
    private String demand;
    private String status;
}
