package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class RentAreaRepositoryImpl implements RentAreaRepositoryCustom {

    @Autowired
    private BuildingConverter buildingConverter;

    @PersistenceContext// initializer for EntityManager
    private EntityManager entityManager;

    @Override
    public List<RentAreaEntity> findAllByBuildingId(Long id) {
        StringBuilder sql = new StringBuilder("SELECT RA.* ")
                .append(" FROM building BD")
                .append(" join rentarea RA on RA.buildingid = BD.id")
                .append(" where BD.id = " + id);
        Query query = entityManager.createNativeQuery(sql.toString(), RentAreaEntity.class);
        return query.getResultList();
    }
}
