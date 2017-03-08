package com.equipment.dao;

import java.util.List;

import com.equipment.bean.AnnouncementBean;

public interface WorkbenchDao {
	public List<AnnouncementBean> fetchPublishedAnnouncement(int pageno)
			throws Exception;

	public List<AnnouncementBean> fetchAllAnnouncement(int pageno,
			String search_announcement_name, String search_announcement_time)
			throws Exception;

	public AnnouncementBean fetchAnnounceById(int announcement_id)
			throws Exception;

	public int releaseAnnouncement(int announcement_id, String announcement_time)
			throws Exception;

	public int fetchAnnouncementState(int announcement_id) throws Exception;

	public int shelveAnnouncement(int announcement_id) throws Exception;

	public int deleteAnnouncement(int announcement_id) throws Exception;

	public int updateAnnouncement(AnnouncementBean announcementBean)
			throws Exception;

	public int addAnnouncement(AnnouncementBean announcementBean)
			throws Exception;

	public AnnouncementBean fetchAnnouncementById(int announcement_id)
			throws Exception;

	public int countAnnouncementRows(String search_announcement_name,
			String search_announcement_time) throws Exception;
}
