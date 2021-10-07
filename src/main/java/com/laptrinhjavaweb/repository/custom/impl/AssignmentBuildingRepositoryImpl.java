package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.view.StaffEntity;
import com.laptrinhjavaweb.repository.custom.AssignmentBuildingRepositoryCustom;
import com.laptrinhjavaweb.utils.Utils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository// nó hiểu đây là 1 module
public class AssignmentBuildingRepositoryImpl implements AssignmentBuildingRepositoryCustom {

    @PersistenceContext// giúp khởi tạo đối tượng EntityManager
    private EntityManager entityManager;

    @Override
    public List<AssignmentBuildingEntity> findAllByBuildingId(Long id) {
        String sql = "SELECT * FROM assignmentbuilding WHERE buildingid = " + id;
        Query query = entityManager.createNativeQuery(sql, AssignmentBuildingEntity.class);
        Utils.customGC(sql);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<StaffEntity> findAllCustom(Long buildingId) {
        StringBuilder sql = new StringBuilder("SELECT US.id, US.fullname, US.createdby, US.createddate, US.modifiedby, US.modifieddate")
                .append(",(case ")
                .append(" WHEN USR.userid in ")
                .append(" (select ASB.staffid ")
                .append(" FROM assignmentbuilding ASB ")
                .append(" WHERE ASB.buildingid = " + buildingId)
                .append(") THEN 'checked' ")
                .append(" ELSE 'NULL' ")
                .append(" END) AS checked ")
                .append(" FROM user US, user_role USR ")
                .append(" WHERE US.id = USR.userid ")
                .append(" AND USR.roleid = 2 -- role staff (default)");

        Query query = entityManager.createNativeQuery(sql.toString(), StaffEntity.class);
        Utils.customGC(sql);
        return query.getResultList();
    }
}