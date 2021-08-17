package com.laptrinhjavaweb.repository.jdbc;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.DistrictEntity;

import java.sql.ResultSet;

public interface IDistrictJDBC {
    DistrictEntity findById(Long id);
    DistrictEntity convertResultSetToEntity(ResultSet resultSet);
}
