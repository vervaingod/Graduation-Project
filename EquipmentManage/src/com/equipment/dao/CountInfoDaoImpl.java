package com.equipment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.equipment.util.DBUtil;

public class CountInfoDaoImpl implements CountInfoDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public int countSum() throws Exception {
		int count_sum = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			count_sum++;
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return count_sum;
	}

	@Override
	public int countSentWorker() throws Exception {
		int count_sent_worker = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where maintenance_dispatch_state=1";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			count_sent_worker++;
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return count_sent_worker;
	}

	@Override
	public int countCancellation() throws Exception {
		int count_cancellation = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where maintenance_dispatch_state=-1";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			count_cancellation++;
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return count_cancellation;
	}

	@Override
	public int countWaitAccept() throws Exception {
		int count_wait_accept = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where maintenance_accept_state=0";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			count_wait_accept++;
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return count_wait_accept;
	}

	@Override
	public int countAccepted() throws Exception {
		int count_accepted = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where maintenance_accept_state=1";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			count_accepted++;
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return count_accepted;
	}

	@Override
	public int countWaitConfirm() throws Exception {
		int count_wait_confirm = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where maintenance_confirm=0";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			count_wait_confirm++;
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return count_wait_confirm;
	}

	@Override
	public int countConfirmed() throws Exception {
		int count_confirmed = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where maintenance_confirm=1";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			count_confirmed++;
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return count_confirmed;
	}
}
