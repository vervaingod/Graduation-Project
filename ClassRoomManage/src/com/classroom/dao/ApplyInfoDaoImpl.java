package com.classroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.classroom.bean.ApplyInfoBean;
import com.classroom.util.DBUtil;

public class ApplyInfoDaoImpl implements ApplyInfoDao {

	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public List<ApplyInfoBean> fetchAllApplyInfo() throws Exception {
		connection = dbUtil.getConnection();
		List<ApplyInfoBean> applyInfoBeanList = null;
		String sql = "select * from applyinfo";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		applyInfoBeanList = new ArrayList<ApplyInfoBean>();
		while (resultSet.next()) {
			ApplyInfoBean applyInfoBean = new ApplyInfoBean();
			applyInfoBean.setId(resultSet.getInt("id"));
			applyInfoBean.setApplystudent(resultSet.getString("applystudent"));
			applyInfoBean.setApplyclassroom(resultSet
					.getString("applyclassroom"));
			applyInfoBean.setRoomstate(resultSet.getInt("roomstate"));
			applyInfoBean.setApplystate(resultSet.getInt("applystate"));
			applyInfoBean.setApplyreply(resultSet.getString("applyreply"));
			applyInfoBean.setRemark(resultSet.getString("remark"));
			applyInfoBeanList.add(applyInfoBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return applyInfoBeanList;
	}

	@Override
	public List<ApplyInfoBean> fetchWaitingApply() throws Exception {
		connection = dbUtil.getConnection();
		List<ApplyInfoBean> applyInfoBeanList = null;
		String sql = "select * from applyinfo where applystate=0";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		applyInfoBeanList = new ArrayList<ApplyInfoBean>();
		while (resultSet.next()) {
			ApplyInfoBean applyInfoBean = new ApplyInfoBean();
			applyInfoBean.setId(resultSet.getInt("id"));
			applyInfoBean.setApplystudent(resultSet.getString("applystudent"));
			applyInfoBean.setApplyclassroom(resultSet
					.getString("applyclassroom"));
			applyInfoBean.setRoomstate(resultSet.getInt("roomstate"));
			applyInfoBean.setApplystate(resultSet.getInt("applystate"));
			applyInfoBean.setApplyreply(resultSet.getString("applyreply"));
			applyInfoBean.setRemark(resultSet.getString("remark"));
			applyInfoBeanList.add(applyInfoBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return applyInfoBeanList;
	}

	@Override
	public List<ApplyInfoBean> fetchAppledInfo() throws Exception {
		connection = dbUtil.getConnection();
		List<ApplyInfoBean> applyInfoBeanList = null;
		String sql = "select * from applyinfo where applystate=1 ";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		applyInfoBeanList = new ArrayList<ApplyInfoBean>();
		while (resultSet.next()) {
			ApplyInfoBean applyInfoBean = new ApplyInfoBean();
			applyInfoBean.setId(resultSet.getInt("id"));
			applyInfoBean.setApplystudent(resultSet.getString("applystudent"));
			applyInfoBean.setApplyclassroom(resultSet
					.getString("applyclassroom"));
			applyInfoBean.setRoomstate(resultSet.getInt("roomstate"));
			applyInfoBean.setApplystate(resultSet.getInt("applystate"));
			applyInfoBean.setApplyreply(resultSet.getString("applyreply"));
			applyInfoBean.setRemark(resultSet.getString("remark"));
			applyInfoBeanList.add(applyInfoBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return applyInfoBeanList;

	}

	@Override
	public int passApply(int id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update applyinfo set applystate=1 where id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		rows = preparedStatement.executeUpdate();
		return rows;
	}

	@Override
	public int refuseApply(int id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update applyinfo set applystate=-1 where id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		rows = preparedStatement.executeUpdate();
		return rows;
	}

	@Override
	public int deleteApply(int id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from applyinfo where id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		rows = preparedStatement.executeUpdate();
		return rows;
	}

	@Override
	public int updateEvaluate(int id, String remark) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update applyinfo set remark=? where id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, remark);
		preparedStatement.setInt(2, id);
		rows = preparedStatement.executeUpdate();
		return rows;
	}

	@Override
	public List<ApplyInfoBean> fetchApplyById(int id) throws Exception {
		connection = dbUtil.getConnection();
		List<ApplyInfoBean> applyInfoBeanList = null;
		String sql = "select * from applyinfo where id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		applyInfoBeanList = new ArrayList<ApplyInfoBean>();
		while (resultSet.next()) {
			ApplyInfoBean applyInfoBean = new ApplyInfoBean();
			applyInfoBean.setId(resultSet.getInt("id"));
			applyInfoBean.setApplystudent(resultSet.getString("applystudent"));
			applyInfoBean.setApplyclassroom(resultSet
					.getString("applyclassroom"));
			applyInfoBean.setRoomstate(resultSet.getInt("roomstate"));
			applyInfoBean.setApplystate(resultSet.getInt("applystate"));
			applyInfoBean.setApplyreply(resultSet.getString("applyreply"));
			applyInfoBean.setRemark(resultSet.getString("remark"));
			applyInfoBeanList.add(applyInfoBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return applyInfoBeanList;

	}

	@Override
	public int addApply(ApplyInfoBean applyInfoBean) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into applyinfo(applystudent,applyclassroom,applystate,roomstate) values (?,?,0,1)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, applyInfoBean.getApplystudent());
		preparedStatement.setString(2, applyInfoBean.getApplyclassroom());
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public List<ApplyInfoBean> fetchApplyByUsername(String applystudent)
			throws Exception {
		connection = dbUtil.getConnection();
		List<ApplyInfoBean> applyInfoBeanList = null;
		String sql = "select * from applyinfo where applystudent=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, applystudent);
		resultSet = preparedStatement.executeQuery();
		applyInfoBeanList = new ArrayList<ApplyInfoBean>();
		while (resultSet.next()) {
			ApplyInfoBean applyInfoBean = new ApplyInfoBean();
			applyInfoBean.setId(resultSet.getInt("id"));
			applyInfoBean.setApplystudent(resultSet.getString("applystudent"));
			applyInfoBean.setApplyclassroom(resultSet
					.getString("applyclassroom"));
			applyInfoBean.setRoomstate(resultSet.getInt("roomstate"));
			applyInfoBean.setApplystate(resultSet.getInt("applystate"));
			applyInfoBean.setApplyreply(resultSet.getString("applyreply"));
			applyInfoBean.setRemark(resultSet.getString("remark"));
			applyInfoBeanList.add(applyInfoBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return applyInfoBeanList;

	}

}
