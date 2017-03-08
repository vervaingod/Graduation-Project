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
 * Servlet implementation class UpdateUserByUsernameServlet
 */
@WebServlet("/UpdateUserByUsernameServlet")
public class UpdateUserByUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserByUsernameServlet() {
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
		String user_username = request.getParameter("user_username");
		String user_name = request.getParameter("user_name");
		String user_phone = request.getParameter("user_phone");
		PeopleDao peopleDao = new PeopleDaoImpl();
		try {
			int rows = peopleDao.updateUserByUsername(user_username, user_name,
					user_phone);
			if (rows >= 1) {
				request.setAttribute("message", "修改成功");
				request.getRequestDispatcher("./FetchUserByRoleServlet")
						.forward(request, response);
			} else {
				request.setAttribute("message", "修改失败");
			}
		} catch (Exception e) {
			request.setAttribute("message", "请检查数据库连接");
			e.printStackTrace();
		}
		request.getRequestDispatcher("./FetchUserByRoleServlet").forward(
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
