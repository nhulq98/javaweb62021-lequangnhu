package com.laptrinhjavaweb.repository.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dto.input.BuildingRequest;
import com.laptrinhjavaweb.dto.output.BuildingResponse;
import com.laptrinhjavaweb.repository.jdbc.IBuildingJDBC;

public class BuildingJDBCImpl extends BaseJDBCImpl implements IBuildingJDBC {
	
	private Connection connection;
	private PreparedStatement prStatement;
	private ResultSet resultSet;

	@Override
	public List<BuildingResponse> findByCondition(BuildingRequest buildingRequest) {
		List<BuildingResponse> results = new ArrayList<>();
		try {
			this.connection = super.getConnection();
			this.connection.setAutoCommit(false);
			this.prStatement = this.connection.prepareStatement(this.buildQueryV2(buildingRequest));

			this.resultSet = this.prStatement.executeQuery();
			while (this.resultSet.next()) {
				// get All data from resultset
				BuildingResponse buildingResponse = new BuildingResponse();
				buildingResponse = convertResultSetToBuildingResponse(this.resultSet);
				results.add(buildingResponse);
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
	public String buildQueryV2(BuildingRequest buildingRequest) {
		try {
			String fromSQLClause = "SELECT * FROM building BD ";
			String joinSQLClause = this.buildJoinSQLClause(buildingRequest);
			String whereSQLClause = this.buildWhereSQLClause(buildingRequest);
			String groupByClause = " GROUP BY BD.id ";

			return (fromSQLClause + joinSQLClause + whereSQLClause.toString() + groupByClause);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String buildBetweenStatement(String whereSQLClause, Long from, Long to) {
		if(!this.isNull(from) || !this.isNull(to)) {
			if(!this.isNull(from) && !this.isNull(to)) {
				return (" AND "+ whereSQLClause +" BETWEEN " + from + " AND " + to + " ");
			}else if(!this.isNull(from) && this.isNull(to)) {
				return (" AND "+ whereSQLClause +" >= " + from + " ");
			}else {
				return (" AND "+ whereSQLClause +" <= " + to + " ");
			}
		}
		return "";
	}

	@Override
	public String buildWhereSQLClause(BuildingRequest buildingRequest) {
		StringBuilder whereSQLClause = new StringBuilder(" WHERE 1=1 ");// Use StringBuilder with purpose is saved memory
		
		whereSQLClause.append(this.checkExistenceOfConditionV2 (" AND BD.name LIKE '%", "%' ", buildingRequest.getName()));
		whereSQLClause.append(this.checkExistenceOfConditionV2 (" AND BD.street LIKE '%", "%' ", buildingRequest.getStreet()));
		whereSQLClause.append(this.checkExistenceOfConditionV2 (" AND BD.ward LIKE '%", "%' ", buildingRequest.getWard()));
		whereSQLClause.append(this.checkExistenceOfConditionV2 (" AND BD.districtid = ", " ", buildingRequest.getDistrictID()));
		whereSQLClause.append(this.checkExistenceOfConditionV2 (" AND BD.floorarea = ", " ", buildingRequest.getFloorArea()));
		whereSQLClause.append(this.checkExistenceOfConditionV2 (" AND BD.numberOfBasement = ", " ", buildingRequest.getNumberOfBasement()));
		whereSQLClause.append(this.checkExistenceOfConditionV2 (" AND BD.direction  LIKE '%", "%' ", buildingRequest.getDirection()));
		whereSQLClause.append(this.checkExistenceOfConditionV2 (" AND BD.Level LIKE '%", "%' ", buildingRequest.getLevel()));
		whereSQLClause.append(this.checkExistenceOfConditionV2 (" AND BD.managername LIKE '%", "%' ", buildingRequest.getManagerName()));
		whereSQLClause.append(this.checkExistenceOfConditionV2 (" AND BD.managerphone LIKE '%", "%' ", buildingRequest.getManagerPhone()));
		whereSQLClause.append(this.checkExistenceOfConditionV2 (" AND ASB.staffid = ", " ", buildingRequest.getUserID()));		
		whereSQLClause.append(this.buildBetweenStatement("BD.rentprice", buildingRequest.getRentPriceFrom(), buildingRequest.getRentPriceTo()));
		whereSQLClause.append(this.buildBetweenStatement("RE.value", buildingRequest.getRentEreaFrom(), buildingRequest.getRentEreaTo()));
		whereSQLClause.append(this.buildConditionForBuildingType(buildingRequest.getListType()));
		
		return whereSQLClause.toString();
	}

	@Override
	public String buildJoinSQLClause(BuildingRequest buildingRequest) {
		String joinSQLClause = " JOIN district DT on DT.id = BD.districtid ";
		
		String[] assignmentbuilding = {" JOIN assignmentbuilding ASB on  ASB.buildingid = BD.id "};
		joinSQLClause += this.checkExistenceOfJoinSQLClause(assignmentbuilding, 
				buildingRequest.getUserID());
		
		String[] rentarea = {" JOIN rentarea RE ON RE.buildingid = BD.id "};
		joinSQLClause += this.checkExistenceOfJoinSQLClause(rentarea, 
				buildingRequest.getRentEreaFrom(), buildingRequest.getRentEreaTo());
		
		String[] buildingrenttype = {" JOIN buildingrenttype BRT ON BRT.buildingid = BD.id ", 
				" JOIN renttype RT ON RT.id = BRT.renttypeid "};
		joinSQLClause += this.checkExistenceOfJoinSQLClause(buildingrenttype, buildingRequest.getListType());
		
		return joinSQLClause;
	}

	@Override
	public String buildConditionForBuildingType(List<String> buildingType) {
		String conditionForBuildingType = "";
		if (!this.isNull(buildingType)) {
			conditionForBuildingType += " AND RT.code = \"" + buildingType.get(0) + "\" ";
			if (buildingType.size() > 1) {
				for (int i = 1; i < buildingType.size(); i++) {
					conditionForBuildingType += " OR RT.code = \"" + buildingType.get(i) + "\" ";
				}
			}
		}
		return conditionForBuildingType;
	}

	@Override
	public String checkExistenceOfConditionV2(String prefix, String suffix, Object parameter) {
		if(parameter != null && !this.isBlank(parameter)) {
			return (prefix + parameter + suffix);
		}
		return "";
	}
	
	@Override
	public String checkExistenceOfJoinSQLClause(String[] joinStr, Object...parameters) {
		String joinClauseStr = "";
		for(Object obj: parameters) {
			if(obj != null) {
				for(String str: joinStr) {
					joinClauseStr += str;
				}
				return joinClauseStr;
			}
		}
		return null;
	}
	
	@Override
	public boolean isNull(Object value) {
		if(value == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isBlank(Object value) {
		if(value instanceof String && value == "") {
			return true;
		}
		return false;
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
	public String buildQuery(BuildingRequest buildingRequest) {
		try {
			boolean temp = false;// keyword AND not exists
			String sqlFromClause = "SELECT * FROM building BD ";
			//StringBuilder sqlFromClause = new StringBuilder("SELECT * FROM building BD ");
			StringBuilder whereSQLClause = new StringBuilder("");
			if (buildingRequest.getDistrictID() != null) {
				//sqlFromClause.append(", district DTrict");// insert table into from
				sqlFromClause += ", district DTrict";

				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("BD.districtid = DTrict.id ")));
				temp = true; // keyword AND exists
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("DTrict.id = " + buildingRequest.getDistrictID() + " ")));
				
			}
			if (buildingRequest.getName() != null) {
				whereSQLClause.append(
						this.checkAndKeyword(temp, new StringBuilder("BD.name LIKE '%" + buildingRequest.getName() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (buildingRequest.getRentEreaFrom() != null || buildingRequest.getRentEreaTo() != null) {
				//sqlFromClause.append(", rentarea RErea");
				sqlFromClause += ", rentarea RErea";
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("RErea.buildingid = BD.id ")));
				temp = true; // keyword AND exists
				if (buildingRequest.getRentEreaFrom() != null && buildingRequest.getRentEreaTo() != null) {
					whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("RErea.value BETWEEN "
							+ buildingRequest.getRentEreaFrom() + " " + buildingRequest.getRentEreaTo() + " ")));
				}
				if (buildingRequest.getRentEreaFrom() != null && buildingRequest.getRentEreaTo() == null) {
					whereSQLClause.append(this.checkAndKeyword(temp,
							new StringBuilder("RErea.value >= " + buildingRequest.getRentEreaFrom() + " ")));
				}
				if (buildingRequest.getRentEreaFrom() == null && buildingRequest.getRentEreaTo() != null) {
					whereSQLClause.append(this.checkAndKeyword(temp,
							new StringBuilder("RErea.value <= " + buildingRequest.getRentEreaTo() + " ")));
				}
			}
			if (buildingRequest.getUserID() != null) {
				//sqlFromClause.append(", user U, assignmentbuilding Abuilding");// insert table into from
				sqlFromClause += ", user U, assignmentbuilding Abuilding";
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("Abuilding.staffid = U.id ")));
				temp = true; // keyword AND exists
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("Abuilding. Buildingid = BD.id ")));
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("U.id = " + buildingRequest.getUserID() + " ")));
			}
			if (buildingRequest.getFloorArea() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.floorarea = " + buildingRequest.getFloorArea() + " ")));
				temp = true; // keyword AND exists
			}
			if (buildingRequest.getWard() != null) {
				whereSQLClause.append(
						this.checkAndKeyword(temp, new StringBuilder("BD.ward LIKE '%" + buildingRequest.getWard() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (buildingRequest.getStreet() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.street LIKE '%" + buildingRequest.getStreet() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (buildingRequest.getNumberOfBasement() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.numberOfBasement = " + buildingRequest.getNumberOfBasement() + " ")));
				temp = true; // keyword AND exists
			}
			if (buildingRequest.getRentPriceFrom() != null && buildingRequest.getRentPriceTo() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("BD.rentprice BETWEEN "
						+ buildingRequest.getRentPriceFrom() + " AND " + buildingRequest.getRentPriceTo() + " ")));
				temp = true; // keyword AND exists
			}
			if (buildingRequest.getRentPriceFrom() != null && buildingRequest.getRentPriceTo() == null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.rentprice >= " + buildingRequest.getRentPriceFrom() + " ")));
				temp = true; // keyword AND exists
			}
			if (buildingRequest.getRentPriceFrom() == null && buildingRequest.getRentPriceTo() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.rentprice <= " + buildingRequest.getRentPriceTo() + " ")));
				temp = true; // keyword AND exists
			}
			if (buildingRequest.getDirection() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.direction  LIKE '%" + buildingRequest.getDirection() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (buildingRequest.getLevel() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.Level LIKE '%" + buildingRequest.getLevel() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (buildingRequest.getManagerName() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.managername LIKE '%" + buildingRequest.getManagerName() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (buildingRequest.getManagerPhone() != null) {
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("BD.managerphone LIKE '%" + buildingRequest.getManagerPhone() + "%' ")));
				temp = true; // keyword AND exists
			}
			if (buildingRequest.getListType() != null) {
				//sqlFromClause.append(", renttype RType");// insert table into from
				sqlFromClause += ", renttype RType";
				//sqlFromClause.append(", buildingrenttype BRType");// insert table into from
				sqlFromClause += ", buildingrenttype BRType";
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("RType.id = BRType. Renttypeid ")));
				temp = true; // keyword AND exists
				whereSQLClause.append(this.checkAndKeyword(temp, new StringBuilder("BD.id = BRType. buildingid ")));
				whereSQLClause.append(this.checkAndKeyword(temp,
						new StringBuilder("RType.id = " + buildingRequest.getListType().get(0) + " ")));
				if (buildingRequest.getListType().size() > 1) {
					for (int i = 1; i < buildingRequest.getListType().size(); i++) {
						whereSQLClause.append(new StringBuilder("OR RType.id = " + buildingRequest.getListType().get(i) + " "));
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
	public String checkExistenceOfCondition (String conditionStr, Object parameter) {
		if(parameter != null && !this.isBlank(parameter)) {
			return conditionStr;
		}
		return "";
	}

	
	@Override
	public BuildingResponse convertResultSetToBuildingResponse(ResultSet resultSet) {
		try {
			BuildingResponse buildingResponse = new BuildingResponse();
			buildingResponse.setId(resultSet.getLong("id"));
			buildingResponse.setName(resultSet.getString("name"));
			buildingResponse.setDistrictID(resultSet.getLong("districtid"));
			buildingResponse.setStreet(resultSet.getString("street"));
			buildingResponse.setStructure(resultSet.getString("structure"));
			buildingResponse.setNumberOfBasement(resultSet.getLong("numberofbasement"));
			buildingResponse.setFloorArea(resultSet.getLong("floorarea"));
			buildingResponse.setDirection(resultSet.getString("direction"));
			buildingResponse.setLevel(resultSet.getString("level"));
			buildingResponse.setRentPrice(resultSet.getLong("rentPrice"));
			buildingResponse.setRentPriceDescription(resultSet.getString("rentPriceDescription"));
			buildingResponse.setServiceFee(resultSet.getString("serviceFee"));
			buildingResponse.setCarFee(resultSet.getString("carFee"));
			buildingResponse.setMotorbikeFee(resultSet.getString("motorbikeFee"));
			buildingResponse.setOvertimeFee(resultSet.getString("overtimeFee"));
			buildingResponse.setWaterFee(resultSet.getString("waterfee"));
			buildingResponse.setElectricityFee(resultSet.getString("electricityfee"));
			buildingResponse.setDeposit(resultSet.getString("deposit"));
			buildingResponse.setPayment(resultSet.getString("payment"));
			buildingResponse.setRentTime(resultSet.getString("renttime"));
			buildingResponse.setDecoratorTime(resultSet.getString("decorationtime"));
			buildingResponse.setBrokerageFee(resultSet.getString("brokeragefee"));
			buildingResponse.setNote(resultSet.getString("note"));
			buildingResponse.setLinkofbuilding(resultSet.getString("linkofbuilding"));
			buildingResponse.setMap(resultSet.getString("map"));
			buildingResponse.setImage(resultSet.getString("image"));
			buildingResponse.setManagerPhone(resultSet.getString("managerphone"));
			buildingResponse.setManagerName(resultSet.getString("managername"));

			buildingResponse.setCreatedDate(resultSet.getTimestamp("createddate"));
			buildingResponse.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			buildingResponse.setCreatedBy(resultSet.getString("createdby"));
			buildingResponse.setModifiedBy(resultSet.getString("modifiedby"));

			return buildingResponse;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
