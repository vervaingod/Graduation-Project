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
import com.equipment.dao.DispatchDao;
import com.equipment.dao.DispatchDaoImpl;
import com.equipment.dao.LogDao;
import com.equipment.dao.LogDaoImpl;
import com.equipment.dao.MaintenanceDao;
import com.equipment.dao.MaintenanceDaoImpl;
import com.equipment.dao.PeopleDao;
import com.equipment.dao.PeopleDaoImpl;

/**
 * Servlet implementation class AddDispatchServlet
 */
@WebServlet("/AddDispatchServlet")
public class AddDispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddDispatchServlet() {
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
		String dispatch_expect_time = request
				.getParameter("dispatch_expect_time");
		String dispatch_maintenance_phone = null;
		int maintenance_id = Integer.parseInt(request
				.getParameter("maintenance_id"));
		String maintenance_man = request.getParameter("maintenance_man");
		PeopleDao peopleDao = new PeopleDaoImpl();
		MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
		LogBean logBean = new LogBean();
		LogDao logDao = new LogDaoImpl();
		try {
			dispatch_maintenance_phone = peopleDao
					.findPhoneByName(maintenance_man);
			int dispatch_user_id = peopleDao.findUserIdByname(maintenance_man);
			DispatchDao dispatchDao = new DispatchDaoImpl();
			int old_dispatchstate = maintenanceDao
					.fetchDispatchState(maintenance_id);
			if (old_dispatchstate == 0) {
				int rows = dispatchDao.addDispatch(dispatch_user_id,
						maintenance_id, dispatch_maintenance_phone,
						dispatch_expect_time);
				if (rows >= 1) {
					int new_dispatchstate = maintenanceDao
							.dispatch(maintenance_id);
					logBean.setLog_operation("派工");
					logBean.setLog_content("派遣维修工");
					logBean.setLog_people(peopleBean.getUser_name());
					logBean.setLog_time(datatime);
					logBean.setLog_maintenance_id(maintenance_id);
					logDao.addLog(logBean);
					if (new_dispatchstate >= 1) {
						request.setAttribute("message", "派工成功");
						request.getRequestDispatcher(
								"./MaintenanceDispatchServlet").forward(
								request, response);
					} else {
						request.setAttribute("message", "派工失败");
						request.getRequestDispatcher(
								"./MaintenanceDispatchServlet").forward(
								request, response);
					}
				} else {
					request.setAttribute("message", "派工失败");
					request.getRequestDispatcher("./MaintenanceDispatchServlet")
							.forward(request, response);
				}
			} else if (old_dispatchstate == 1) {
				request.setAttribute("message", "无需重复派工！！");
				request.getRequestDispatcher("./MaintenanceDispatchServlet")
						.forward(request, response);
			} else {
				request.setAttribute("message", "已经作废，不能派工！");
				request.getRequestDispatcher("./MaintenanceDispatchServlet")
						.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("message", "连接失败，请检查数据库！！");
			request.getRequestDispatcher("./MaintenanceDispatchServlet")
					.forward(request, response);
			e.printStackTrace();
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
