package com.laptrinhjavaweb.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.dto.response.CustomerResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerConverter extends AbstractConverter<CustomerDTO, CustomerEntity> {

    @Override
    public CustomerDTO convertEntityToDTO(CustomerEntity entity) {
        CustomerDTO dto = modelMapper.map(entity, CustomerDTO.class);
        return dto;
    }

    @Override
    public CustomerEntity convertDTOToEntity(CustomerDTO customerDTO) {
        CustomerEntity entity = modelMapper.map(customerDTO, CustomerEntity.class);
        return entity;
    }

    public CustomerRequest convertMapToRequest(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        CustomerRequest result = mapper.convertValue(map, CustomerRequest.class);
        return result;
    }

    public Map<String, Object> convertRequestToMap(CustomerRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = mapper.convertValue(request, Map.class);
        return result;
    }

    public CustomerResponse convertEntityToResponse(CustomerEntity entity) {
        CustomerResponse response = modelMapper.map(entity, CustomerResponse.class);
        if(entity.getAssignmentCustomers().size() > 0){
            response.setManagerName(entity.getAssignmentCustomers().get(0).getUser().getFullName());
        }
        return response;
    }
}
