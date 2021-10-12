package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.BuildingSearch;
import com.laptrinhjavaweb.dto.MyUserDetail;
import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepositoryCustom {

    // Scope for get data
    List<CustomerEntity> findByCondition(CustomerRequest request);

    // Scope for logic
    void authorization(StringBuilder sql, Long staffId);

    /**
     * buildQueryForCustomerSearch to concat all clauses to complete sql final
     *
     * @param request the customer from search form
     * @return sql String final
     */
    StringBuilder buildQueryForCustomerSearch(CustomerRequest request);

    void buildWhereSQLClause(CustomerRequest conditionSearch, StringBuilder sql);

}
