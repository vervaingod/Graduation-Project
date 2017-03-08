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
import com.equipment.dao.DepartmentDao;
import com.equipment.dao.DepartmentDaoImpl;
import com.equipment.dao.MaintenanceDao;
import com.equipment.dao.MaintenanceDaoImpl;

/**
 * @author 许嘉阳
 * @功能 报修确认
 */
@WebServlet("/MaintenanceConfirmServlet")
public class MaintenanceConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaintenanceConfirmServlet() {
		super();
	}

	DepartmentDao departmentDao = new DepartmentDaoImpl();
	String maintenance_department = null;
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
		String maintenance_unit = request.getParameter("maintenance_unit");
		if (maintenance_unit == null) {
			maintenance_unit = "";
		}
		String maintenance_fail_type = request
				.getParameter("maintenance_fail_type");
		if (maintenance_fail_type == null) {
			maintenance_fail_type = "";
		}
		String maintenance_time = request.getParameter("maintenance_time");
		if (maintenance_time == null) {
			maintenance_time = "";
		}
		int maintenance_confirm = 3;
		if (request.getParameter("maintenance_confirm") != null
				&& request.getParameter("maintenance_confirm") != "") {
			maintenance_confirm = Integer.parseInt(request
					.getParameter("maintenance_confirm"));
		}
		request.setAttribute("maintenance_id", maintenance_id);
		request.setAttribute("maintenance_unit", maintenance_unit);
		request.setAttribute("maintenance_fail_type", maintenance_fail_type);
		request.setAttribute("maintenance_time", maintenance_time);
		request.setAttribute("maintenance_confirm", maintenance_confirm);
		// 分页的实现
		String pagenoString = request.getParameter("pageno");
		int pageno = 1;
		if (pagenoString != null) {
			pageno = Integer.parseInt(pagenoString);
		}
		try {
			maintenance_department = departmentDao
					.findDepartmentById(peopleBean.getUser_department_id());
			int maintenancerow = maintenanceDao.countAllConfirmByDepartment(
					maintenance_department, maintenance_id, maintenance_unit,
					maintenance_fail_type, maintenance_time,
					maintenance_confirm);
			int maxpage = maintenancerow % PageBean.ROWS_PRO_PAGE == 0 ? maintenancerow
					/ PageBean.ROWS_PRO_PAGE
					: (maintenancerow / PageBean.ROWS_PRO_PAGE + 1);
			if (pageno < 1) {
				pageno = 1;
			}
			if (pageno > maxpage) {
				pageno = maxpage;
			}
			maintenanceBeanlist = maintenanceDao.fetchAllConfirmByDepartment(
					maintenance_department, pageno, maintenance_id,
					maintenance_unit, maintenance_fail_type, maintenance_time,
					maintenance_confirm);
			request.setAttribute("maintenanceBeanlist", maintenanceBeanlist);
			PageBean pageBean = new PageBean();
			pageBean.setMaxpage(maxpage);
			pageBean.setPageno(pageno);
			request.setAttribute("pageBean", pageBean);
		} catch (Exception e) {
			request.setAttribute("message", "暂无确认信息！");
			e.printStackTrace();
		}
		request.getRequestDispatcher("maintenance_confirm.jsp").forward(
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
