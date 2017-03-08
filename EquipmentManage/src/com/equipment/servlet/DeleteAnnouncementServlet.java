package com.equipment.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.dao.WorkBenchDaoImpl;
import com.equipment.dao.WorkbenchDao;

/**
 * Servlet implementation class DeleteAnnouncementServlet
 */
@WebServlet("/DeleteAnnouncementServlet")
public class DeleteAnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAnnouncementServlet() {
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
		WorkbenchDao workbenchDao = new WorkBenchDaoImpl();
		try {
			int rows = workbenchDao.deleteAnnouncement(announcement_id);
			if (rows >= 1) {
				request.setAttribute("message", "删除成功！");
			} else {
				request.setAttribute("message", "删除失败，请重试");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "请检查数据库连接！");
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
