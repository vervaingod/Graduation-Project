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
 * @author 许嘉阳
 * @功能 增加公告
 */

/**
 * Servlet implementation class AddAnnouncementServlet
 */
@WebServlet("/AddAnnouncementServlet")
public class AddAnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddAnnouncementServlet() {
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
		String announcement_name = request.getParameter("announcement_name");
		String announcement_content = request
				.getParameter("announcement_content");
		AnnouncementBean announcementBean = new AnnouncementBean();
		announcementBean.setAnnouncement_name(announcement_name);
		announcementBean.setAnnouncement_content(announcement_content);
		WorkbenchDao workbenchDao = new WorkBenchDaoImpl();

		List<AnnouncementBean> announcementBeanlist = new ArrayList<AnnouncementBean>();
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
			announcementBeanlist = workbenchDao.fetchAllAnnouncement(pageno,
					"", "");
			request.setAttribute("announcementBeanlist", announcementBeanlist);
			PageBean pageBean = new PageBean();
			pageBean.setMaxpage(maxpage);
			pageBean.setPageno(pageno);
			request.setAttribute("pageBean", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "请检查数据库的连接");
		}

		if (announcement_name == "") {
			request.setAttribute("message", "请输入公告名");
		} else {
			try {
				int rows = workbenchDao.addAnnouncement(announcementBean);
				if (rows >= 1) {
					request.setAttribute("message", "添加成功");
				} else {
					request.setAttribute("message", "添加失败，请重试");
				}
			} catch (Exception e) {
				request.setAttribute("message", "请检查数据库的连接");
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
