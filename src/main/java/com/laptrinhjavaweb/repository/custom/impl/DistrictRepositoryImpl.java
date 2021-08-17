package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.DistrictEntity;
import com.laptrinhjavaweb.repository.custom.DistrictRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class DistrictRepositoryImpl implements DistrictRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DistrictEntity findById(Long id) {
        String sql = "Select * From district where id = " + id;
        Query query = entityManager.createNativeQuery(sql, DistrictEntity.class);
        return (DistrictEntity) query.getSingleResult();
    }
}
