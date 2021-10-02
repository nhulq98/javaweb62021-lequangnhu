package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.repository.custom.AssignmentBuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingRepository extends AssignmentBuildingRepositoryCustom, JpaRepository<AssignmentBuildingEntity, Long> {
    void deleteByBuildingId(Long id);

    List<AssignmentBuildingEntity> findByBuildingId(Long id);
}
