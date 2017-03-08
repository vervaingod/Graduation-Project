package com.equipment.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.bean.AnnouncementBean;
import com.equipment.dao.WorkBenchDaoImpl;
import com.equipment.dao.WorkbenchDao;

/**
 * Servlet implementation class UpdateAnnouncementServlet
 */
@WebServlet("/UpdateAnnouncementServlet")
public class UpdateAnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateAnnouncementServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		int announcement_id = Integer.parseInt(request
				.getParameter("announcement_id"));
		String announcement_name = request.getParameter("announcement_name");
		String announcement_content = request
				.getParameter("announcement_content");
		AnnouncementBean announcementBean = new AnnouncementBean();
		WorkbenchDao workbenchDao = new WorkBenchDaoImpl();
		if (announcement_name == "") {
			request.setAttribute("message", "公告名称不能为空");
		} else {
			announcementBean.setAnnouncement_id(announcement_id);
			announcementBean.setAnnouncement_name(announcement_name);
			announcementBean.setAnnouncement_content(announcement_content);
			try {
				int rows = workbenchDao.updateAnnouncement(announcementBean);
				if (rows >= 1) {
					request.setAttribute("message", "更新成功");
				} else {
					request.setAttribute("message", "更新失败，请重试");
				}
			} catch (Exception e) {
				request.setAttribute("message", "请检查数据库连接");
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("./SystemAnnouncementServlet").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
