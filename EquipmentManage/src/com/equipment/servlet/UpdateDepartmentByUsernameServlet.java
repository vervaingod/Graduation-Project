package com.equipment.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.dao.DepartmentDao;
import com.equipment.dao.DepartmentDaoImpl;

/**
 * Servlet implementation class UpdateDepartmentByUsernameServlet
 */
@WebServlet("/UpdateDepartmentByUsernameServlet")
public class UpdateDepartmentByUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateDepartmentByUsernameServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		String department_username = request
				.getParameter("department_username");
		String department_name = request.getParameter("department_name");
		String department_linkman = request.getParameter("department_linkman");
		String department_tel = request.getParameter("department_tel");
		DepartmentDao departmentDao = new DepartmentDaoImpl();
		try {
			int rows = departmentDao.updateDepartmentByUsername(
					department_username, department_name, department_linkman,
					department_tel);
			if (rows >= 1) {
				request.setAttribute("message", "修改成功");
				request.getRequestDispatcher("./OrganizationDepartmentServlet")
						.forward(request, response);
			} else {
				request.setAttribute("message", "修改失败");
				request.getRequestDispatcher("./OrganizationDepartmentServlet")
						.forward(request, response);
			}

		} catch (Exception e) {
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
