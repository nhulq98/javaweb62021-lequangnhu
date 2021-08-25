package com.laptrinhjavaweb.repository.jpa.impl;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.jpa.IBuildingJPA;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

public class BuildingJPA implements IBuildingJPA {

    @PersistenceContext// giúp khởi tạo đối tượng EntityManager
    private EntityManager entityManager;

    @Override
    public void saveJPA(BuildingEntity entity) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public BuildingEntity updateJPA(BuildingEntity entity) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entityManager.merge(entity);
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
        return new BuildingEntity();
    }

    @Override
    public void deleteAssignmentStaffs(BuildingEntity entity) {
        entityManager.remove(entity);
    }
}
