package com.laptrinhjavaweb.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerRequest {
    private String fullName;
    private String phone;
    private String email;
    private Long staffId;

    private List<Long> customerIds;
}
