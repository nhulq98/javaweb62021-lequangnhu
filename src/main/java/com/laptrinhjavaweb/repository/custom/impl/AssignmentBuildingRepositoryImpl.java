package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
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
        Utils.destroyReference(sql);
        return query.getResultList();
    }

}