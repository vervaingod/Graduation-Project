package com.equipment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.equipment.bean.PageBean;
import com.equipment.bean.PeopleBean;
import com.equipment.util.DBUtil;

public class PeopleDaoImpl implements PeopleDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public PeopleBean login(PeopleBean peopleBean) throws Exception {
		PeopleBean peopleBean2 = new PeopleBean();
		connection = dbUtil.getConnection();
		String sql = "select * from user where user_username=? and user_password=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, peopleBean.getUser_username());
		preparedStatement.setString(2, peopleBean.getUser_password());
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			peopleBean2.setUser_id(resultSet.getInt("user_id"));
			peopleBean2.setUser_username(resultSet.getString("user_username"));
			peopleBean2.setUser_password(resultSet.getString("user_password"));
			peopleBean2.setUser_name(resultSet.getString("user_name"));
			peopleBean2
					.setUser_copyright(resultSet.getString("user_copyright"));
			peopleBean2.setUser_phone(resultSet.getString("user_phone"));
			peopleBean2.setUser_role(resultSet.getInt("user_role"));
			peopleBean2.setUser_technical_support(resultSet
					.getString("user_technical_support"));
			peopleBean2.setUser_department_id(resultSet
					.getInt("user_department_id"));
		}

		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return peopleBean2;
	}

	@Override
	public List<PeopleBean> FindAllUserByRole(int findrole, int pageno)
			throws Exception {
		List<PeopleBean> peopleBeanlist = null;
		int startIndex = (pageno - 1) * PageBean.ROWS_PRO_PAGE;
		connection = dbUtil.getConnection();
		String sql = "select * from user where user_role=? limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, findrole);
		preparedStatement.setInt(2, startIndex);
		preparedStatement.setInt(3, PageBean.ROWS_PRO_PAGE);
		resultSet = preparedStatement.executeQuery();
		peopleBeanlist = new ArrayList<>();
		while (resultSet.next()) {
			PeopleBean peopleBean = new PeopleBean();
			peopleBean.setUser_id(resultSet.getInt("user_id"));
			peopleBean.setUser_username(resultSet.getString("user_username"));
			peopleBean.setUser_password(resultSet.getString("user_password"));
			peopleBean.setUser_copyright(resultSet.getString("user_copyright"));
			peopleBean.setUser_technical_support(resultSet
					.getString("user_technical_support"));
			peopleBean.setUser_name(resultSet.getString("user_name"));
			peopleBean.setUser_phone(resultSet.getString("user_phone"));
			peopleBean.setUser_role(resultSet.getInt("user_role"));
			peopleBean.setUser_department_id(resultSet
					.getInt("user_department_id"));
			peopleBeanlist.add(peopleBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return peopleBeanlist;
	}

	@Override
	public List<PeopleBean> FindAllUserByDepeartment(int department_id,
			int pageno) throws Exception {
		List<PeopleBean> peopleBeanlist = null;
		int startIndex = (pageno - 1) * PageBean.ROWS_PRO_PAGE;
		connection = dbUtil.getConnection();
		String sql = "select * from user where user_department_id=? and user_role<>10 and user_role<>20 limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, department_id);
		preparedStatement.setInt(2, startIndex);
		preparedStatement.setInt(3, PageBean.ROWS_PRO_PAGE);
		resultSet = preparedStatement.executeQuery();
		peopleBeanlist = new ArrayList<>();
		while (resultSet.next()) {
			PeopleBean peopleBean = new PeopleBean();
			peopleBean.setUser_id(resultSet.getInt("user_id"));
			peopleBean.setUser_username(resultSet.getString("user_username"));
			peopleBean.setUser_password(resultSet.getString("user_password"));
			peopleBean.setUser_department_id(resultSet
					.getInt("user_department_id"));
			peopleBean.setUser_copyright(resultSet.getString("user_copyright"));
			peopleBean.setUser_technical_support(resultSet
					.getString("user_technical_support"));
			peopleBean.setUser_name(resultSet.getString("user_name"));
			peopleBean.setUser_phone(resultSet.getString("user_phone"));
			peopleBean.setUser_role(resultSet.getInt("user_role"));
			peopleBeanlist.add(peopleBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return peopleBeanlist;
	}

	@Override
	public int initializaPassword(int id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update user set user_password=123456 where user_id = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
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
	public int addPeople(PeopleBean peopleBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into user (user_username,user_password,user_name,user_phone,user_role,user_department_id) values(?,?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, peopleBean.getUser_username());
		preparedStatement.setString(2, peopleBean.getUser_password());
		preparedStatement.setString(3, peopleBean.getUser_name());
		preparedStatement.setString(4, peopleBean.getUser_phone());
		preparedStatement.setInt(5, peopleBean.getUser_role());
		preparedStatement.setInt(6, peopleBean.getUser_department_id());
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int changePassword(int people_id, String new_password)
			throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update user set user_password=? where user_id = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, new_password);
		preparedStatement.setInt(2, people_id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int updateUserByUserBean(PeopleBean peopleBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update user set user_copyright=?,user_technical_support=?,user_name=?,user_phone=? where user_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, peopleBean.getUser_copyright());
		preparedStatement.setString(2, peopleBean.getUser_technical_support());
		preparedStatement.setString(3, peopleBean.getUser_name());
		preparedStatement.setString(4, peopleBean.getUser_phone());
		preparedStatement.setInt(5, peopleBean.getUser_id());
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public PeopleBean findUserInformationByID(int user_id) throws Exception {
		PeopleBean peopleBean2 = new PeopleBean();
		connection = dbUtil.getConnection();
		String sql = "select * from user where user_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			peopleBean2.setUser_id(resultSet.getInt("user_id"));
			peopleBean2.setUser_username(resultSet.getString("user_username"));
			peopleBean2.setUser_password(resultSet.getString("user_password"));
			peopleBean2.setUser_name(resultSet.getString("user_name"));
			peopleBean2
					.setUser_copyright(resultSet.getString("user_copyright"));
			peopleBean2.setUser_phone(resultSet.getString("user_phone"));
			peopleBean2.setUser_role(resultSet.getInt("user_role"));
			peopleBean2.setUser_technical_support(resultSet
					.getString("user_technical_support"));
			peopleBean2.setUser_department_id(resultSet
					.getInt("user_department_id"));
		}

		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return peopleBean2;
	}

	@Override
	public int updateUserByUsername(String user_username, String user_name,
			String user_phone) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update user set user_name=?,user_phone=? where user_username=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user_name);
		preparedStatement.setString(2, user_phone);
		preparedStatement.setString(3, user_username);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public String findPhoneByName(String name) throws Exception {
		String phone = null;
		connection = dbUtil.getConnection();
		String sql = "select * from user where user_name=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, name);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			phone = resultSet.getString("user_phone");
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return phone;
	}

	@Override
	public int findUserIdByname(String name) throws Exception {
		int user_id = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from user where user_name=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, name);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			user_id = resultSet.getInt("user_id");
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return user_id;
	}

	@Override
	public int countUserByusername(String user_username) throws Exception {
		int count = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from user where user_username=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user_username);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			count++;
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return count;
	}

	@Override
	public String findNameByID(int user_id) throws Exception {
		String name = null;
		connection = dbUtil.getConnection();
		String sql = "select * from user where user_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			name = resultSet.getString("user_name");
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return name;
	}

	@Override
	public int countUserRowsByRole(int role) throws Exception {
		int userrows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from user where user_role=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, role);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			userrows = resultSet.getInt(1);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return userrows;
	}

	@Override
	public int countUserRowsByDepartment(int user_department_id)
			throws Exception {
		int userrows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from user where user_department_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user_department_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			userrows = resultSet.getInt(1);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return userrows;
	}

	@Override
	public List<PeopleBean> FindAllGuy(int findrole) throws Exception {
		List<PeopleBean> peopleBeanlist = null;
		connection = dbUtil.getConnection();
		String sql = "select * from user where user_role=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, findrole);
		resultSet = preparedStatement.executeQuery();
		peopleBeanlist = new ArrayList<>();
		while (resultSet.next()) {
			PeopleBean peopleBean = new PeopleBean();
			peopleBean.setUser_id(resultSet.getInt("user_id"));
			peopleBean.setUser_username(resultSet.getString("user_username"));
			peopleBean.setUser_password(resultSet.getString("user_password"));
			peopleBean.setUser_copyright(resultSet.getString("user_copyright"));
			peopleBean.setUser_technical_support(resultSet
					.getString("user_technical_support"));
			peopleBean.setUser_name(resultSet.getString("user_name"));
			peopleBean.setUser_phone(resultSet.getString("user_phone"));
			peopleBean.setUser_role(resultSet.getInt("user_role"));
			peopleBean.setUser_department_id(resultSet
					.getInt("user_department_id"));
			peopleBeanlist.add(peopleBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return peopleBeanlist;

	}
}
