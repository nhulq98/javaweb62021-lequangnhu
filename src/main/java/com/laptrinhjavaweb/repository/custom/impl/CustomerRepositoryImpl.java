package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.CustomerSearch;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import com.laptrinhjavaweb.utils.Utils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerEntity> findByCondition(CustomerSearch customerSearch) {
        StringBuilder sql = this.buildQueryForCustomerSearch(customerSearch);
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    private StringBuilder buildFromClause(Long staffId){
        StringBuilder sql = new StringBuilder("SELECT C.*")
                .append(" FROM customer C ");
        if(staffId != null){
            sql.append(" JOIN staff_customer AC on AC.customerid = C.id");
        }
        return sql;
    }

    /**
     * buildQueryForCustomerSearch to concat all clauses to complete sql final
     *
     * @param customerSearch the customer from search form
     * @return sql String final
     */
    private StringBuilder buildQueryForCustomerSearch(CustomerSearch customerSearch) {
        StringBuilder sql = buildFromClause(customerSearch.getStaffId());

        buildWhereSQLClause(customerSearch, sql);

        return sql;
    }

    private void buildWhereSQLClause(CustomerSearch customerSearch, StringBuilder sql) {
        sql.append(" WHERE 1=1 ")
        .append(Utils.createConditionForNumber("AC.staffid", customerSearch.getStaffId()))
        .append(Utils.createConditionForStringByLike("C.phone", customerSearch.getPhone()))
        .append(Utils.createConditionForStringByLike("C.fullname", customerSearch.getFullName()))
        .append(Utils.createConditionForStringByLike("C.email", customerSearch.getEmail()))
        .append(" GROUP BY C.id ");
    }
}