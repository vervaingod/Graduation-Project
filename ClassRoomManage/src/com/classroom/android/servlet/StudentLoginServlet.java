package com.classroom.android.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classroom.bean.PeopleBean;
import com.classroom.biz.PeopleBiz;
import com.classroom.biz.PeopleBizImpl;

/**
 * Servlet implementation class StudentLoginServlet
 */
@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentLoginServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PeopleBean peopleBean = new PeopleBean();
		peopleBean.setUsername(username);
		peopleBean.setPassword(password);
		PeopleBiz peopleBiz = new PeopleBizImpl();
		PeopleBean peopleBean2 = new PeopleBean();
		peopleBean2 = peopleBiz.login(peopleBean);
		if (username.equals(peopleBean2.getUsername())) {
			response.getOutputStream().write(("yes").toString().getBytes());
		} else {
			response.getOutputStream().write(("no").toString().getBytes());
		}
	}

}
