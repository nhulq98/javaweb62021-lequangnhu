package com.laptrinhjavaweb.mapper;

import com.laptrinhjavaweb.entity.DistrictEntity;

import java.sql.ResultSet;

public class DistrictMapper implements IRowMapper<DistrictEntity> {
    @Override
    public DistrictEntity mapRow(ResultSet resultSet) {
        try {
            DistrictEntity districtEntity = new DistrictEntity();
            districtEntity.setName(resultSet.getString("name"));
            districtEntity.setCode(resultSet.getString("code"));

            return districtEntity;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new DistrictEntity();
        }
    }
}
