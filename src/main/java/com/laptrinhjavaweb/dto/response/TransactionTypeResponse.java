package com.laptrinhjavaweb.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionTypeResponse extends BaseResponse{
    private String name;
    private String code;
    private String note;
}
