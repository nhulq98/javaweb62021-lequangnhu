package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.MyUserDetail;
import com.laptrinhjavaweb.dto.request.CustomerRequest;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.view.StaffEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
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
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public void authorization(StringBuilder sql, Long staffId) {
        MyUserDetail userDetails = SecurityUtils.getMyUserDetail();

        boolean temp = SecurityUtils.isRole(SystemConstant.ROLE_STAFF, userDetails);
        if (temp == true) {
            sql.append(" JOIN assignmentcustomer AC on AC.customerid = C.id");
            //sql.append(" JOIN user US on AC.staffid = US.id");
            sql.append(" WHERE 1=1 ");
            sql.append(" AND AC.staffid =" + userDetails.getId());
        } else if (staffId != null) {// User Logged has role: MANAGER and exists by "staffId" search condition
            sql.append(" JOIN assignmentcustomer AC on AC.customerid = C.id ");
            sql.append(" WHERE 1=1 ");
        } else {
            sql.append(" WHERE 1=1 ");
        }

    }

    /**
     * buildQueryForCustomerSearch to concat all clauses to complete sql final
     *
     * @param conditionSearch the customer from search form
     * @return sql String final
     */
    @Override
    public StringBuilder buildQueryForCustomerSearch(CustomerRequest conditionSearch) {
        StringBuilder sql = new StringBuilder("SELECT C.*")
                .append(" FROM customer C ");
        authorization(sql, conditionSearch.getStaffId());
        buildWhereSQLClause(conditionSearch, sql);

        sql.append(" GROUP BY C.id ");
        return sql;
    }

    @Override
    public void buildWhereSQLClause(CustomerRequest conditionSearch, StringBuilder sql) {
        if (conditionSearch.getStaffId() != null) {
            sql.append(" and AC.staffid = " + conditionSearch.getStaffId());
        }
        if (conditionSearch.getFullName() != null && conditionSearch.getFullName().trim() != "") {
            sql.append(" and C.fullname LIKE '%" + conditionSearch.getFullName() + "%'");
        }
        if (conditionSearch.getPhone() != null && conditionSearch.getPhone().trim() != "") {
            sql.append(" and C.phone = " + conditionSearch.getPhone());
        }
        if (conditionSearch.getEmail() != null && conditionSearch.getEmail().trim() != "") {
            sql.append(" and C.email LIKE '%" + conditionSearch.getEmail() +"%'");
        }
    }
}