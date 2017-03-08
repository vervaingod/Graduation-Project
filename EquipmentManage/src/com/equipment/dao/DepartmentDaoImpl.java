package com.equipment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.equipment.bean.DepartmentBean;
import com.equipment.bean.PageBean;
import com.equipment.util.DBUtil;

public class DepartmentDaoImpl implements DepartmentDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public List<DepartmentBean> fetchAllLocalPoliceStation(int pageno)
			throws Exception {
		List<DepartmentBean> departmentBeanlist = new ArrayList<DepartmentBean>();
		connection = dbUtil.getConnection();
		String sql = "select * from department where department_type=2 limit ?,?";
		int startIndex = (pageno - 1) * PageBean.ROWS_PRO_PAGE;
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRO_PAGE);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			DepartmentBean departmentBean = new DepartmentBean();
			departmentBean.setDepartment_id(resultSet.getInt("department_id"));
			departmentBean.setDepartment_name(resultSet
					.getString("department_name"));
			departmentBean.setDepartment_tel(resultSet
					.getString("department_tel"));
			departmentBean.setDepartment_linkman(resultSet
					.getString("department_linkman"));
			departmentBean.setDepartment_username(resultSet
					.getString("department_username"));
			departmentBean.setDepartment_type(resultSet
					.getInt("department_type"));
			departmentBeanlist.add(departmentBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return departmentBeanlist;
	}

	@Override
	public List<DepartmentBean> fetchAllMaintainingUnit(int pageno)
			throws Exception {
		List<DepartmentBean> departmentBeanlist = new ArrayList<DepartmentBean>();
		connection = dbUtil.getConnection();
		String sql = "select * from department where department_type=3 limit ?,?";
		int startIndex = (pageno - 1) * PageBean.ROWS_PRO_PAGE;
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRO_PAGE);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			DepartmentBean departmentBean = new DepartmentBean();
			departmentBean.setDepartment_id(resultSet.getInt("department_id"));
			departmentBean.setDepartment_name(resultSet
					.getString("department_name"));
			departmentBean.setDepartment_tel(resultSet
					.getString("department_tel"));
			departmentBean.setDepartment_linkman(resultSet
					.getString("department_linkman"));
			departmentBean.setDepartment_username(resultSet
					.getString("department_username"));
			departmentBean.setDepartment_type(resultSet
					.getInt("department_type"));
			departmentBeanlist.add(departmentBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return departmentBeanlist;
	}

	@Override
	public String findDepartmentById(int user_department_id) throws Exception {
		String department = null;
		connection = dbUtil.getConnection();
		String sql = "select * from department where department_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user_department_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			department = resultSet.getString("department_name");
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return department;
	}

	@Override
	public DepartmentBean fetchDepartmentById(int department_id)
			throws Exception {
		DepartmentBean departmentBean = new DepartmentBean();
		connection = dbUtil.getConnection();
		String sql = "select * from department where department_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, department_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			departmentBean.setDepartment_name(resultSet
					.getString("department_name"));
			departmentBean.setDepartment_linkman(resultSet
					.getString("department_linkman"));
			departmentBean.setDepartment_username(resultSet
					.getString("department_username"));
			departmentBean.setDepartment_tel(resultSet
					.getString("department_tel"));
			departmentBean.setDepartment_type(resultSet
					.getInt("department_type"));
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return departmentBean;
	}

	@Override
	public int updateDepartmentByUsername(String department_username,
			String department_name, String department_linkman,
			String department_tel) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update department set department_name=?,department_linkman=?,department_tel=?where department_username=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, department_name);
		preparedStatement.setString(2, department_linkman);
		preparedStatement.setString(3, department_tel);
		preparedStatement.setString(4, department_username);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int addDepartment(DepartmentBean departmentBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into department(department_name,department_linkman,department_username,department_tel,department_type) values(?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, departmentBean.getDepartment_name());
		preparedStatement.setString(2, departmentBean.getDepartment_linkman());
		preparedStatement.setString(3, departmentBean.getDepartment_username());
		preparedStatement.setString(4, departmentBean.getDepartment_tel());
		preparedStatement.setInt(5, departmentBean.getDepartment_type());
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int countDepartmentByname(String department_name) throws Exception {
		int count = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from department where department_name=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, department_name);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			count++;
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return count;
	}

	@Override
	public int findDepartmentIdByname(String department_name) throws Exception {
		int department_id = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from department where department_name=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, department_name);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			department_id = resultSet.getInt("department_id");
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return department_id;
	}

	@Override
	public int countLocalPoliceStation() throws Exception {
		int localpolicestationrows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from department where department_type=2";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			localpolicestationrows = resultSet.getInt(1);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return localpolicestationrows;
	}

	@Override
	public List<DepartmentBean> fetchAllPoliceStation() throws Exception {
		List<DepartmentBean> departmentBeanlist = new ArrayList<DepartmentBean>();
		connection = dbUtil.getConnection();
		String sql = "select * from department where department_type=2";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			DepartmentBean departmentBean = new DepartmentBean();
			departmentBean.setDepartment_id(resultSet.getInt("department_id"));
			departmentBean.setDepartment_name(resultSet
					.getString("department_name"));
			departmentBean.setDepartment_tel(resultSet
					.getString("department_tel"));
			departmentBean.setDepartment_linkman(resultSet
					.getString("department_linkman"));
			departmentBean.setDepartment_username(resultSet
					.getString("department_username"));
			departmentBean.setDepartment_type(resultSet
					.getInt("department_type"));
			departmentBeanlist.add(departmentBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return departmentBeanlist;

	}

	@Override
	public int countMaintainingUnit() throws Exception {
		int localpolicestationrows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from department where department_type=3";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			localpolicestationrows = resultSet.getInt(1);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return localpolicestationrows;
	}

	@Override
	public List<DepartmentBean> fetchAllUnit() throws Exception {
		List<DepartmentBean> departmentBeanlist = new ArrayList<DepartmentBean>();
		connection = dbUtil.getConnection();
		String sql = "select * from department where department_type=3";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			DepartmentBean departmentBean = new DepartmentBean();
			departmentBean.setDepartment_id(resultSet.getInt("department_id"));
			departmentBean.setDepartment_name(resultSet
					.getString("department_name"));
			departmentBean.setDepartment_tel(resultSet
					.getString("department_tel"));
			departmentBean.setDepartment_linkman(resultSet
					.getString("department_linkman"));
			departmentBean.setDepartment_username(resultSet
					.getString("department_username"));
			departmentBean.setDepartment_type(resultSet
					.getInt("department_type"));
			departmentBeanlist.add(departmentBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return departmentBeanlist;

	}

}
