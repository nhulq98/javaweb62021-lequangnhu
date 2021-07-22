package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.IBuildingDAO;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.condition.BuildingCondition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingDAO extends BaseDAO implements IBuildingDAO {

	private Connection connection;
	private PreparedStatement prStatement;
	private ResultSet resultSet;

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
			this.prStatement = this.connection.prepareStatement(this.BuildQuery(condition));

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
	public String BuildQuery(BuildingCondition condition) {
		try {
			boolean temp = false;// keyword AND not exists
			String sqlFrom = "SELECT * FROM building BD ";
			//StringBuilder sqlFrom = new StringBuilder("SELECT * FROM building BD ");
			StringBuilder sqlWhere = new StringBuilder("");
			if (condition.getDistrictID() != null) {
				//sqlFrom.append(", district DTrict");// insert table into from
				sqlFrom += ", district DTrict";

				sqlWhere.append(this.checkAndKeyword(temp, new StringBuilder("BD.districtid = DTrict.id ")));
				temp = true; // keyword AND exists
				sqlWhere.append(this.checkAndKeyword(temp,
						new StringBuilder("DTrict.id = " + condition.getDistrictID() + " ")));
				
			}
			if (condition.getName() != null) {
				sqlWhere.append(
						this.checkAndKeyword(temp, new StringBuilder("BD.name LIKE '%" + condition.getName() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getRentEreaFrom() != null || condition.getRentEreaTo() != null) {
				//sqlFrom.append(", rentarea RErea");
				sqlFrom += ", rentarea RErea";
				sqlWhere.append(this.checkAndKeyword(temp, new StringBuilder("RErea.buildingid = BD.id ")));
				temp = true; // keyword AND exists
				if (condition.getRentEreaFrom() != null && condition.getRentEreaTo() != null) {
					sqlWhere.append(this.checkAndKeyword(temp, new StringBuilder("RErea.value BETWEEN "
							+ condition.getRentEreaFrom() + " " + condition.getRentEreaTo() + " ")));
				}
				if (condition.getRentEreaFrom() != null && condition.getRentEreaTo() == null) {
					sqlWhere.append(this.checkAndKeyword(temp,
							new StringBuilder("RErea.value >= " + condition.getRentEreaFrom() + " ")));
				}
				if (condition.getRentEreaFrom() == null && condition.getRentEreaTo() != null) {
					sqlWhere.append(this.checkAndKeyword(temp,
							new StringBuilder("RErea.value <= " + condition.getRentEreaTo() + " ")));
				}
			}
			if (condition.getUserID() != null) {
				//sqlFrom.append(", user U, assignmentbuilding Abuilding");// insert table into from
				sqlFrom += ", user U, assignmentbuilding Abuilding";
				sqlWhere.append(this.checkAndKeyword(temp, new StringBuilder("Abuilding.staffid = U.id ")));
				temp = true; // keyword AND exists
				sqlWhere.append(this.checkAndKeyword(temp, new StringBuilder("Abuilding. Buildingid = BD.id ")));
				sqlWhere.append(this.checkAndKeyword(temp, new StringBuilder("U.id = " + condition.getUserID() + " ")));
			}
			if (condition.getFloorArea() != null) {
				sqlWhere.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.floorarea = " + condition.getFloorArea() + " ")));
				temp = true; // keyword AND exists
			}
			if (condition.getWard() != null) {
				sqlWhere.append(
						this.checkAndKeyword(temp, new StringBuilder("BD.ward LIKE '%" + condition.getWard() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getStreet() != null) {
				sqlWhere.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.street LIKE '%" + condition.getStreet() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getNumberOfBasement() != null) {
				sqlWhere.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.numberOfBasement = " + condition.getNumberOfBasement() + " ")));
				temp = true; // keyword AND exists
			}
			if (condition.getRentPriceFrom() != null && condition.getRentPriceTo() != null) {
				sqlWhere.append(this.checkAndKeyword(temp, new StringBuilder("BD.rentprice BETWEEN "
						+ condition.getRentPriceFrom() + " AND " + condition.getRentPriceTo() + " ")));
				temp = true; // keyword AND exists
			}
			if (condition.getRentPriceFrom() != null && condition.getRentPriceTo() == null) {
				sqlWhere.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.rentprice >= " + condition.getRentPriceFrom() + " ")));
				temp = true; // keyword AND exists
			}
			if (condition.getRentPriceFrom() == null && condition.getRentPriceTo() != null) {
				sqlWhere.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.rentprice <= " + condition.getRentPriceTo() + " ")));
				temp = true; // keyword AND exists
			}
			if (condition.getDirection() != null) {
				sqlWhere.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.direction  LIKE '%" + condition.getDirection() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getLevel() != null) {
				sqlWhere.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.Level LIKE '%" + condition.getLevel() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getManagerName() != null) {
				sqlWhere.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.managername LIKE '%" + condition.getManagerName() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getManagerPhone() != null) {
				sqlWhere.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.managerphone LIKE '%" + condition.getManagerPhone() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (condition.getListType() != null) {
				//sqlFrom.append(", renttype RType");// insert table into from
				sqlFrom += ", renttype RType";
				//sqlFrom.append(", buildingrenttype BRType");// insert table into from
				sqlFrom += ", buildingrenttype BRType";
				sqlWhere.append(this.checkAndKeyword(temp, new StringBuilder("RType.id = BRType. Renttypeid ")));
				temp = true; // keyword AND exists
				sqlWhere.append(this.checkAndKeyword(temp, new StringBuilder("BD.id = BRType. buildingid ")));
				sqlWhere.append(this.checkAndKeyword(temp,
						new StringBuilder("RType.id = " + condition.getListType().get(0) + " ")));
				if (condition.getListType().size() > 1) {
					for (int i = 1; i < condition.getListType().size(); i++) {
						sqlWhere.append(new StringBuilder("OR RType.id = " + condition.getListType().get(i) + " "));
					}
				}
			}
			String result = sqlFrom + sqlWhere.toString();
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

}
