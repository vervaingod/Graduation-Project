package com.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.classroom.bean.ClassBean;
import com.classroom.util.DBUtil;

public class ClassDaoImpl implements ClassDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public List<ClassBean> fetchAllClass() throws Exception {
		List<ClassBean> classBeanlist = null;
		connection = dbUtil.getConnection();
		String sql = "select * from class";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		classBeanlist = new ArrayList<ClassBean>();
		while (resultSet.next()) {
			ClassBean classBean = new ClassBean();
			classBean.setId(resultSet.getInt("id"));
			classBean.setClassnum(resultSet.getString("classnum"));
			classBean.setClassname(resultSet.getString("classname"));
			classBean.setClassroom(resultSet.getString("classroom"));
			classBean.setClassstate(resultSet.getInt("classstate"));
			classBeanlist.add(classBean);
		}

		dbUtil.closeDBResource(connection, preparedStatement, resultSet);

		return classBeanlist;
	}

	@Override
	public int addClass(ClassBean classBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into class(classnum,classname,classroom,classstate) values(?,?,?,1)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, classBean.getClassnum());
		preparedStatement.setString(2, classBean.getClassname());
		preparedStatement.setString(3, classBean.getClassroom());
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int startClass(String classnum) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update class set classstate=1 where classnum=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, classnum);
		rows = preparedStatement.executeUpdate();
		return rows;
	}

	@Override
	public int stopClass(String classnum) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update class set classstate=0 where classnum=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, classnum);
		rows = preparedStatement.executeUpdate();
		return rows;
	}

	@Override
	public List<ClassBean> searchClass(String classnum, String classname,
			String classroom) throws Exception {
		List<ClassBean> classBeanList = null;
		connection = dbUtil.getConnection();
		String sql = "select * from class where classnum=? or classname=? or classroom=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, classnum);
		preparedStatement.setString(2, classname);
		preparedStatement.setString(3, classroom);
		
		resultSet = preparedStatement.executeQuery();
		classBeanList = new ArrayList<ClassBean>();
		while (resultSet.next()) {
			ClassBean classBean = new ClassBean();
			classBean.setId(resultSet.getInt("id"));
			classBean.setClassnum(resultSet.getString("classnum"));
			classBean.setClassname(resultSet.getString("classname"));
			classBean.setClassroom(resultSet.getString("classroom"));
			classBean.setClassstate(resultSet.getInt("classstate"));
			classBeanList.add(classBean);
		}

		dbUtil.closeDBResource(connection, preparedStatement, resultSet);

		return classBeanList;
	}

}
