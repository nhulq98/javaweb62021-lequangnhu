package com.laptrinhjavaweb.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.condition.BuildingCondition;
import com.laptrinhjavaweb.repository.jdbc.IBuildingJDBC;

public class BuildingImpl extends BaseImpl implements IBuildingJDBC {
	
	private Connection connection;
	private PreparedStatement prStatement;
	private ResultSet resultSet;
	private static boolean temp = false; // this temp variable use to check keyword AND.(If it's false ==> not exists)

	@Override
	public List<BuildingDTO> findAll() {
		String sql = "SELECT * FROM building";
		List<BuildingDTO> results = new ArrayList<>();
		try {
			this.connection = super.getConnection();
			connection.setAutoCommit(false);
			prStatement = connection.prepareStatement(sql);

			resultSet = prStatement.executeQuery();
			while (resultSet.next()) {
				// get All data from resultset
				results.add(convertToBuildingDTO(resultSet));
				return results;
			}
			connection.commit();
			return results;

		} catch (SQLException e) {
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return null;
		} finally {
			closeAll(connection, prStatement, resultSet);
		}
	}

	@Override
	public List<BuildingDTO> findByCondition(BuildingCondition condition) {
		List<BuildingDTO> results = new ArrayList<>();
		try {
			this.connection = super.getConnection();
			this.connection.setAutoCommit(false);
			this.prStatement = this.connection.prepareStatement(this.buildQuery_V2(condition));

			this.resultSet = this.prStatement.executeQuery();
			while (this.resultSet.next()) {
				// get All data from resultset
				BuildingDTO buildingDTO = new BuildingDTO();
				buildingDTO = convertToBuildingDTO(this.resultSet);
				results.add(buildingDTO);
			}
			this.connection.commit();
			return results;

		} catch (SQLException e) {
			try {
				if (this.connection != null) {
					this.connection.rollback();
					e.printStackTrace();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return null;
		} finally {
			closeAll(this.connection, this.prStatement, this.resultSet);
		}
	}

	@Override
	public BuildingDTO convertToBuildingDTO(ResultSet resultSet) {
		try {
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO.setId(resultSet.getLong("id"));
			buildingDTO.setName(resultSet.getString("name"));
			buildingDTO.setDistrictID(resultSet.getLong("districtid"));
			buildingDTO.setStreet(resultSet.getString("street"));
			buildingDTO.setStructure(resultSet.getString("structure"));
			buildingDTO.setNumberOfBasement(resultSet.getLong("numberofbasement"));
			buildingDTO.setFloorArea(resultSet.getLong("floorarea"));
			buildingDTO.setDirection(resultSet.getString("direction"));
			buildingDTO.setLevel(resultSet.getString("level"));
			buildingDTO.setRentPrice(resultSet.getLong("rentPrice"));
			buildingDTO.setRentPriceDescription(resultSet.getString("rentPriceDescription"));
			buildingDTO.setServiceFee(resultSet.getString("serviceFee"));
			buildingDTO.setCarFee(resultSet.getString("carFee"));
			buildingDTO.setMotorbikeFee(resultSet.getString("motorbikeFee"));
			buildingDTO.setOvertimeFee(resultSet.getString("overtimeFee"));
			buildingDTO.setWaterFee(resultSet.getString("waterfee"));
			buildingDTO.setElectricityFee(resultSet.getString("electricityfee"));
			buildingDTO.setDeposit(resultSet.getString("deposit"));
			buildingDTO.setPayment(resultSet.getString("payment"));
			buildingDTO.setRentTime(resultSet.getString("renttime"));
			buildingDTO.setDecoratorTime(resultSet.getString("decorationtime"));
			buildingDTO.setBrokerageFee(resultSet.getString("brokeragefee"));
			buildingDTO.setNote(resultSet.getString("note"));
			buildingDTO.setLinkofbuilding(resultSet.getString("linkofbuilding"));
			buildingDTO.setMap(resultSet.getString("map"));
			buildingDTO.setImage(resultSet.getString("image"));
			buildingDTO.setManagerPhone(resultSet.getString("managerphone"));
			buildingDTO.setManagerName(resultSet.getString("managername"));

			buildingDTO.setCreatedDate(resultSet.getTimestamp("createddate"));
			buildingDTO.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			buildingDTO.setCreatedBy(resultSet.getString("createdby"));
			buildingDTO.setModifiedBy(resultSet.getString("modifiedby"));

			return buildingDTO;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public String buildQuery(BuildingCondition condition) {
		try {
			boolean temp = false;// keyword AND not exists
			String sqlFromClause = "SELECT * FROM building BD ";
			//StringBuilder sqlFromClause = new StringBuilder("SELECT * FROM building BD ");
			StringBuilder whereSQLClause = new StringBuilder("");
			if (condition.getDistrictID() != null) {
				//sqlFromClause.append(", district DTrict");// insert table into from
				sqlFromClause += ", district DTrict";

				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("BD.districtid = DTrict.id ")));
				temp = true; // keyword AND exists
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("DTrict.id = " + condition.getDistrictID() + " ")));
				
			}
			if (condition.getName() != null) {
				whereSQLClause.append(
						this.checkAndKeyword(temp, new StringBuilder("BD.name LIKE '%" + condition.getName() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getRentEreaFrom() != null || condition.getRentEreaTo() != null) {
				//sqlFromClause.append(", rentarea RErea");
				sqlFromClause += ", rentarea RErea";
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("RErea.buildingid = BD.id ")));
				temp = true; // keyword AND exists
				if (condition.getRentEreaFrom() != null && condition.getRentEreaTo() != null) {
					whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("RErea.value BETWEEN "
							+ condition.getRentEreaFrom() + " " + condition.getRentEreaTo() + " ")));
				}
				if (condition.getRentEreaFrom() != null && condition.getRentEreaTo() == null) {
					whereSQLClause.append(this.checkAndKeyword(temp,
							new StringBuilder("RErea.value >= " + condition.getRentEreaFrom() + " ")));
				}
				if (condition.getRentEreaFrom() == null && condition.getRentEreaTo() != null) {
					whereSQLClause.append(this.checkAndKeyword(temp,
							new StringBuilder("RErea.value <= " + condition.getRentEreaTo() + " ")));
				}
			}
			if (condition.getUserID() != null) {
				//sqlFromClause.append(", user U, assignmentbuilding Abuilding");// insert table into from
				sqlFromClause += ", user U, assignmentbuilding Abuilding";
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("Abuilding.staffid = U.id ")));
				temp = true; // keyword AND exists
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("Abuilding. Buildingid = BD.id ")));
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("U.id = " + condition.getUserID() + " ")));
			}
			if (condition.getFloorArea() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.floorarea = " + condition.getFloorArea() + " ")));
				temp = true; // keyword AND exists
			}
			if (condition.getWard() != null) {
				whereSQLClause.append(
						this.checkAndKeyword(temp, new StringBuilder("BD.ward LIKE '%" + condition.getWard() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getStreet() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.street LIKE '%" + condition.getStreet() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getNumberOfBasement() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.numberOfBasement = " + condition.getNumberOfBasement() + " ")));
				temp = true; // keyword AND exists
			}
			if (condition.getRentPriceFrom() != null && condition.getRentPriceTo() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("BD.rentprice BETWEEN "
						+ condition.getRentPriceFrom() + " AND " + condition.getRentPriceTo() + " ")));
				temp = true; // keyword AND exists
			}
			if (condition.getRentPriceFrom() != null && condition.getRentPriceTo() == null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.rentprice >= " + condition.getRentPriceFrom() + " ")));
				temp = true; // keyword AND exists
			}
			if (condition.getRentPriceFrom() == null && condition.getRentPriceTo() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.rentprice <= " + condition.getRentPriceTo() + " ")));
				temp = true; // keyword AND exists
			}
			if (condition.getDirection() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.direction  LIKE '%" + condition.getDirection() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getLevel() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.Level LIKE '%" + condition.getLevel() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getManagerName() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.managername LIKE '%" + condition.getManagerName() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getManagerPhone() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.managerphone LIKE '%" + condition.getManagerPhone() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getListType() != null) {
				//sqlFromClause.append(", renttype RType");// insert table into from
				sqlFromClause += ", renttype RType";
				//sqlFromClause.append(", buildingrenttype BRType");// insert table into from
				sqlFromClause += ", buildingrenttype BRType";
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("RType.id = BRType. Renttypeid ")));
				temp = true; // keyword AND exists
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("BD.id = BRType. buildingid ")));
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("RType.id = " + condition.getListType().get(0) + " ")));
				if (condition.getListType().size() > 1) {
					for (int i = 1; i < condition.getListType().size(); i++) {
						whereSQLClause.append(new StringBuilder("OR RType.id = " + condition.getListType().get(i) + " "));
					}
				}
			}
			String result = sqlFromClause + whereSQLClause.toString();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public StringBuilder checkAndKeyword(boolean temp, StringBuilder string) {
		if(temp == false) {
			return new StringBuilder(" WHERE " + string.toString());
		}else {
			return new StringBuilder(" AND ").append(string.toString());
		}
	}

	@Override
	public String buildQuery_V2(BuildingCondition condition) {
		try {
			String fromSQLClause = "SELECT * FROM building BD ";
			String joinSQLClause = " JOIN district DT on DT.id = BD.districtid ";
			StringBuilder whereSQLClause = new StringBuilder(" WHERE 1=1 ");
			whereSQLClause.append(this.checkExistenceOfCondition (" AND BD.name LIKE '%" + condition.getName() + "%' ", condition.getName()));
			whereSQLClause.append(this.checkExistenceOfCondition (" AND BD.street LIKE '%" + condition.getStreet() + "%' ", condition.getStreet()));
			whereSQLClause.append(this.checkExistenceOfCondition (" AND BD.ward LIKE '%" + condition.getWard() + "%' ", condition.getWard()));
			whereSQLClause.append(this.checkExistenceOfCondition (" AND BD.districtid = " + condition.getDistrictID() + " ", condition.getDistrictID()));
			whereSQLClause.append(this.checkExistenceOfCondition (" AND BD.floorarea = " + condition.getFloorArea() + " ", condition.getFloorArea()));
			whereSQLClause.append(this.checkExistenceOfCondition (" AND BD.numberOfBasement = " + condition.getNumberOfBasement() + " ", condition.getNumberOfBasement()));
			whereSQLClause.append(this.checkExistenceOfCondition (" AND BD.direction  LIKE '%" + condition.getDirection() + "%' ", condition.getDirection()));
			whereSQLClause.append(this.checkExistenceOfCondition (" AND BD.Level LIKE '%" + condition.getLevel() + "%' ", condition.getLevel()));
			whereSQLClause.append(this.checkExistenceOfCondition (" AND BD.managername LIKE '%" + condition.getManagerName() + "%' ", condition.getManagerName()));
			whereSQLClause.append(this.checkExistenceOfCondition (" AND BD.managerphone LIKE '%" + condition.getManagerPhone() + "%' ", condition.getManagerPhone()));
			whereSQLClause.append(this.buildBetweenStatement("BD.rentprice", condition.getRentPriceFrom(), condition.getRentPriceTo()));
			if (condition.getUserID() != null) {
				joinSQLClause += " JOIN assignmentbuilding ASB on  ASB.buildingid = BD.id ";
				whereSQLClause.append(" AND ASB.staffid = " + condition.getUserID() + " ");
			}
			if (!this.isNull(condition.getRentEreaFrom()) || !this.isNull(condition.getRentEreaTo())) {
				joinSQLClause += " JOIN rentarea RE ON RE.buildingid = BD.id ";
				whereSQLClause.append(this.buildBetweenStatement("RE.value", condition.getRentEreaFrom(), condition.getRentEreaTo()));
			}
			if (condition.getListType() != null) {
				joinSQLClause += " JOIN buildingrenttype BRT ON BRT.buildingid = BD.id ";
				joinSQLClause += " JOIN renttype RT ON RT.id = BRT.renttypeid ";
				
				whereSQLClause.append(" AND RT.code = \"" + condition.getListType().get(0) + "\" ");
				if (condition.getListType().size() > 1) {
					for (int i = 1; i < condition.getListType().size(); i++) {
						whereSQLClause.append(" OR RT.code = \"" + condition.getListType().get(i) + "\" ");
					}
				}
			}
			String result = fromSQLClause + joinSQLClause + whereSQLClause.toString() + " GROUP BY BD.id ";
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean isNull(Object value) {
		if(value == null) {
			return true;
		}
		return false;
	}

	@Override
	public String checkExistenceOfCondition (String sql, Object parameter) {
		if(parameter != null && !this.isBlank(parameter)) {
			return sql;
		}
		return "";
	}

	@Override
	public StringBuilder buildBetweenStatement(String whereSQLClause, Object from, Object to) {
		if(!this.isNull(from) || !this.isNull(to)) {
			if(!this.isNull(from) && !this.isNull(to)) {
				return new StringBuilder(" AND "+ whereSQLClause +" BETWEEN " + from + " AND " + to + " ");
			}else if(!this.isNull(from) && this.isNull(to)) {
				return new StringBuilder(" AND "+ whereSQLClause +" >= " + from + " ");
			}else {
				return new StringBuilder(" AND "+ whereSQLClause +" <= " + to + " ");
			}
		}
		return new StringBuilder("");
	}

	@Override
	public boolean isBlank(Object value) {
		if(value instanceof String && value == "") {
			return true;
		}
		return false;
	}

}
