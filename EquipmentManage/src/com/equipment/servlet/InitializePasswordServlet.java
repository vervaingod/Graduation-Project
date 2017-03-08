package com.equipment.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.dao.PeopleDao;
import com.equipment.dao.PeopleDaoImpl;

/**
 * Servlet implementation class InitializePasswordServlet
 */
@WebServlet("/InitializePasswordServlet")
public class InitializePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitializePasswordServlet() {
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
		int user_id = Integer.parseInt(request.getParameter("id"));
		PeopleDao peopleDao = new PeopleDaoImpl();
		String message = null;
		try {
			int rows = peopleDao.initializaPassword(user_id);
			if (rows >= 1) {
				message = "初始化成功";
			} else {
				message = "初始化失败";
			}

			String type = request.getParameter("type");
			if ("people".equals(type)) {
				request.setAttribute("message", message);
				request.getRequestDispatcher("./FetchUserByRoleServlet")
						.forward(request, response);
			} else {
				request.setAttribute("message", message);
				request.getRequestDispatcher("./OrganizationDepartmentServlet")
						.forward(request, response);
			}
		} catch (Exception e) {

		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
