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
 * Servlet implementation class RegisterStudentServlet
 */
@WebServlet("/RegisterStudentServlet")
public class RegisterStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("Sno");
		String name = request.getParameter("Name");
		String classid = request.getParameter("Class");
		String password = request.getParameter("Pwd");
		PeopleBiz peopleBiz = new PeopleBizImpl();
		int rows = peopleBiz.fetchUsername(username);
		if (rows == 1) {
			// 学号被注册
			response.getOutputStream().write("1".toString().getBytes());
		} else {
			PeopleBean peopleBean = new PeopleBean();
			peopleBean.setName(name);
			peopleBean.setUsername(username);
			peopleBean.setClassid(classid);
			peopleBean.setPassword(password);
			peopleBiz.registerStudent(peopleBean);
			response.getOutputStream().write("2".toString().getBytes());
		}
	}

}
