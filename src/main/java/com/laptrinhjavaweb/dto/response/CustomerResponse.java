package com.laptrinhjavaweb.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse extends BaseResponse {
    private String fullName;
    private String phone;
    private String email;
    private String managerName;
    private String demand;
    private String status;
}
