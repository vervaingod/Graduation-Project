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
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserServlet() {
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
		String user_name = request.getParameter("user_name");
		String user_phone = request.getParameter("user_phone");
		String user_copyright = request.getParameter("user_copyright");
		String user_technical_support = request
				.getParameter("user_technical_support");
		HttpSession session_peoplebean = request.getSession();
		PeopleBean peopleBean = (PeopleBean) session_peoplebean
				.getAttribute("session_peoplebean");
		PeopleBean peopleBean2 = new PeopleBean();
		peopleBean2.setUser_id(peopleBean.getUser_id());
		peopleBean2.setUser_name(user_name);
		peopleBean2.setUser_phone(user_phone);
		peopleBean2.setUser_copyright(user_copyright);
		peopleBean2.setUser_technical_support(user_technical_support);
		try {
			PeopleDao peopleDao = new PeopleDaoImpl();
			int rows = peopleDao.updateUserByUserBean(peopleBean2);
			if (rows >= 1) {
				PeopleBean peopleBean3 = new PeopleBean();
				peopleBean3 = peopleDao.findUserInformationByID(peopleBean
						.getUser_id());
				session_peoplebean.setAttribute("session_peoplebean",
						peopleBean3);
				request.setAttribute("message", "修改成功！！！");
				request.getRequestDispatcher("basicinfomation.jsp").forward(
						request, response);
			} else {
				request.setAttribute("message", "修改失败");
				request.getRequestDispatcher("basicinfomation.jsp").forward(
						request, response);
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
