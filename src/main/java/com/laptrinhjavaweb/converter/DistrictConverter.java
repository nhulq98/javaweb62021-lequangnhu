package com.laptrinhjavaweb.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.response.DistrictResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DistrictConverter {

    public DistrictResponse convertMapToResponse(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();
        DistrictResponse result = mapper.convertValue(map, DistrictResponse.class);
        return result;
    }
}
