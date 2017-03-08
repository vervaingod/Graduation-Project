package com.equipment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.equipment.bean.CostBean;
import com.equipment.util.DBUtil;

public class CostDaoImpl implements CostDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public int addCost(String cost_project, String cost_service,
			String cost_material, int cost_maintenance_id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into cost(cost_project,cost_service,cost_material,cost_maintenance_id)values(?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, cost_project);
		preparedStatement.setString(2, cost_service);
		preparedStatement.setString(3, cost_material);
		preparedStatement.setInt(4, cost_maintenance_id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public List<CostBean> fetchAllCostByMaintenanceID(int cost_maintenance_id)
			throws Exception {
		List<CostBean> costBeanlist = new ArrayList<CostBean>();
		connection = dbUtil.getConnection();
		String sql = "select * from cost where cost_maintenance_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, cost_maintenance_id);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			CostBean costBean = new CostBean();
			costBean.setCost_id(resultSet.getInt("cost_id"));
			costBean.setCost_project(resultSet.getString("cost_project"));
			costBean.setCost_service(resultSet.getString("cost_service"));
			costBean.setCost_material(resultSet.getString("cost_material"));
			costBean.setCost_maintenance_id(resultSet
					.getInt("cost_maintenance_id"));
			costBeanlist.add(costBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return costBeanlist;
	}

	@Override
	public int countSumCost(int cost_maintenance_id) throws Exception {
		int sum = 0;
		int cost_service = 0;
		int cost_material = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from cost where cost_maintenance_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, cost_maintenance_id);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			if (resultSet.getString("cost_service") == null) {
				cost_service = 0;
			} else {
				cost_service = Integer.parseInt(resultSet
						.getString("cost_service"));
			}
			if (resultSet.getString("cost_material") == null) {
				cost_material = 0;
			} else {
				cost_material = Integer.parseInt(resultSet
						.getString("cost_material"));
			}

			sum = sum + cost_service + cost_material;
		}

		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return sum;
	}

	@Override
	public int deleteCostByID(int cost_id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from cost where cost_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, cost_id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}
}
