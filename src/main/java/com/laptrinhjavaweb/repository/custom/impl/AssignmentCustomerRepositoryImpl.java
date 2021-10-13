package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.view.StaffEntity;
import com.laptrinhjavaweb.repository.custom.AssignmentBuildingRepositoryCustom;
import com.laptrinhjavaweb.repository.custom.AssignmentCustomerRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AssignmentCustomerRepositoryImpl implements AssignmentCustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<StaffEntity> findAllCustom(Long customerId) {
        StringBuilder sql = new StringBuilder("SELECT US.id, US.fullname, US.createdby, US.createddate, US.modifiedby, US.modifieddate")
                .append(",(case ")
                .append(" WHEN USR.userid in ")
                .append(" (select ASCUST.staffid ")
                .append(" FROM staff_customer ASCUST ")
                .append(" WHERE ASCUST.customerid = " + customerId)
                .append(") THEN 'checked' ")
                .append(" ELSE 'NULL' ")
                .append(" END) AS checked ")
                .append(" FROM user US, user_role USR ")
                .append(" WHERE US.id = USR.userid ")
                .append(" AND USR.roleid = 2 -- role staff (default)");

        Query query = entityManager.createNativeQuery(sql.toString(), StaffEntity.class);
        return query.getResultList();
    }
}
