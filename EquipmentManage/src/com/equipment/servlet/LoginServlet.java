package com.equipment.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.bean.PeopleBean;
import com.equipment.dao.PeopleDao;
import com.equipment.dao.PeopleDaoImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String user_username = request.getParameter("username");
		String user_password = request.getParameter("password");
		PeopleBean peopleBean = new PeopleBean();
		peopleBean.setUser_username(user_username);
		peopleBean.setUser_password(user_password);
		PeopleDao peopleDao = new PeopleDaoImpl();
		PeopleBean peopleBean2 = new PeopleBean();
		try {
			peopleBean2 = peopleDao.login(peopleBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user_username.equals(peopleBean2.getUser_username())) {
			request.getSession()
					.setAttribute("session_peoplebean", peopleBean2);
			request.getRequestDispatcher("./WorkbenchServlet").forward(
					request, response);
		} else {
			System.out.println("no!");
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
