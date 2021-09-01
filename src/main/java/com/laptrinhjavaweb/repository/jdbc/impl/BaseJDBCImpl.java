package com.laptrinhjavaweb.repository.jdbc.impl;

import com.laptrinhjavaweb.mapper.IRowMapper;
import com.laptrinhjavaweb.repository.jdbc.IBaseJDBC;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BaseJDBCImpl implements IBaseJDBC{
	private static ResourceBundle dbResourceBundle = ResourceBundle.getBundle("application");
	private static String PASSWORD = dbResourceBundle.getString("password");
	private static String URL = dbResourceBundle.getString("url");
	private static String USER = dbResourceBundle.getString("userName");
	private static String DRIVER_NAME = dbResourceBundle.getString("driverName");

	private static Connection connection = getConnection();
	private PreparedStatement prStatement;
	private ResultSet resultSet;

	public static Connection getConnection() {
		try {
			// Load the Connector driver
			Class.forName(DRIVER_NAME);
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			return null;
		}

	}

	public static void closeAll(Connection connection, PreparedStatement prStatement, ResultSet resultSet) {
		try {
			if (connection != null) {
				connection.close();
			}
			if (prStatement != null) {
				prStatement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return;
		}
	}

	public static void setParameters(PreparedStatement prStatement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				// way 1:
				// prStatement.setObject(i + 1, parameters[i]);

				// way 2:
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					prStatement.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					prStatement.setString(index, (String) parameter);
				} else if (parameter instanceof Timestamp) {
					prStatement.setTimestamp(index, (Timestamp) parameter);
				} else if (parameter instanceof Integer) {
					prStatement.setInt(index, (Integer) parameter);
				} else if (parameter == null) {
					prStatement.setNull(index, Types.NULL);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//@Override
	public <T> List<T> query(String sql, IRowMapper<T> objectMapper, Object... parameters) {
		List<T> results = new ArrayList<>();
		try {
			connection.setAutoCommit(false);
			prStatement = connection.prepareStatement(sql);

			// set parameters
			if(parameters.length != 0){
				setParameters(prStatement, parameters);
			}
			resultSet = prStatement.executeQuery();
			while (resultSet.next()) {
				//get All data from resultset
				results.add(objectMapper.mapRow(resultSet));
			}
			connection.commit();
			return results;

		} catch (SQLException e) {
			try {
				if(connection != null){
					connection.rollback();
				}
			}catch (SQLException e1){
				e1.printStackTrace();
			}

			return new ArrayList<>();
		} finally {

			closeAll(connection, prStatement, resultSet);
			//connectionPool.releaseConnection(connection);
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Long id = null;
		try{
			connection.setAutoCommit(false);
			prStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// set parameter
			setParameters(prStatement, parameters);

			prStatement.executeUpdate();
			ResultSet resultSet = prStatement.getGeneratedKeys();
			if(resultSet.next()){
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		}catch (SQLException e){
			try {
				connection.rollback();// return and database not change
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return 0L;
		}finally {
			closeAll(connection, prStatement, resultSet);
		}
	}

	//generic function for update and delete in database
	@Override
	public void update(String sql, Object... parameters) {
		try{
			connection.setAutoCommit(false);
			prStatement = connection.prepareStatement(sql);
			// set parameter
			setParameters(prStatement, parameters);

			prStatement.executeUpdate();

			connection.commit();
		}catch (SQLException e){
			try {
				connection.rollback();// return and database not affected
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}finally {
			closeAll(connection, prStatement, resultSet);
		}
	}
}
