package com.laptrinhjavaweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;

public interface IBaseDAO {
	Connection getConnection();
	void closeAll(Connection connection, PreparedStatement prStatement, ResultSet resultSet);
	void setParameters(PreparedStatement prStatement, Object... parameters);
}
