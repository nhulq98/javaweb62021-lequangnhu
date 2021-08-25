package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.repository.custom.AssignmentBuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentBuildingRepository extends AssignmentBuildingRepositoryCustom, JpaRepository<AssignmentBuildingEntity, Long> {
}
