package com.equipment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.equipment.bean.DispatchBean;
import com.equipment.bean.PageBean;
import com.equipment.util.DBUtil;

public class DispatchDaoImpl implements DispatchDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public int addDispatch(int dispatch_user_id, int maintenance_id,
			String dispatch_maintenance_phone, String dispatch_expect_time)
			throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into dispatch (dispatch_user_id,dispatch_maintenance_id,dispatch_expect_time,dispatch_maintenance_phone) values(?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, dispatch_user_id);
		preparedStatement.setInt(2, maintenance_id);
		preparedStatement.setString(3, dispatch_expect_time);
		preparedStatement.setString(4, dispatch_maintenance_phone);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public List<DispatchBean> fetchAllDispatchByUserId(int dispatch_user_id,
			int pageno) throws Exception {
		List<DispatchBean> dispatchBeanlist = new ArrayList<DispatchBean>();
		int startIndex = (pageno - 1) * PageBean.ROWS_PRO_PAGE;
		connection = dbUtil.getConnection();
		String sql = "select * from dispatch where dispatch_user_id=? limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, dispatch_user_id);
		preparedStatement.setInt(2, startIndex);
		preparedStatement.setInt(3, PageBean.ROWS_PRO_PAGE);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			DispatchBean dispatchBean = new DispatchBean();
			dispatchBean.setDispatch_id(resultSet.getInt("dispatch_id"));
			dispatchBean.setDispatch_user_id(resultSet
					.getInt("dispatch_user_id"));
			dispatchBean.setDispatch_maintenance_id(resultSet
					.getInt("dispatch_maintenance_id"));
			dispatchBean.setDispatch_expect_time(resultSet
					.getString("dispatch_expect_time"));
			dispatchBean.setDispatch_maintenance_phone(resultSet
					.getString("dispatch_maintenance_phone"));
			dispatchBeanlist.add(dispatchBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return dispatchBeanlist;
	}

	@Override
	public DispatchBean fetchDispatchByMaintenanceId(int dispatch_maintenance_id)
			throws Exception {
		DispatchBean dispatchBean = new DispatchBean();
		connection = dbUtil.getConnection();
		String sql = "select * from dispatch where dispatch_maintenance_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, dispatch_maintenance_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			dispatchBean.setDispatch_id(resultSet.getInt("dispatch_id"));
			dispatchBean.setDispatch_user_id(resultSet
					.getInt("dispatch_user_id"));
			dispatchBean.setDispatch_maintenance_id(resultSet
					.getInt("dispatch_maintenance_id"));
			dispatchBean.setDispatch_expect_time(resultSet
					.getString("dispatch_expect_time"));
			dispatchBean.setDispatch_maintenance_phone(resultSet
					.getString("dispatch_maintenance_phone"));
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return dispatchBean;
	}

	@Override
	public int countAllDispatchByUserId(int dispatch_user_id,
			int maintenance_id, String maintenance_department,
			String maintenance_fail_type, String maintenance_time,
			int maintenance_accept_state, int maintenance_confirm)
			throws Exception {
		int dispatchrows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from dispatch where dispatch_user_id=?";
//		if (maintenance_id != 3) {
//			sql = sql + " and maintenance_id=" + maintenance_id;
//		}
//		if (maintenance_department != "") {
//			sql = sql + " and maintenance_department=" + "'"
//					+ maintenance_department + "'";
//		}
//		if (maintenance_fail_type != "") {
//			sql = sql + " and maintenance_fail_type=" + "'"
//					+ maintenance_fail_type + "'";
//		}
//		if (maintenance_time != "") {
//			sql = sql + " and maintenance_time like" + "'" + maintenance_time
//					+ "%'";
//		}
//		if (maintenance_accept_state != 3) {
//			sql = sql + " and maintenance_accept_state="
//					+ maintenance_accept_state;
//		}
//		if (maintenance_confirm != 3) {
//			sql = sql + " and maintenance_confirm=" + maintenance_confirm;
//		}

		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, dispatch_user_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			dispatchrows = resultSet.getInt(1);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return dispatchrows;
	}
}
