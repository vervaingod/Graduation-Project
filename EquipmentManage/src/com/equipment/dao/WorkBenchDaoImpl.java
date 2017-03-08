package com.equipment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.equipment.bean.AnnouncementBean;
import com.equipment.bean.PageBean;
import com.equipment.util.DBUtil;

public class WorkBenchDaoImpl implements WorkbenchDao {
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public List<AnnouncementBean> fetchPublishedAnnouncement(int pageno)
			throws Exception {
		List<AnnouncementBean> announcementBeanlist = new ArrayList<AnnouncementBean>();
		connection = dbUtil.getConnection();
		String sql = "select * from announcement where announcement_state=1 limit ?,?";
		int startIndex = (pageno - 1) * PageBean.ROWS_PRO_PAGE;
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRO_PAGE);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			AnnouncementBean announcementBean = new AnnouncementBean();
			announcementBean.setAnnouncement_id(resultSet
					.getInt("announcement_id"));
			announcementBean.setAnnouncement_name(resultSet
					.getString("announcement_name"));
			announcementBean.setAnnouncement_time(resultSet
					.getString("announcement_time"));
			announcementBean.setAnnouncement_content(resultSet
					.getString("announcement_content"));
			announcementBeanlist.add(announcementBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return announcementBeanlist;

	}

	@Override
	public List<AnnouncementBean> fetchAllAnnouncement(int pageno,
			String search_announcement_name, String search_announcement_time)
			throws Exception {
		List<AnnouncementBean> announcementBeanlist = new ArrayList<AnnouncementBean>();
		int startIndex = (pageno - 1) * PageBean.ROWS_PRO_PAGE;
		connection = dbUtil.getConnection();
		String sql = "select * from announcement where 1=1";
		if (search_announcement_name != "") {
			sql = sql + " and announcement_name=" + "'"
					+ search_announcement_name + "'";
		}
		if (search_announcement_time != "") {
			sql = sql + " and announcement_time like " + "'"
					+ search_announcement_time + "%'";
		}
		sql = sql + " limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, startIndex);
		preparedStatement.setInt(2, PageBean.ROWS_PRO_PAGE);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			AnnouncementBean announcementBean = new AnnouncementBean();
			announcementBean.setAnnouncement_id(resultSet
					.getInt("announcement_id"));
			announcementBean.setAnnouncement_name(resultSet
					.getString("announcement_name"));
			announcementBean.setAnnouncement_time(resultSet
					.getString("announcement_time"));
			announcementBean.setAnnouncement_state(resultSet
					.getInt("announcement_state"));
			announcementBean.setAnnouncement_content(resultSet
					.getString("announcement_content"));
			announcementBeanlist.add(announcementBean);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return announcementBeanlist;

	}

	@Override
	public AnnouncementBean fetchAnnounceById(int announcement_id)
			throws Exception {
		AnnouncementBean announcementBean = new AnnouncementBean();
		connection = dbUtil.getConnection();
		String sql = "select * from announcement where announcement_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, announcement_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			announcementBean.setAnnouncement_id(resultSet
					.getInt("announcement_id"));
			announcementBean.setAnnouncement_name(resultSet
					.getString("announcement_name"));
			announcementBean.setAnnouncement_state(resultSet
					.getInt("announcement_state"));
			announcementBean.setAnnouncement_time(resultSet
					.getString("announcement_time"));
			announcementBean.setAnnouncement_content(resultSet
					.getString("announcement_content"));
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return announcementBean;
	}

	@Override
	public int releaseAnnouncement(int announcement_id, String announcement_time)
			throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update announcement set announcement_state=1,announcement_time=? where announcement_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, announcement_time);
		preparedStatement.setInt(2, announcement_id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int fetchAnnouncementState(int announcement_id) throws Exception {
		int announcement_state = 0;
		connection = dbUtil.getConnection();
		String sql = "select * from announcement where announcement_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, announcement_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			announcement_state = resultSet.getInt("announcement_state");
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return announcement_state;
	}

	@Override
	public int shelveAnnouncement(int announcement_id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update announcement set announcement_state=-1 where announcement_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, announcement_id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int deleteAnnouncement(int announcement_id) throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "delete from announcement where announcement_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, announcement_id);
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int updateAnnouncement(AnnouncementBean announcementBean)
			throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "update announcement set announcement_name=?,announcement_state=0,announcement_content=? where announcement_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, announcementBean.getAnnouncement_name());
		preparedStatement.setString(2,
				announcementBean.getAnnouncement_content());
		preparedStatement.setInt(3, announcementBean.getAnnouncement_id());
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public int addAnnouncement(AnnouncementBean announcementBean)
			throws Exception {
		int rows = 0;
		connection = dbUtil.getConnection();
		String sql = "insert into announcement(announcement_name,announcement_state,announcement_content) values(?,0,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, announcementBean.getAnnouncement_name());
		preparedStatement.setString(2,
				announcementBean.getAnnouncement_content());
		rows = preparedStatement.executeUpdate();
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return rows;
	}

	@Override
	public AnnouncementBean fetchAnnouncementById(int announcement_id)
			throws Exception {
		AnnouncementBean announcementBean = new AnnouncementBean();
		connection = dbUtil.getConnection();
		String sql = "select * from announcement where announcement_id=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, announcement_id);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			announcementBean.setAnnouncement_id(resultSet
					.getInt("announcement_id"));
			announcementBean.setAnnouncement_name(resultSet
					.getString("announcement_name"));
			announcementBean.setAnnouncement_state(resultSet
					.getInt("announcement_state"));
			announcementBean.setAnnouncement_time(resultSet
					.getString("announcement_time"));
			announcementBean.setAnnouncement_content(resultSet
					.getString("announcement_content"));
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return announcementBean;
	}

	@Override
	public int countAnnouncementRows(String search_announcement_name,
			String search_announcement_time) throws Exception {
		int announcementrows = 0;
		connection = dbUtil.getConnection();
		String sql = "select count(*) from announcement where 1=1";
		if (search_announcement_name != "") {
			sql = sql + " and announcement_name=" + "'"
					+ search_announcement_name + "'";
		}
		if (search_announcement_time != "") {
			sql = sql + " and announcement_time like" + "'"
					+ search_announcement_time + "%'";
		}
		preparedStatement = connection.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			announcementrows = resultSet.getInt(1);
		}
		dbUtil.closeDBResource(connection, preparedStatement, resultSet);
		return announcementrows;
	}

}
