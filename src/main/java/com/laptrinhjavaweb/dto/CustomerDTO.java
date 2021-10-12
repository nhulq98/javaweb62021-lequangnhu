package com.laptrinhjavaweb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

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
}
