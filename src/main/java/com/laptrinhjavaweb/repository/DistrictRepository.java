package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.DistrictEntity;
import com.laptrinhjavaweb.repository.custom.DistrictRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
}
