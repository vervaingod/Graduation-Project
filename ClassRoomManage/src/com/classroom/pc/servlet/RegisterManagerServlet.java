package com.classroom.pc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.classroom.bean.PeopleBean;
import com.classroom.biz.PeopleBiz;
import com.classroom.biz.PeopleBizImpl;

import net.sf.json.JSONSerializer;

/**
 * Servlet implementation class RegisterManagerServlet
 */
@WebServlet("/RegisterManagerServlet")
public class RegisterManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterManagerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String type = request.getParameter("type");
		if ("json".equals(type)) {
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
		} else {
			String name = request.getParameter("name");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String classid = request.getParameter("classid");
			String phone = request.getParameter("phone");
			PeopleBean peopleBean = new PeopleBean();
			peopleBean.setName(name);
			peopleBean.setUsername(username);
			peopleBean.setPassword(password);
			peopleBean.setEmail(email);
			peopleBean.setClassid(classid);
			peopleBean.setPhone(Integer.parseInt(phone));
			PeopleBiz peopleBiz = new PeopleBizImpl();
			int rows = peopleBiz.registerManager(peopleBean);
			if (rows == 1) {
				request.getRequestDispatcher("./FetchAllManageServlet").forward(request, response);
				System.out.println("注册成功");
			} else {
				request.getRequestDispatcher("./FetchAllManageServlet").forward(request, response);
				System.out.println("注册失败");

			}
		}
	}
}
