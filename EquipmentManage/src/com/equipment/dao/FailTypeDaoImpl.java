package com.equipment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.equipment.bean.FailTypeBean;
import com.equipment.bean.PageBean;
import com.equipment.util.DBUtil;

public class FailTypeDaoImpl implements FailTypeDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public List<FailTypeBean> fillAllFailType(int pageno) throws Exception {
		List<FailTypeBean> failTypeBeanlist = new ArrayList<FailTypeBean>();
		connection = dbUtil.getConnection();
		String sql = "select * from fail_type limit ?,?";
		int startIndex = (pageno - 1) * PageBean.ROWS_PRO_PAGE;
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRO_PAGE);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			FailTypeBean failTypeBean = new FailTypeBean();
			failTypeBean.setFail_type_id(resultSet.getInt("fail_type_id"));
			failTypeBean.setFail_type_name(resultSet
					.getString("fail_type_name"));
			failTypeBean.setFail_type_service_fee(resultSet
					.getString("fail_type_service_fee"));
			failTypeBeanlist.add(failTypeBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return failTypeBeanlist;
	}

	@Override
	public int updateFailTypeById(int fail_type_id, String fail_type_name,
			String fail_type_service_fee) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update fail_type set fail_type_name=?,fail_type_service_fee=? where fail_type_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, fail_type_name);
		preparedStatement.setString(2, fail_type_service_fee);
		preparedStatement.setInt(3, fail_type_id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public FailTypeBean fetchFailTypeById(int fail_type_id) throws Exception {
		FailTypeBean failTypeBean = new FailTypeBean();
		connection = dbUtil.getConnection();
		String sql = "select * from fail_type where fail_type_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, fail_type_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			failTypeBean.setFail_type_name(resultSet
					.getString("fail_type_name"));
			failTypeBean.setFail_type_service_fee(resultSet
					.getString("fail_type_service_fee"));
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return failTypeBean;
	}

	@Override
	public int addFailType(String fail_type_name, String fail_type_service_fee)
			throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into fail_type (fail_type_name,fail_type_service_fee) values(?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, fail_type_name);
		preparedStatement.setString(2, fail_type_service_fee);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int countFailTypeByName(String fail_type_name) throws Exception {
		int count = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from fail_type where fail_type_name=? ";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, fail_type_name);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			count++;
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return count;
	}

	@Override
	public int deleteFailTypeById(int id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from fail_type where fail_type_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		rows = preparedStatement.executeUpdate();
		return rows;
	}

	@Override
	public int countFailType() throws Exception {
		int failtyperows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from fail_type";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			failtyperows = resultSet.getInt(1);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return failtyperows;
	}

	@Override
	public List<FailTypeBean> fetchAllFailType() throws Exception {
		List<FailTypeBean> failTypeBeanlist = new ArrayList<FailTypeBean>();
		connection = dbUtil.getConnection();
		String sql = "select * from fail_type";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			FailTypeBean failTypeBean = new FailTypeBean();
			failTypeBean.setFail_type_id(resultSet.getInt("fail_type_id"));
			failTypeBean.setFail_type_name(resultSet
					.getString("fail_type_name"));
			failTypeBean.setFail_type_service_fee(resultSet
					.getString("fail_type_service_fee"));
			failTypeBeanlist.add(failTypeBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return failTypeBeanlist;

	}
}
