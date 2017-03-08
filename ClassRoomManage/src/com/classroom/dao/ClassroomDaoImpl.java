package com.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.classroom.bean.ClassroomBean;
import com.classroom.util.DBUtil;

public class ClassroomDaoImpl implements ClassroomDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public List<ClassroomBean> fetchAllClassRoom() throws Exception {
		connection = dbUtil.getConnection();
		List<ClassroomBean> classroomBeanlist = null;
		String sql = "select * from classroom";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		classroomBeanlist = new ArrayList<ClassroomBean>();
		while (resultSet.next()) {
			ClassroomBean classroomBean = new ClassroomBean();
			classroomBean.setId(resultSet.getInt("id"));
			classroomBean.setClassroomnumber(resultSet
					.getString("classroomnumber"));
			classroomBean.setRoom(resultSet.getString("room"));
			classroomBean.setState(resultSet.getInt("state"));
			classroomBean.setNote(resultSet.getString("note"));
			classroomBeanlist.add(classroomBean);
		}
		return classroomBeanlist;
	}

	@Override
	public int addClassRoom(ClassroomBean classroomBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into classroom (classroomnumber,room,state,note) values(?,?,1,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, classroomBean.getClassroomnumber());
		preparedStatement.setString(2, classroomBean.getRoom());
		preparedStatement.setString(3, classroomBean.getNote());
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public ClassroomBean fetchClassroomByRoomnumber(String classroomnumber)
			throws Exception {
		ClassroomBean classroomBean = null;
		connection = dbUtil.getConnection();
		String sql = "select * from classroom where classroomnumber=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, classroomnumber);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			classroomBean = new ClassroomBean();
			classroomBean.setClassroomnumber(resultSet
					.getString("classroomnumber"));
			classroomBean.setRoom(resultSet.getString("room"));
			classroomBean.setState(resultSet.getInt("state"));
			classroomBean.setNote(resultSet.getString("note"));
		}

		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return classroomBean;
	}

	@Override
	public int updateClassroom(ClassroomBean classroomBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update classroom set room=?,state=?,note=? where classroomnumber=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, classroomBean.getRoom());
		preparedStatement.setInt(2, classroomBean.getState());
		preparedStatement.setString(3, classroomBean.getNote());
		preparedStatement.setString(4, classroomBean.getClassroomnumber());

		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int deleteClassroom(String classroomnumber) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from classroom where classroomnumber=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, classroomnumber);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int startClassroom(String classroomnumber) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update classroom set state=1 where classroomnumber=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, classroomnumber);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int changeClassroomToBusy(String classroomnumber) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update classroom set state=0 where classroomnumber=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, classroomnumber);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public List<ClassroomBean> searchClassroom(String classroomnumber,
			String room) throws Exception {
		connection = dbUtil.getConnection();
		List<ClassroomBean> classroomBeanlist = null;
		String sql = "select * from classroom where classroomnumber=? or room=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, classroomnumber);
		preparedStatement.setString(2, room);
		resultSet = preparedStatement.executeQuery();
		classroomBeanlist = new ArrayList<ClassroomBean>();
		while (resultSet.next()) {
			ClassroomBean classroomBean = new ClassroomBean();
			classroomBean.setId(resultSet.getInt("id"));
			classroomBean.setClassroomnumber(resultSet
					.getString("classroomnumber"));
			classroomBean.setRoom(resultSet.getString("room"));
			classroomBean.setState(resultSet.getInt("state"));
			classroomBean.setNote(resultSet.getString("note"));
			classroomBeanlist.add(classroomBean);
		}
		return classroomBeanlist;
	}

	@Override
	public List<ClassroomBean> fetchFreeClassRoom() throws Exception {
		connection = dbUtil.getConnection();
		List<ClassroomBean> classroomBeanlist = null;
		String sql = "select * from classroom where state=1";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		classroomBeanlist = new ArrayList<ClassroomBean>();
		while (resultSet.next()) {
			ClassroomBean classroomBean = new ClassroomBean();
			classroomBean.setId(resultSet.getInt("id"));
			classroomBean.setClassroomnumber(resultSet
					.getString("classroomnumber"));
			classroomBean.setRoom(resultSet.getString("room"));
			classroomBean.setState(resultSet.getInt("state"));
			classroomBean.setNote(resultSet.getString("note"));
			classroomBeanlist.add(classroomBean);
		}
		return classroomBeanlist;
	}
};
