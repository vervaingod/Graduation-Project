package com.equipment.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.equipment.bean.MaintenanceBean;
import com.equipment.bean.PageBean;
import com.equipment.bean.PeopleBean;
import com.equipment.dao.MaintenanceDao;
import com.equipment.dao.MaintenanceDaoImpl;
import com.equipment.dao.PeopleDao;
import com.equipment.dao.PeopleDaoImpl;

/**
 * @author 许嘉阳
 * @功能 维修派工
 */
@WebServlet("/MaintenanceDispatchServlet")
public class MaintenanceDispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaintenanceDispatchServlet() {
		super();
	}

	PeopleDao peopleDao = new PeopleDaoImpl();
	String maintenance_unit = null;
	MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
	List<MaintenanceBean> maintenanceBeanlist = new ArrayList<MaintenanceBean>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		HttpSession session_peoplebean = request.getSession();
		PeopleBean peopleBean = (PeopleBean) session_peoplebean
				.getAttribute("session_peoplebean");
		int maintenance_id = 0;
		if (request.getParameter("maintenance_id") != null
				&& request.getParameter("maintenance_id") != "") {
			maintenance_id = Integer.parseInt(request
					.getParameter("maintenance_id"));
		}
		String maintenance_department = request
				.getParameter("maintenance_department");
		if (maintenance_department == null) {
			maintenance_department = "";
		}
		String maintenance_fail_type = request
				.getParameter("maintenance_fail_type");
		if (maintenance_fail_type == null) {
			maintenance_fail_type = "";
		}
		int maintenance_dispatch_state = 3;
		if (request.getParameter("maintenance_dispatch_state") != null
				&& request.getParameter("maintenance_dispatch_state") != "") {
			maintenance_dispatch_state = Integer.parseInt(request
					.getParameter("maintenance_dispatch_state"));
		}
		String maintenance_time = request.getParameter("maintenance_time");
		if (maintenance_time == null) {
			maintenance_time = "";
		}
		int maintenance_accept_state = 3;
		if (request.getParameter("maintenance_accept_state") != null
				&& request.getParameter("maintenance_accept_state") != "") {
			maintenance_accept_state = Integer.parseInt(request
					.getParameter("maintenance_accept_state"));
		}
		int maintenance_confirm = 3;
		if (request.getParameter("maintenance_confirm") != null
				&& request.getParameter("maintenance_confirm") != "") {
			maintenance_confirm = Integer.parseInt(request
					.getParameter("maintenance_confirm"));
		}
		String pagenoString = request.getParameter("pageno");
		int pageno = 1;
		if (pagenoString != null) {
			pageno = Integer.parseInt(pagenoString);
		}
		try {
			maintenance_unit = peopleDao.findDepartmentById(peopleBean
					.getUser_department_id());
			int maintenancerows = maintenanceDao.countAllMaintenanceByUnit(
					maintenance_unit, maintenance_id, maintenance_department,
					maintenance_fail_type, maintenance_dispatch_state,
					maintenance_time, maintenance_accept_state,
					maintenance_confirm);
			int maxpage = maintenancerows % PageBean.ROWS_PRO_PAGE == 0 ? maintenancerows
					/ PageBean.ROWS_PRO_PAGE
					: (maintenancerows / PageBean.ROWS_PRO_PAGE + 1);
			if (pageno < 1) {
				pageno = 1;
			}
			if (pageno > maxpage) {
				pageno = maxpage;
			}
			maintenanceBeanlist = maintenanceDao.fetchAllMaintenanceByUnit(
					maintenance_unit, pageno, maintenance_id,
					maintenance_department, maintenance_fail_type,
					maintenance_dispatch_state, maintenance_time,
					maintenance_accept_state, maintenance_confirm);
			request.setAttribute("maintenanceBeanlist", maintenanceBeanlist);
			PageBean pageBean = new PageBean();
			pageBean.setMaxpage(maxpage);
			pageBean.setPageno(pageno);
			request.setAttribute("pageBean", pageBean);

			request.setAttribute("maintenance_id", maintenance_id);
			request.setAttribute("maintenance_department",
					maintenance_department);
			request.setAttribute("maintenance_fail_type", maintenance_fail_type);
			request.setAttribute("maintenance_dispatch_state",
					maintenance_dispatch_state);
			request.setAttribute("maintenance_time", maintenance_time);
			request.setAttribute("maintenance_accept_state",
					maintenance_accept_state);
			request.setAttribute("maintenance_confirm", maintenance_confirm);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "暂无数据");
		}
		request.getRequestDispatcher("maintenance_dispatch.jsp").forward(
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
