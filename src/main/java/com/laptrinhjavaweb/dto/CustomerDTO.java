package com.laptrinhjavaweb.dto;

import com.laptrinhjavaweb.dto.response.TransactionTypeResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomerDTO extends AbstractDTO<CustomerDTO> {
    private String fullName;
    private String phone;
    private String email;
    private Long staffId;
    private String company;
    private String demand;
    private String note;
    private String status;
    List<TransactionTypeResponse> transactionTypelist = new ArrayList<>();
}
