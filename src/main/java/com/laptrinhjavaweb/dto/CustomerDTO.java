package com.laptrinhjavaweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO extends AbstractDTO<CustomerDTO> {
    private String fullName;
    private String phone;
    private String email;
    private Long staffId;
}
