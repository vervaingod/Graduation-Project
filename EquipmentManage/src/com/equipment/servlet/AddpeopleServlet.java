package com.equipment.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.equipment.bean.PeopleBean;
import com.equipment.dao.PeopleDao;
import com.equipment.dao.PeopleDaoImpl;

/**
 * Servlet implementation class AddpeopleServlet
 */
@WebServlet("/AddpeopleServlet")
public class AddpeopleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddpeopleServlet() {
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

		HttpSession session_peoplebean = request.getSession();
		PeopleBean peopleBean = (PeopleBean) session_peoplebean
				.getAttribute("session_peoplebean");
		PeopleDao peopleDao = new PeopleDaoImpl();
		PeopleBean peopleBean2 = new PeopleBean();
		try {
			peopleBean2.setUser_username(user_username);
			peopleBean2.setUser_password("123456");
			peopleBean2.setUser_name(user_name);
			peopleBean2.setUser_department_id(peopleBean
					.getUser_department_id());
			peopleBean2.setUser_phone(user_phone);
			int role = peopleBean.getUser_role();
			if (role == 0) {
				peopleBean2.setUser_role(1);
			} else if (role == 10) {
				peopleBean2.setUser_role(11);
			} else {
				peopleBean2.setUser_role(21);
			}
			String message = null;
			int rows = peopleDao.addPeople(peopleBean2);
			if (rows >= 1) {
				message = "添加成功";
			} else {
				message = "添加失败";
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("./FetchUserByRoleServlet").forward(
					request, response);
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
