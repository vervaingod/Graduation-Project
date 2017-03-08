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
import javax.servlet.http.HttpSession;

import com.equipment.bean.LogBean;
import com.equipment.bean.PeopleBean;
import com.equipment.dao.LogDao;
import com.equipment.dao.LogDaoImpl;
import com.equipment.dao.MaintenanceDao;
import com.equipment.dao.MaintenanceDaoImpl;

/**
 * Servlet implementation class InvalidDispatchServlet
 */
@WebServlet("/InvalidDispatchServlet")
public class InvalidDispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InvalidDispatchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
	LogDao logdao = new LogDaoImpl();
	LogBean logbean = new LogBean();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String datetime = getTime();
		HttpSession session_peoplebean = request.getSession();
		PeopleBean peopleBean = (PeopleBean) session_peoplebean
				.getAttribute("session_peoplebean");
		int maintenance_id = Integer.parseInt(request
				.getParameter("maintenance_id"));
		logbean.setLog_operation("作废");
		logbean.setLog_content("派工作废");
		logbean.setLog_maintenance_id(maintenance_id);
		logbean.setLog_time(datetime);
		logbean.setLog_people(peopleBean.getUser_name());
		try {
			int maintenance_dispatch_state = maintenanceDao
					.fetchDispatchState(maintenance_id);
			switch (maintenance_dispatch_state) {
			case -1:
				request.setAttribute("message", "已经作废，不能重复进行操作");
				break;
			case 1:
				request.setAttribute("message", "已经派工，不能作废");
				break;
			case 0:
				int rows = maintenanceDao
						.invalidDispatchByMaintenanceID(maintenance_id);
				if (rows >= 1) {
					logdao.addLog(logbean);
					request.setAttribute("message", "作废成功");
				} else {
					request.setAttribute("message", "作废失败");
				}
				break;
			}
		} catch (Exception e) {
			request.setAttribute("message", "请检查数据库连接！");
			e.printStackTrace();
		}
		request.getRequestDispatcher("./MaintenanceDispatchServlet").forward(
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
