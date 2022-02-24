package com.laptrinhjavaweb.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.builder.CustomerSearch;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.dto.response.CustomerResponse;
import com.laptrinhjavaweb.dto.response.TransactionResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerConverter extends AbstractConverter<CustomerDTO, CustomerEntity> {

    @Override
    public CustomerDTO convertEntityToDTO(CustomerEntity entity) {
        Optional.ofNullable(entity)
                .orElseThrow(() -> new NullPointerException("customerEntity null!"));

        CustomerDTO dto = modelMapper.map(entity, CustomerDTO.class);
        List<TransactionResponse> transactionlist = entity.getTransactions().stream().map(TransactionResponse::new).collect(Collectors.toList());
        dto.setTransactionlist(transactionlist);

        return dto;
    }

    @Override
    public CustomerEntity convertDTOToEntity(CustomerDTO customerDTO) {
        Optional.ofNullable(customerDTO)
                .orElseThrow(() -> new NullPointerException("customerDTO null!"));

        CustomerEntity entity = modelMapper.map(customerDTO, CustomerEntity.class);
        return entity;
    }

    public CustomerRequest convertMapToRequest(Map<String, Object> map) {
        Optional.ofNullable(map)
                .orElseThrow(() -> new NullPointerException("map null!"));

        ObjectMapper mapper = new ObjectMapper();
        CustomerRequest result = mapper.convertValue(map, CustomerRequest.class);
        return result;
    }

    public Map<String, Object> convertRequestToMap(CustomerRequest request) {
        Optional.ofNullable(request)
                .orElseThrow(() -> new NullPointerException("CustomerRequest null!"));

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = mapper.convertValue(request, Map.class);
        return result;
    }

    public CustomerResponse convertEntityToResponse(CustomerEntity entity) {
        Optional.ofNullable(entity)
                .orElseThrow(() -> new NullPointerException("CustomerEntity null!"));

        CustomerResponse response = modelMapper.map(entity, CustomerResponse.class);
        if(entity.getStaffs().size() > 0){
            response.setManagerName(entity.getStaffs().get(0).getFullName());
        }
        return response;
    }

    public CustomerSearch convertDTOToBuilder(CustomerRequest customerRequest) {
        Optional.ofNullable(customerRequest)
                .orElseThrow(() -> new NullPointerException("customerRequest null!"));

        Long staffId = customerRequest.getStaffId();

        // for authorization when user logging is staff
        if(SecurityUtils.getAuthorities().contains(SystemConstant.ROLE_STAFF)){
            staffId = SecurityUtils.getPrincipal().getId();
        }

        CustomerSearch result = new CustomerSearch.Builder()
                .setFullName(customerRequest.getFullName())
                .setEmail(customerRequest.getEmail())
                .setPhone(customerRequest.getPhone())
                .setStaffId(staffId).build();
        return result;
    }
}
