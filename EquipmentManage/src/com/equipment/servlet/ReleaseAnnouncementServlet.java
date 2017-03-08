package com.equipment.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.dao.WorkBenchDaoImpl;
import com.equipment.dao.WorkbenchDao;

/**
 * Servlet implementation class ReleaseAnnouncementServlet
 */
@WebServlet("/ReleaseAnnouncementServlet")
public class ReleaseAnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReleaseAnnouncementServlet() {
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
		String datetime = getTime();
		WorkbenchDao workbenchDao = new WorkBenchDaoImpl();
		try {
			int announcement_state = workbenchDao
					.fetchAnnouncementState(announcement_id);
			if (announcement_state == 1) {
				request.setAttribute("message", "已经发布不能重复发布");
			} else {
				int rows = workbenchDao.releaseAnnouncement(announcement_id,
						datetime);
				if (rows >= 1) {
					request.setAttribute("message", "发布成功");
				} else {
					request.setAttribute("message", "发布失败，请重试");
				}

			}
		} catch (Exception e) {
			request.setAttribute("message", "请检查数据库连接");
			e.printStackTrace();
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

	// 获取当前时间
	private String getTime() {
		Date currDate = Calendar.getInstance().getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String datetime = simpleDateFormat.format(currDate);
		return datetime;
	}
}
