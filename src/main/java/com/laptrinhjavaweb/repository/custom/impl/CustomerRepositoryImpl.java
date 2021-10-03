package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CustomerEntity> findByCondition(CustomerRequest request) {
        StringBuilder sql = this.buildQueryForCustomerSearch(request);
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public StringBuilder buildQueryForCustomerSearch(CustomerRequest request) {
        StringBuilder sql = new StringBuilder("SELECT C.*")
                .append(" FROM customer C ")
                .append(" JOIN assignmentcustomer AC on AC.customerid = C.id")
                .append(" JOIN user US on AC.staffid = US.id")
                .append(" WHERE 1=1 ");

        if (request.getStaffId() != null) {
            sql.append(" and AC.staffid = " + request.getStaffId());
        }
        if (request.getFullName() != null && request.getFullName().trim() != "") {
            sql.append(" C.fullname LIKE '%" + request.getFullName() + "%'");
        }
        if (request.getFullName() != null && request.getFullName().trim() != "") {
            sql.append(" C.phone = " + request.getPhone());
        }
        if (request.getFullName() != null && request.getFullName().trim() != "") {
            sql.append(" C.email LIKE '%" + request.getEmail() +"%'");
        }
        sql.append(" GROUP BY C.id ");
        return sql;
    }
}
