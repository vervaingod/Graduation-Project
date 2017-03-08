package com.equipment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.equipment.bean.MaintenanceBean;
import com.equipment.bean.PageBean;
import com.equipment.util.DBUtil;

public class MaintenanceDaoImpl implements MaintenanceDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public int addMaintenance(MaintenanceBean maintenanceBean) throws Exception {
		int maintenance_id = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into maintenance(maintenance_department,maintenance_name,maintenance_phone,maintenance_time,maintenance_fail_type,maintenance_description,maintenance_dispatch_state,maintenance_accept_state,maintenance_confirm,maintenance_unit) values(?,?,?,?,?,?,0,0,0,?)";
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1,
				maintenanceBean.getMaintenance_department());
		preparedStatement.setString(2, maintenanceBean.getMaintenance_name());
		preparedStatement.setString(3, maintenanceBean.getMaintenance_phone());
		preparedStatement.setString(4, maintenanceBean.getMaintenance_time());
		preparedStatement.setString(5,
				maintenanceBean.getMaintenance_fail_type());
		preparedStatement.setString(6,
				maintenanceBean.getMaintenance_description());
		preparedStatement.setString(7, maintenanceBean.getMaintenance_unit());
		preparedStatement.executeUpdate();
		ResultSet rs = preparedStatement.getGeneratedKeys();
		if (rs.next()) {
			maintenance_id = rs.getInt(1);
		} else {
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return maintenance_id;
	}

	@Override
	public List<MaintenanceBean> fetchAllMaintenanceByUnit(
			String maintenance_unit, int pageno, int maintenance_id,
			String maintenance_department, String maintenance_fail_type,
			int maintenance_dispatch_state, String maintenance_time,
			int maintenance_accept_state, int maintenance_confirm)
			throws Exception {
		List<MaintenanceBean> maintenanceBeanlist = null;
		int startIndex = (pageno - 1) * PageBean.ROWS_PRO_PAGE;
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where maintenance_unit=? ";
		if (maintenance_id != 0) {
			sql = sql + " and maintenance_id=" + maintenance_id;
		}
		if (maintenance_department != "") {
			sql = sql + " and maintenance_department=" + "'"
					+ maintenance_department + "'";
		}
		if (maintenance_fail_type != "") {
			sql = sql + " and maintenance_fail_type=" + "'"
					+ maintenance_fail_type + "'";
		}
		if (maintenance_dispatch_state != 3) {
			sql = sql + " and maintenance_dispatch_state="
					+ maintenance_dispatch_state;
		}
		if (maintenance_time != "") {
			sql = sql + " and maintenance_time like" + "'" + maintenance_time
					+ "%'";
		}
		if (maintenance_accept_state != 3) {
			sql = sql + " and maintenance_accept_state="
					+ maintenance_accept_state;
		}
		if (maintenance_confirm != 3) {
			sql = sql + " and maintenance_confirm=" + maintenance_confirm;
		}
		sql = sql + " limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, maintenance_unit);
		preparedStatement.setInt(2, startIndex);
		preparedStatement.setInt(3, PageBean.ROWS_PRO_PAGE);
		resultSet = preparedStatement.executeQuery();
		maintenanceBeanlist = new ArrayList<>();
		while (resultSet.next()) {
			MaintenanceBean maintenanceBean = new MaintenanceBean();
			maintenanceBean.setMaintenance_id(resultSet
					.getInt("maintenance_id"));
			maintenanceBean.setMaintenance_department(resultSet
					.getString("maintenance_department"));
			maintenanceBean.setMaintenance_name(resultSet
					.getString("maintenance_name"));
			maintenanceBean.setMaintenance_phone(resultSet
					.getString("maintenance_phone"));
			maintenanceBean.setMaintenance_time(resultSet
					.getString("maintenance_time"));
			maintenanceBean.setMaintenance_fail_type(resultSet
					.getString("maintenance_fail_type"));
			maintenanceBean.setMaintenance_description(resultSet
					.getString("maintenance_description"));
			maintenanceBean.setMaintenance_dispatch_state(resultSet
					.getInt("maintenance_dispatch_state"));
			maintenanceBean.setMaintenance_accept_state(resultSet
					.getInt("maintenance_accept_state"));
			maintenanceBean.setMaintenance_confirm(resultSet
					.getInt("maintenance_confirm"));
			maintenanceBean.setMaintenance_record(resultSet
					.getInt("maintenance_record"));
			maintenanceBean.setMaintenance_equipment(resultSet
					.getString("maintenance_equipment"));
			maintenanceBean.setMaintenance_IP(resultSet
					.getString("maintenance_IP"));
			maintenanceBean.setMaintenance_MAC(resultSet
					.getString("maintenance_MAC"));
			maintenanceBean.setMaintenance_arrive_time(resultSet
					.getString("maintenance_arrive_time"));
			maintenanceBean.setMaintenance_completion(resultSet
					.getString("maintenance_completion"));
			maintenanceBean.setMaintenance_material_cost(resultSet
					.getInt("maintenance_material_cost"));
			maintenanceBean.setMaintenance_sum_cost(resultSet
					.getInt("maintenance_sum_cost"));
			maintenanceBeanlist.add(maintenanceBean);

		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return maintenanceBeanlist;
	}

	// @Override
	// public int updateMaintenanceByUsername(String department_username,
	// String department_name, String department_linkman,
	// String department_tel) throws Exception {
	// int rows = 0;
	// connection = dbUtil.getConnection();
	// String sql =
	// "update department set department_name=?,department_linkman=?,department_tel=?where department_username=?";
	// preparedStatement = connection.prepareStatement(sql);
	// preparedStatement.setString(1, department_name);
	// preparedStatement.setString(2, department_linkman);
	// preparedStatement.setString(3, department_tel);
	// preparedStatement.setString(4, department_username);
	// rows = preparedStatement.executeUpdate();
	// dbUtil.closeDBResource(connection, preparedStatement, resultSet);
	// return rows;
	// }

	@Override
	public MaintenanceBean findMaintenanceById(int maintenance_id)
			throws Exception {
		MaintenanceBean maintenanceBean = new MaintenanceBean();
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where maintenance_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, maintenance_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			maintenanceBean.setMaintenance_id(resultSet
					.getInt("maintenance_id"));
			maintenanceBean.setMaintenance_department(resultSet
					.getString("maintenance_department"));
			maintenanceBean.setMaintenance_unit(resultSet
					.getString("maintenance_unit"));
			maintenanceBean.setMaintenance_name(resultSet
					.getString("maintenance_name"));
			maintenanceBean.setMaintenance_phone(resultSet
					.getString("maintenance_phone"));
			maintenanceBean.setMaintenance_time(resultSet
					.getString("maintenance_time"));
			maintenanceBean.setMaintenance_fail_type(resultSet
					.getString("maintenance_fail_type"));
			maintenanceBean.setMaintenance_description(resultSet
					.getString("maintenance_description"));
			maintenanceBean.setMaintenance_dispatch_state(resultSet
					.getInt("maintenance_dispatch_state"));
			maintenanceBean.setMaintenance_accept_state(resultSet
					.getInt("maintenance_accept_state"));
			maintenanceBean.setMaintenance_confirm(resultSet
					.getInt("maintenance_confirm"));
			maintenanceBean.setMaintenance_record(resultSet
					.getInt("maintenance_record"));
			maintenanceBean.setMaintenance_equipment(resultSet
					.getString("maintenance_equipment"));
			maintenanceBean.setMaintenance_IP(resultSet
					.getString("maintenance_IP"));
			maintenanceBean.setMaintenance_MAC(resultSet
					.getString("maintenance_MAC"));
			maintenanceBean.setMaintenance_arrive_time(resultSet
					.getString("maintenance_arrive_time"));
			maintenanceBean.setMaintenance_completion(resultSet
					.getString("maintenance_completion"));
			maintenanceBean.setMaintenance_material_cost(resultSet
					.getInt("maintenance_material_cost"));
			maintenanceBean.setMaintenance_sum_cost(resultSet
					.getInt("maintenance_sum_cost"));
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return maintenanceBean;
	}

	@Override
	public List<MaintenanceBean> fetchAllMaintenanceByDepartment(
			String maintenance_department) throws Exception {
		List<MaintenanceBean> maintenanceBeanlist = null;
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where maintenance_department=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, maintenance_department);
		resultSet = preparedStatement.executeQuery();
		maintenanceBeanlist = new ArrayList<>();
		while (resultSet.next()) {
			MaintenanceBean maintenanceBean = new MaintenanceBean();
			maintenanceBean.setMaintenance_id(resultSet
					.getInt("maintenance_id"));
			maintenanceBean.setMaintenance_department(resultSet
					.getString("maintenance_department"));
			maintenanceBean.setMaintenance_name(resultSet
					.getString("maintenance_name"));
			maintenanceBean.setMaintenance_phone(resultSet
					.getString("maintenance_phone"));
			maintenanceBean.setMaintenance_time(resultSet
					.getString("maintenance_time"));
			maintenanceBean.setMaintenance_fail_type(resultSet
					.getString("maintenance_fail_type"));
			maintenanceBean.setMaintenance_description(resultSet
					.getString("maintenance_description"));
			maintenanceBean.setMaintenance_dispatch_state(resultSet
					.getInt("maintenance_dispatch_state"));
			maintenanceBean.setMaintenance_accept_state(resultSet
					.getInt("maintenance_accept_state"));
			maintenanceBean.setMaintenance_confirm(resultSet
					.getInt("maintenance_confirm"));
			maintenanceBean.setMaintenance_record(resultSet
					.getInt("maintenance_record"));
			maintenanceBean.setMaintenance_equipment(resultSet
					.getString("maintenance_equipment"));
			maintenanceBean.setMaintenance_IP(resultSet
					.getString("maintenance_IP"));
			maintenanceBean.setMaintenance_MAC(resultSet
					.getString("maintenance_MAC"));
			maintenanceBean.setMaintenance_arrive_time(resultSet
					.getString("maintenance_arrive_time"));
			maintenanceBean.setMaintenance_completion(resultSet
					.getString("maintenance_completion"));
			maintenanceBean.setMaintenance_material_cost(resultSet
					.getInt("maintenance_material_cost"));
			maintenanceBean.setMaintenance_sum_cost(resultSet
					.getInt("maintenance_sum_cost"));
			maintenanceBeanlist.add(maintenanceBean);

		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return maintenanceBeanlist;
	}

	@Override
	public int dispatch(int maintenance_id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update maintenance set maintenance_dispatch_state=1 where maintenance_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, maintenance_id);
		rows = preparedStatement.executeUpdate();
		return rows;
	}

	@Override
	public int fetchDispatchState(int maintenance_id) throws Exception {
		int maintenance_dispatch_state = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where maintenance_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, maintenance_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			maintenance_dispatch_state = resultSet
					.getInt("maintenance_dispatch_state");
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return maintenance_dispatch_state;
	}

	@Override
	public int updateMaintenanceByAccept(int maintenance_id,
			String maintenance_equipment, String maintenance_IP,
			String maintenance_MAC, int maintenance_record,
			int maintenance_sum_cost, String maintenance_completion)
			throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update maintenance set maintenance_equipment=?,maintenance_IP=?,maintenance_MAC=?,maintenance_record=?,maintenance_sum_cost=?,maintenance_completion=?,maintenance_accept_state=1 where maintenance_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, maintenance_equipment);
		preparedStatement.setString(2, maintenance_IP);
		preparedStatement.setString(3, maintenance_MAC);
		preparedStatement.setInt(4, maintenance_record);
		preparedStatement.setInt(5, maintenance_sum_cost);
		preparedStatement.setString(6, maintenance_completion);
		preparedStatement.setInt(7, maintenance_id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public List<MaintenanceBean> fetchAllConfirmByDepartment(
			String maintenance_department, int pageno, int maintenance_id,
			String maintenance_unit, String maintenance_fail_type,
			String maintenance_time, int maintenance_confirm) throws Exception {
		List<MaintenanceBean> maintenanceBeanlist = new ArrayList<MaintenanceBean>();
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where maintenance_department=? and maintenance_accept_state=1";
		if (maintenance_id != 0) {
			sql = sql + " and maintenance_id=" + maintenance_id;
		}
		if (maintenance_unit != "") {
			sql = sql + " and maintenance_unit=" + "'" + maintenance_unit + "'";
		}
		if (maintenance_fail_type != "") {
			sql = sql + " and maintenance_fail_type=" + "'"
					+ maintenance_fail_type + "'";
		}
		if (maintenance_time != "") {
			sql = sql + " and maintenance_time like" + "'" + maintenance_time
					+ "%'";
		}
		if (maintenance_confirm != 3) {
			sql = sql + " and maintenance_confirm=" + maintenance_confirm;
		}
		sql = sql + " limit ?,?";
		int startIndex = (pageno - 1) * PageBean.ROWS_PRO_PAGE;
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, maintenance_department);
		preparedStatement.setInt(2, startIndex);
		preparedStatement.setInt(3, PageBean.ROWS_PRO_PAGE);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			MaintenanceBean maintenanceBean = new MaintenanceBean();
			maintenanceBean.setMaintenance_id(resultSet
					.getInt("maintenance_id"));
			maintenanceBean.setMaintenance_department(resultSet
					.getString("maintenance_department"));
			maintenanceBean.setMaintenance_name(resultSet
					.getString("maintenance_name"));
			maintenanceBean.setMaintenance_phone(resultSet
					.getString("maintenance_phone"));
			maintenanceBean.setMaintenance_time(resultSet
					.getString("maintenance_time"));
			maintenanceBean.setMaintenance_fail_type(resultSet
					.getString("maintenance_fail_type"));
			maintenanceBean.setMaintenance_description(resultSet
					.getString("maintenance_description"));
			maintenanceBean.setMaintenance_dispatch_state(resultSet
					.getInt("maintenance_dispatch_state"));
			maintenanceBean.setMaintenance_accept_state(resultSet
					.getInt("maintenance_accept_state"));
			maintenanceBean.setMaintenance_confirm(resultSet
					.getInt("maintenance_confirm"));
			maintenanceBean.setMaintenance_record(resultSet
					.getInt("maintenance_record"));
			maintenanceBean.setMaintenance_equipment(resultSet
					.getString("maintenance_equipment"));
			maintenanceBean.setMaintenance_IP(resultSet
					.getString("maintenance_IP"));
			maintenanceBean.setMaintenance_MAC(resultSet
					.getString("maintenance_MAC"));
			maintenanceBean.setMaintenance_arrive_time(resultSet
					.getString("maintenance_arrive_time"));
			maintenanceBean.setMaintenance_completion(resultSet
					.getString("maintenance_completion"));
			maintenanceBean.setMaintenance_material_cost(resultSet
					.getInt("maintenance_material_cost"));
			maintenanceBean.setMaintenance_sum_cost(resultSet
					.getInt("maintenance_sum_cost"));
			maintenanceBean.setMaintenance_unit(resultSet
					.getString("maintenance_unit"));
			maintenanceBeanlist.add(maintenanceBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return maintenanceBeanlist;
	}

	@Override
	public int confirmMaintenanceByMaintenanceID(int maintenance_id)
			throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update maintenance set maintenance_confirm=1 where maintenance_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, maintenance_id);
		rows = preparedStatement.executeUpdate();
		return rows;
	}

	@Override
	public int fetchConfirmStateByMaintenanceID(int maintenance_id)
			throws Exception {
		int maintenance_confirm = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where maintenance_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, maintenance_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			maintenance_confirm = resultSet.getInt("maintenance_confirm");
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return maintenance_confirm;
	}

	@Override
	public List<MaintenanceBean> fetchAllMaintenance(int pageno,
			int maintenance_id, String maintenance_department,
			String maintenance_fail_type, int maintenance_dispatch_state,
			String maintenance_time, int maintenance_accept_state,
			int maintenance_confirm, String maintenance_unit) throws Exception {
		List<MaintenanceBean> maintenanceBeanlist = null;
		int startIndex = (pageno - 1) * PageBean.ROWS_PRO_PAGE;
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where 1=1";
		if (maintenance_id != 0) {
			sql = sql + " and maintenance_id=" + maintenance_id;
		}
		if (maintenance_department != "") {
			sql = sql + " and maintenance_department=" + "'"
					+ maintenance_department + "'";
		}
		if (maintenance_fail_type != "") {
			sql = sql + " and maintenance_fail_type=" + "'"
					+ maintenance_fail_type + "'";
		}
		if (maintenance_dispatch_state != 3) {
			sql = sql + " and maintenance_dispatch_state="
					+ maintenance_dispatch_state;
		}

		if (maintenance_time != "") {
			sql = sql + " and maintenance_time like " + "'" + maintenance_time
					+ "%'";
		}
		if (maintenance_accept_state != 3) {
			sql = sql + " and maintenance_accept_state="
					+ maintenance_accept_state;
		}
		if (maintenance_confirm != 3) {
			sql = sql + " and maintenance_confirm=" + maintenance_confirm;
		}
		if (maintenance_unit != "") {
			sql = sql + " and maintenance_unit=" + "'" + maintenance_unit + "'";
		}
		sql = sql + "  limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRO_PAGE);
		resultSet = preparedStatement.executeQuery();
		maintenanceBeanlist = new ArrayList<>();
		while (resultSet.next()) {
			MaintenanceBean maintenanceBean = new MaintenanceBean();
			maintenanceBean.setMaintenance_id(resultSet
					.getInt("maintenance_id"));
			maintenanceBean.setMaintenance_department(resultSet
					.getString("maintenance_department"));
			maintenanceBean.setMaintenance_name(resultSet
					.getString("maintenance_name"));
			maintenanceBean.setMaintenance_phone(resultSet
					.getString("maintenance_phone"));
			maintenanceBean.setMaintenance_time(resultSet
					.getString("maintenance_time"));
			maintenanceBean.setMaintenance_fail_type(resultSet
					.getString("maintenance_fail_type"));
			maintenanceBean.setMaintenance_description(resultSet
					.getString("maintenance_description"));
			maintenanceBean.setMaintenance_dispatch_state(resultSet
					.getInt("maintenance_dispatch_state"));
			maintenanceBean.setMaintenance_accept_state(resultSet
					.getInt("maintenance_accept_state"));
			maintenanceBean.setMaintenance_confirm(resultSet
					.getInt("maintenance_confirm"));
			maintenanceBean.setMaintenance_record(resultSet
					.getInt("maintenance_record"));
			maintenanceBean.setMaintenance_equipment(resultSet
					.getString("maintenance_equipment"));
			maintenanceBean.setMaintenance_IP(resultSet
					.getString("maintenance_IP"));
			maintenanceBean.setMaintenance_MAC(resultSet
					.getString("maintenance_MAC"));
			maintenanceBean.setMaintenance_arrive_time(resultSet
					.getString("maintenance_arrive_time"));
			maintenanceBean.setMaintenance_completion(resultSet
					.getString("maintenance_completion"));
			maintenanceBean.setMaintenance_material_cost(resultSet
					.getInt("maintenance_material_cost"));
			maintenanceBean.setMaintenance_sum_cost(resultSet
					.getInt("maintenance_sum_cost"));
			maintenanceBeanlist.add(maintenanceBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return maintenanceBeanlist;
	}

	@Override
	public int fetchAcceptStateByMaintenanceID(int maintenance_id)
			throws Exception {
		int maintenance_accept_state = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from maintenance where maintenance_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, maintenance_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			maintenance_accept_state = resultSet
					.getInt("maintenance_accept_state");
		}
		return maintenance_accept_state;
	}

	@Override
	public int countAllMaintenance(int maintenance_id,
			String maintenance_department, String maintenance_fail_type,
			int maintenance_dispatch_state, String maintenance_time,
			int maintenance_accept_state, int maintenance_confirm,
			String maintenance_unit) throws Exception {
		int maintenancerows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from maintenance where 1=1";
		if (maintenance_id != 0) {
			sql = sql + " and maintenance_id=" + maintenance_id;
		}
		if (maintenance_department != "") {
			sql = sql + " and maintenance_department=" + "'"
					+ maintenance_department + "'";
		}
		if (maintenance_fail_type != "") {
			sql = sql + " and maintenance_fail_type=" + "'"
					+ maintenance_fail_type + "'";
		}
		if (maintenance_dispatch_state != 3) {
			sql = sql + " and maintenance_dispatch_state="
					+ maintenance_dispatch_state;
		}
		if (maintenance_time != "") {
			sql = sql + " and maintenance_time like " + "'" + maintenance_time
					+ "%'";
		}
		if (maintenance_accept_state != 3) {
			sql = sql + " and maintenance_accept_state="
					+ maintenance_accept_state;
		}
		if (maintenance_confirm != 3) {
			sql = sql + " and maintenance_confirm=" + maintenance_confirm;
		}
		if (maintenance_unit != "") {
			sql = sql + " and maintenance_unit=" + "'" + maintenance_unit + "'";
		}
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			maintenancerows = resultSet.getInt(1);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return maintenancerows;
	}

	@Override
	public int countAllMaintenanceByUnit(String maintenance_unit,
			int maintenance_id, String maintenance_department,
			String maintenance_fail_type, int maintenance_dispatch_state,
			String maintenance_time, int maintenance_accept_state,
			int maintenance_confirm) throws Exception {
		int maintenancerows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from maintenance where maintenance_unit=?";
		if (maintenance_id != 0) {
			sql = sql + " and maintenance_id=" + maintenance_id;
		}
		if (maintenance_department != "") {
			sql = sql + " and maintenance_department=" + "'"
					+ maintenance_department + "'";
		}
		if (maintenance_fail_type != "") {
			sql = sql + " and maintenance_fail_type=" + "'"
					+ maintenance_fail_type + "'";
		}
		if (maintenance_dispatch_state != 3) {
			sql = sql + " and maintenance_dispatch_state="
					+ maintenance_dispatch_state;
		}
		if (maintenance_time != "") {
			sql = sql + " and maintenance_time like" + "'" + maintenance_time
					+ "%'";
		}
		if (maintenance_accept_state != 3) {
			sql = sql + " and maintenance_accept_state="
					+ maintenance_accept_state;
		}
		if (maintenance_confirm != 3) {
			sql = sql + " and maintenance_confirm=" + maintenance_confirm;
		}
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, maintenance_unit);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			maintenancerows = resultSet.getInt(1);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return maintenancerows;
	}

	@Override
	public int countAllConfirmByDepartment(String maintenance_department,
			int maintenance_id, String maintenance_unit,
			String maintenance_fail_type, String maintenance_time,
			int maintenance_confirm) throws Exception {
		int confirmrows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from maintenance where maintenance_department=? and maintenance_accept_state=1";
		if (maintenance_id != 0) {
			sql = sql + " and maintenance_id=" + maintenance_id;
		}
		if (maintenance_unit != "") {
			sql = sql + " and maintenance_unit=" + "'" + maintenance_unit + "'";
		}
		if (maintenance_fail_type != "") {
			sql = sql + " and maintenance_fail_type=" + "'"
					+ maintenance_fail_type + "'";
		}
		if (maintenance_time != "") {
			sql = sql + " and maintenance_time like" + "'" + maintenance_time
					+ "%'";
		}
		if (maintenance_confirm != 3) {
			sql = sql + " and maintenance_confirm=" + maintenance_confirm;
		}
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, maintenance_department);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			confirmrows = resultSet.getInt(1);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return confirmrows;
	}

	@Override
	public int invalidDispatchByMaintenanceID(int maintenance_id)
			throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update maintenance set maintenance_dispatch_state=-1 where maintenance_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, maintenance_id);
		rows = preparedStatement.executeUpdate();
		return rows;
	}
}
