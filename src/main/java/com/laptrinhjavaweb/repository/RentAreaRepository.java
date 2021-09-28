package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentAreaRepository extends RentAreaRepositoryCustom, JpaRepository<RentAreaEntity, Long> {

    //void findAllByBuildingId(Long id);
}
