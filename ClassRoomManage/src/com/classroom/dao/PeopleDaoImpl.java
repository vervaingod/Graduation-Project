package com.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.classroom.bean.PeopleBean;
import com.classroom.util.DBUtil;

public class PeopleDaoImpl implements PeopleDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public PeopleBean login(PeopleBean peopleBean) throws Exception {
		PeopleBean peopleBean2 = new PeopleBean();
		connection = dbUtil.getConnection();
		String sql = "select * from people where username=? and password=? and state=1";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, peopleBean.getUsername());
		preparedStatement.setString(2, peopleBean.getPassword());
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {

			peopleBean2.setPeopleid(resultSet.getInt("peopleid"));
			peopleBean2.setUsername(resultSet.getString("username"));
			peopleBean2.setName(resultSet.getString("name"));
			peopleBean2.setPassword(resultSet.getString("password"));
			peopleBean2.setEmail(resultSet.getString("email"));
			peopleBean2.setClassid(resultSet.getString("classid"));
			peopleBean2.setPhone(resultSet.getInt("phone"));
			peopleBean2.setRole(resultSet.getInt("role"));
			peopleBean2.setState(resultSet.getInt("state"));
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return peopleBean2;
	}

	@Override
	public List<PeopleBean> fetchAllStudentList() throws Exception {
		List<PeopleBean> peopleBeanlist = null;
		connection = dbUtil.getConnection();
		String sql = "select * from people where role=0";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		peopleBeanlist = new ArrayList<PeopleBean>();
		while (resultSet.next()) {
			PeopleBean peopleBean = new PeopleBean();
			peopleBean.setPeopleid(resultSet.getInt("peopleid"));
			peopleBean.setUsername(resultSet.getString("username"));
			peopleBean.setName(resultSet.getString("name"));
			peopleBean.setPassword(resultSet.getString("password"));
			peopleBean.setEmail(resultSet.getString("email"));
			peopleBean.setClassid(resultSet.getString("classid"));
			peopleBean.setPhone(resultSet.getInt("phone"));
			peopleBean.setRole(resultSet.getInt("role"));
			peopleBean.setState(resultSet.getInt("state"));
			peopleBeanlist.add(peopleBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return peopleBeanlist;
	}

	@Override
	public int registermanager(PeopleBean peopleBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into people(name,username,password,email,classid,phone,role,state) values (?,?,?,?,?,?,1,1)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, peopleBean.getName());
		preparedStatement.setString(2, peopleBean.getUsername());
		preparedStatement.setString(3, peopleBean.getPassword());
		preparedStatement.setString(4, peopleBean.getEmail());
		preparedStatement.setString(5, peopleBean.getClassid());
		preparedStatement.setInt(6, peopleBean.getPhone());

		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int fetchUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from people where username = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			rows = 1;
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int fetchName(String name) throws Exception {
		// TODO Auto-generated method stub
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from people where name = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, name);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			rows = 1;
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;

	}

	@Override
	public int StartStudent(String username) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update people set state=1 where username=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		rows = preparedStatement.executeUpdate();
		return rows;
	}

	@Override
	public int StopStudent(String username) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update people set state=0 where username=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		rows = preparedStatement.executeUpdate();
		return rows;
	}

	@Override
	public List<PeopleBean> fetchAllmanageList() throws Exception {

		List<PeopleBean> peopleBeanlist = null;
		connection = dbUtil.getConnection();
		String sql = "select * from people where role=1";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		peopleBeanlist = new ArrayList<PeopleBean>();
		while (resultSet.next()) {
			PeopleBean peopleBean = new PeopleBean();
			peopleBean.setPeopleid(resultSet.getInt("peopleid"));
			peopleBean.setUsername(resultSet.getString("username"));
			peopleBean.setPassword(resultSet.getString("password"));
			peopleBean.setEmail(resultSet.getString("email"));
			peopleBean.setClassid(resultSet.getString("classid"));
			peopleBean.setPhone(resultSet.getInt("phone"));
			peopleBean.setRole(resultSet.getInt("role"));
			peopleBean.setState(resultSet.getInt("state"));
			peopleBeanlist.add(peopleBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return peopleBeanlist;

	}

	@Override
	public List<PeopleBean> searchStudentList(String name, String username,
			int state) throws Exception {
		List<PeopleBean> peopleBeanlist = null;
		connection = dbUtil.getConnection();
		String sql = "select * FROM people where name=? or username=? or state=? and role=0";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, username);
		preparedStatement.setInt(3, state);
		resultSet = preparedStatement.executeQuery();
		peopleBeanlist = new ArrayList<PeopleBean>();
		while (resultSet.next()) {
			PeopleBean peopleBean = new PeopleBean();
			peopleBean.setPeopleid(resultSet.getInt("peopleid"));
			peopleBean.setUsername(resultSet.getString("username"));
			peopleBean.setName(resultSet.getString("name"));
			peopleBean.setPassword(resultSet.getString("password"));
			peopleBean.setEmail(resultSet.getString("email"));
			peopleBean.setClassid(resultSet.getString("classid"));
			peopleBean.setPhone(resultSet.getInt("phone"));
			peopleBean.setRole(resultSet.getInt("role"));
			peopleBean.setState(resultSet.getInt("state"));
			peopleBeanlist.add(peopleBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return peopleBeanlist;
	}

	@Override
	public int registerStudent(PeopleBean peopleBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into people(name,username,password,email,classid,phone,role,state) values (?,?,?,?,?,?,0,0)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, peopleBean.getName());
		preparedStatement.setString(2, peopleBean.getUsername());
		preparedStatement.setString(3, peopleBean.getPassword());
		preparedStatement.setString(4, peopleBean.getEmail());
		preparedStatement.setString(5, peopleBean.getClassid());
		preparedStatement.setInt(6, peopleBean.getPhone());

		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

}
