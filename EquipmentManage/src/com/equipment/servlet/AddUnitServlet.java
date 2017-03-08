package com.equipment.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.bean.DepartmentBean;
import com.equipment.bean.PeopleBean;
import com.equipment.dao.DepartmentDao;
import com.equipment.dao.DepartmentDaoImpl;
import com.equipment.dao.PeopleDao;
import com.equipment.dao.PeopleDaoImpl;

/**
 * Servlet implementation class AddUnitServlet
 */
@WebServlet("/AddUnitServlet")
public class AddUnitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUnitServlet() {
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
		String department_name = request.getParameter("department_name");
		String department_linkman = request.getParameter("department_linkman");
		String department_username = request
				.getParameter("department_username");
		String department_tel = request.getParameter("department_tel");

		DepartmentBean departmentBean = new DepartmentBean();
		PeopleBean peopleBean = new PeopleBean();
		PeopleDao peopleDao = new PeopleDaoImpl();
		DepartmentDao departmentDao = new DepartmentDaoImpl();
		departmentBean.setDepartment_name(department_name);
		departmentBean.setDepartment_linkman(department_linkman);
		departmentBean.setDepartment_username(department_username);
		departmentBean.setDepartment_tel(department_tel);
		departmentBean.setDepartment_type(3);
		peopleBean.setUser_username(department_username);
		peopleBean.setUser_name(department_linkman);
		peopleBean.setUser_phone(department_tel);
		peopleBean.setUser_password("123456");
		peopleBean.setUser_role(10);
		try {
			int count_unit = departmentDao
					.countDepartmentByname(department_name);
			int count_people = peopleDao
					.countUserByusername(department_username);
			if (count_unit >= 1) {
				request.setAttribute("message", "该维修公司已存在！");
			} else if (count_people >= 1) {
				request.setAttribute("message", "该用户名已经存在！");
			} else {
				int adddepartment_rows = departmentDao
						.addDepartment(departmentBean);
				int department_id = departmentDao
						.findDepartmentIdByname(department_name);
				peopleBean.setUser_department_id(department_id);
				int addpeople_rows = peopleDao.addPeople(peopleBean);
				if (adddepartment_rows >= 1 && addpeople_rows >= 1) {
					request.setAttribute("message", "添加成功");
				} else {
					request.setAttribute("message", "添加失败");
				}
			}
		} catch (Exception e) {
			request.setAttribute("message", "请检查数据库连接");
			e.printStackTrace();
		}
		request.getRequestDispatcher("./MaintainingUnitServlet").forward(
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
