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
 * @author 许嘉阳
 * @功能 维修工完成维修受理
 */

/**
 * Servlet implementation class AcceptMaintenanceByIdServlet
 */
@WebServlet("/AcceptMaintenanceByIdServlet")
public class AcceptMaintenanceByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AcceptMaintenanceByIdServlet() {
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
		MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
		String datetime = getTime();
		HttpSession session_peoplebean = request.getSession();
		PeopleBean peopleBean = (PeopleBean) session_peoplebean
				.getAttribute("session_peoplebean");
		LogBean logBean = new LogBean();
		LogDao logDao = new LogDaoImpl();
		int maintenance_id = Integer.parseInt(request
				.getParameter("maintenance_id"));
		String maintenance_equipment = request
				.getParameter("maintenance_equipment");
		String maintenance_IP = request.getParameter("maintenance_IP");
		String maintenance_MAC = request.getParameter("maintenance_MAC");
		int maintenance_record = Integer.parseInt(request
				.getParameter("maintenance_record"));
		int maintenance_sum_cost = Integer
				.parseInt(request.getParameter("sum"));
		if (maintenance_equipment == "") {
			request.setAttribute("message", "请输入设备名");
			request.setAttribute("maintenance_id", maintenance_id);
			request.getRequestDispatcher("./FetchMaintenanceAcceptByIdServlet")
					.forward(request, response);
			return;
		} else if (maintenance_IP == "") {
			request.setAttribute("message", "请输入IP地址");
			request.setAttribute("maintenance_id", maintenance_id);
			request.getRequestDispatcher("./FetchMaintenanceAcceptByIdServlet")
					.forward(request, response);
			return;
		} else if (maintenance_MAC == "") {
			request.setAttribute("message", "请输入MAC地址");
			request.setAttribute("maintenance_id", maintenance_id);
			request.getRequestDispatcher("./FetchMaintenanceAcceptByIdServlet")
					.forward(request, response);
			return;
		} else {
			try {
				int maintenance_accept_state = maintenanceDao
						.fetchAcceptStateByMaintenanceID(maintenance_id);
				if (maintenance_accept_state == 1) {
					request.setAttribute("message", "已经受理，无需重复受理");
					request.getRequestDispatcher("./MaintenanceAcceptServlet")
							.forward(request, response);
					return;
				} else {
					int rows = maintenanceDao.updateMaintenanceByAccept(
							maintenance_id, maintenance_equipment,
							maintenance_IP, maintenance_MAC,
							maintenance_record, maintenance_sum_cost, datetime);
					if (rows >= 1) {
						logBean.setLog_operation("受理");
						logBean.setLog_content("受理维修");
						logBean.setLog_time(datetime);
						logBean.setLog_maintenance_id(maintenance_id);
						logBean.setLog_people(peopleBean.getUser_name());
						logDao.addLog(logBean);
						request.setAttribute("message", "受理成功");
						request.getRequestDispatcher(
								"./MaintenanceAcceptServlet").forward(request,
								response);
						return;
					} else {
						request.setAttribute("message", "受理失败，请重试");
						request.getRequestDispatcher(
								"./MaintenanceAcceptServlet").forward(request,
								response);
						return;
					}
				}
			} catch (Exception e) {
				request.setAttribute("message", "请检查数据库连接");
				e.printStackTrace();
				request.getRequestDispatcher("./MaintenanceAcceptServlet")
						.forward(request, response);
				return;
			}
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

	// 获取当前时间
	private String getTime() {
		Date currDate = Calendar.getInstance().getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String datetime = simpleDateFormat.format(currDate);
		return datetime;
	}

}
