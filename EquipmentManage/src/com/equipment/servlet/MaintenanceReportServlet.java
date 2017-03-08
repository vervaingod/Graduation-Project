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

import com.equipment.bean.LogBean;
import com.equipment.bean.MaintenanceBean;
import com.equipment.dao.LogDao;
import com.equipment.dao.LogDaoImpl;
import com.equipment.dao.MaintenanceDao;
import com.equipment.dao.MaintenanceDaoImpl;

/**
 * 
 * @author 许嘉阳
 * @功能 维修申报
 */
@WebServlet("/MaintenanceReportServlet")
public class MaintenanceReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaintenanceReportServlet() {
		super();
	}

	Date currDate = Calendar.getInstance().getTime();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	String datatime = simpleDateFormat.format(currDate);
	LogDao logDao = new LogDaoImpl();
	LogBean logBean = new LogBean();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String maintenance_department = request
				.getParameter("maintenance_department");
		String maintenance_name = request.getParameter("maintenance_name");
		String maintenance_phone = request.getParameter("maintenance_phone");
		String maintenance_fail_type = request
				.getParameter("maintenance_fail_type");
		String maintenance_unit = request.getParameter("maintenance_unit");
		String maintenance_description = request
				.getParameter("maintenance_description");
		MaintenanceBean maintenanceBean = new MaintenanceBean();
		maintenanceBean.setMaintenance_department(maintenance_department);
		maintenanceBean.setMaintenance_time(datatime);
		maintenanceBean.setMaintenance_name(maintenance_name);
		maintenanceBean.setMaintenance_phone(maintenance_phone);
		maintenanceBean.setMaintenance_fail_type(maintenance_fail_type);
		maintenanceBean.setMaintenance_unit(maintenance_unit);
		maintenanceBean.setMaintenance_description(maintenance_description);

		try {
			MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
			int maintenance_id = maintenanceDao.addMaintenance(maintenanceBean);
			if (maintenance_id >= 1) {
				logBean.setLog_operation("申报");
				logBean.setLog_content("维修申报");
				logBean.setLog_people(maintenance_name);
				logBean.setLog_time(datatime);
				logBean.setLog_maintenance_id(maintenance_id);
				int rows = logDao.addLog(logBean);
				if (rows >= 1) {
					request.setAttribute("message", "申报成功！");
				} else {
					request.setAttribute("message", "申报失败，请重试！");
				}
			} else {
				request.setAttribute("message", "申报失败，请重试！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "申报失败，请检查数据库连接");
		}
		request.getRequestDispatcher("maintenance_report.jsp").forward(request,
				response);
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
