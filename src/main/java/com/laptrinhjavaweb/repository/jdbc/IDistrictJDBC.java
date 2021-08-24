package com.laptrinhjavaweb.repository.jdbc;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.DistrictEntity;

import java.sql.ResultSet;

public interface IDistrictJDBC {
    //Get Data
    DistrictEntity findById(Long id);

    //Logic
    DistrictEntity convertResultSetToEntity(ResultSet resultSet);
}
