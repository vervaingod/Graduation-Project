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
 * Servlet implementation class WorkbenchServlet
 */
@WebServlet("/WorkbenchServlet")
public class WorkbenchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorkbenchServlet() {
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
		WorkbenchDao workbenchDao = new WorkBenchDaoImpl();
		List<AnnouncementBean> announcementBeanlist = new ArrayList<AnnouncementBean>();
		AnnouncementBean announcementBean = new AnnouncementBean();
		String type = request.getParameter("type");
		// 分页的实现
		String pagenoString = request.getParameter("pageno");
		int pageno = 1;
		if (pagenoString != null) {
			pageno = Integer.parseInt(pagenoString);
		}
		try {
			int announcementrows = workbenchDao.countAnnouncementRows("", "");
			int maxpage = announcementrows % PageBean.ROWS_PRO_PAGE == 0 ? announcementrows
					/ PageBean.ROWS_PRO_PAGE
					: (announcementrows / PageBean.ROWS_PRO_PAGE + 1);
			if (pageno < 1) {
				pageno = 1;
			}
			if (pageno > maxpage) {
				pageno = maxpage;
			}
			announcementBeanlist = workbenchDao
					.fetchPublishedAnnouncement(pageno);
			request.setAttribute("announcementBeanlist", announcementBeanlist);
			PageBean pageBean = new PageBean();
			pageBean.setMaxpage(maxpage);
			pageBean.setPageno(pageno);
			request.setAttribute("pageBean", pageBean);
		} catch (Exception e) {
			request.setAttribute("message", "请检查数据库连接");
			request.getRequestDispatcher("workbench.jsp").forward(request,
					response);
			e.printStackTrace();
			return;
		}
		// 判断是否显示编辑框
		if ("fetch".equals(type)) {
			request.setAttribute("display", "block");
			int announcement_id = Integer.parseInt(request
					.getParameter("announcement_id"));
			try {
				announcementBean = workbenchDao
						.fetchAnnounceById(announcement_id);
				String announcement_name = announcementBean
						.getAnnouncement_name();
				String announcement_content = announcementBean
						.getAnnouncement_content();
				request.setAttribute("announcement_name", announcement_name);
				request.setAttribute("announcement_content",
						announcement_content);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "请检查数据库连接");
			}
		} else {
			request.setAttribute("display", "none");
		}
		request.getRequestDispatcher("workbench.jsp")
				.forward(request, response);
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
