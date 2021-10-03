package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepositoryCustom {

    List<CustomerEntity> findByCondition(CustomerRequest request);

    /**
     * buildQueryForCustomerSearch to concat all clauses to complete sql final
     *
     * @param request the customer from search form
     * @return sql String final
     */
    StringBuilder buildQueryForCustomerSearch(CustomerRequest request);
}
