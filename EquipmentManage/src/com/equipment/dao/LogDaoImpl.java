package com.equipment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.equipment.bean.LogBean;
import com.equipment.util.DBUtil;

public class LogDaoImpl implements LogDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public int addLog(LogBean logBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into log(log_operation,log_content,log_people,log_time,log_maintenance_id) values(?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, logBean.getLog_operation());
		preparedStatement.setString(2, logBean.getLog_content());
		preparedStatement.setString(3, logBean.getLog_people());
		preparedStatement.setString(4, logBean.getLog_time());
		preparedStatement.setInt(5, logBean.getLog_maintenance_id());
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public List<LogBean> fetchAllLogByMaintenanceId(int log_maintenance_id)
			throws Exception {
		List<LogBean> logBeanlist = new ArrayList<LogBean>();
		connection = dbUtil.getConnection();
		String sql = "select * from log where log_maintenance_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, log_maintenance_id);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			LogBean logBean = new LogBean();
			logBean.setLog_id(resultSet.getInt("log_id"));
			logBean.setLog_operation(resultSet.getString("log_operation"));
			logBean.setLog_content(resultSet.getString("log_content"));
			logBean.setLog_people(resultSet.getString("log_people"));
			logBean.setLog_time(resultSet.getString("log_time"));
			logBeanlist.add(logBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return logBeanlist;
	}
}
