package com.equipment.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.bean.AnnouncementBean;
import com.equipment.bean.PageBean;
import com.equipment.dao.WorkBenchDaoImpl;
import com.equipment.dao.WorkbenchDao;

/**
 * Servlet implementation class SystemAnnouncementServlet
 */
@WebServlet("/SystemAnnouncementServlet")
public class SystemAnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SystemAnnouncementServlet() {
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
		List<AnnouncementBean> announcementBeanlist = new ArrayList<AnnouncementBean>();
		AnnouncementBean announcementBean = new AnnouncementBean();
		WorkbenchDao workbenchDao = new WorkBenchDaoImpl();
		String search_announcement_name = request
				.getParameter("search_announcement_name");
		String search_announcement_time = request
				.getParameter("search_announcement_time");
		if (search_announcement_name == null) {
			search_announcement_name = "";
		}
		if (search_announcement_time == null) {
			search_announcement_time = "";
		}
		request.setAttribute("search_announcement_name",
				search_announcement_name);
		request.setAttribute("search_announcement_time",
				search_announcement_time);
		String type = request.getParameter("type");
		String announcement_id = request.getParameter("announcement_id");
		String pagenoString = request.getParameter("pageno");
		int pageno = 1;
		if (pagenoString != null) {
			pageno = Integer.parseInt(pagenoString);
		}
		try {
			int announcementrows = workbenchDao.countAnnouncementRows(
					search_announcement_name, search_announcement_time);
			int maxpage = announcementrows % PageBean.ROWS_PRO_PAGE == 0 ? announcementrows
					/ PageBean.ROWS_PRO_PAGE
					: (announcementrows / PageBean.ROWS_PRO_PAGE + 1);
			if (pageno < 1) {
				pageno = 1;
			}
			if (pageno > maxpage) {
				pageno = maxpage;
			}
			announcementBeanlist = workbenchDao.fetchAllAnnouncement(pageno,
					search_announcement_name, search_announcement_time);
			request.setAttribute("announcementBeanlist", announcementBeanlist);
			PageBean pageBean = new PageBean();
			pageBean.setMaxpage(maxpage);
			pageBean.setPageno(pageno);
			request.setAttribute("pageBean", pageBean);
			if ("update".equals(type)) {
				announcementBean = workbenchDao.fetchAnnounceById(Integer
						.parseInt(announcement_id));
				request.setAttribute("announcement_id",
						announcementBean.getAnnouncement_id());
				request.setAttribute("announcement_name",
						announcementBean.getAnnouncement_name());
				request.setAttribute("announcement_content",
						announcementBean.getAnnouncement_content());
				request.setAttribute("display", "block");
				request.getRequestDispatcher("system_announcement.jsp")
						.forward(request, response);
			} else if ("add".equals(type)) {
				request.getRequestDispatcher("system_announcement_add.jsp")
						.forward(request, response);
			} else {
				request.setAttribute("display", "none");
				request.getRequestDispatcher("system_announcement.jsp")
						.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "请检查数据库连接");
			request.getRequestDispatcher("system_announcement.jsp").forward(
					request, response);
		}
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
