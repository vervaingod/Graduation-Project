package com.classroom.pc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classroom.bean.PeopleBean;
import com.classroom.biz.PeopleBiz;
import com.classroom.biz.PeopleBizImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		PeopleBean peopleBean = new PeopleBean();
		peopleBean.setUsername(username);
		peopleBean.setPassword(password);
		PeopleBiz peopleBiz = new PeopleBizImpl();
		PeopleBean peopleBean2 = new PeopleBean();
		peopleBean2 = peopleBiz.login(peopleBean);
		if (username.equals(peopleBean2.getUsername())) {

			request.getRequestDispatcher("./FetchWaitingApplyServlet").forward(
					request, response);

		} else {
			out.print("<script language='javascript'>alert('登录失败......');window.location.href='login.jsp';</script>");
			// response.getOutputStream().write(("no").toString().getBytes());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
