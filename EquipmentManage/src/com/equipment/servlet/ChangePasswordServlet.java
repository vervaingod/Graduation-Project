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
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePasswordServlet() {
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
		String old_password = request.getParameter("old_password");
		String new_password = request.getParameter("new_password");
		String confirm_password = request.getParameter("confirm_password");
		PeopleDao peopleDao = new PeopleDaoImpl();
		HttpSession session_peoplebean = request.getSession();
		PeopleBean peopleBean = (PeopleBean) session_peoplebean
				.getAttribute("session_peoplebean");
		if (peopleBean.getUser_password().equals(old_password)) {
			if (new_password.equals("")) {
				request.setAttribute("message", "新密码不能为空");
				request.getRequestDispatcher("changepassword.jsp").forward(
						request, response);
			} else {
				if (new_password.equals(confirm_password)) {
					int rows = 0;
					try {
						rows = peopleDao.changePassword(
								peopleBean.getUser_id(), new_password);
						if (rows >= 1) {
							request.getRequestDispatcher("login.jsp").forward(
									request, response);
						} else {
							request.setAttribute("message", "修改失败！");
							request.getRequestDispatcher("changepassword.jsp")
									.forward(request, response);

						}
					} catch (Exception e) {
						request.setAttribute("message", "修改失败，请检查数据库连接");
						request.getRequestDispatcher("changepassword.jsp")
								.forward(request, response);
					}
				} else {
					request.setAttribute("message", "两次密码输入不一致！");
					request.getRequestDispatcher("changepassword.jsp").forward(
							request, response);
				}
			}
		} else {
			request.setAttribute("message", "原密码不正确");
			request.getRequestDispatcher("changepassword.jsp").forward(request,
					response);
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
