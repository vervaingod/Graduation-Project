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
 * 
 * @author 许嘉阳
 * @功能 通过维修单编号确认维修
 */
@WebServlet("/ConfirmMaintenanceByMaintenanceID")
public class ConfirmMaintenanceByMaintenanceID extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmMaintenanceByMaintenanceID() {
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

		Date currDate = Calendar.getInstance().getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String datatime = simpleDateFormat.format(currDate);
		HttpSession session_peoplebean = request.getSession();
		PeopleBean peopleBean = (PeopleBean) session_peoplebean
				.getAttribute("session_peoplebean");
		LogBean logBean = new LogBean();
		LogDao logDao = new LogDaoImpl();

		int maintenance_id = Integer.parseInt(request
				.getParameter("maintenance_id"));
		MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
		try {
			int maintenance_confirm = maintenanceDao
					.fetchConfirmStateByMaintenanceID(maintenance_id);
			if (maintenance_confirm == 1) {
				request.setAttribute("message", "您已确认过，无需重新确认！");
			} else {
				int rows = maintenanceDao
						.confirmMaintenanceByMaintenanceID(maintenance_id);
				if (rows >= 1) {
					logBean.setLog_operation("确认");
					logBean.setLog_content("确认维修完成");
					logBean.setLog_time(datatime);
					logBean.setLog_people(peopleBean.getUser_name());
					logBean.setLog_maintenance_id(maintenance_id);
					logDao.addLog(logBean);
					request.setAttribute("message", "确认成功");
				} else {
					request.setAttribute("message", "确认失败，请重试！");
				}
			}
		} catch (Exception e) {
			request.setAttribute("message", "请检查数据库连接");
			e.printStackTrace();
		}
		request.getRequestDispatcher("./MaintenanceConfirmServlet").forward(
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
