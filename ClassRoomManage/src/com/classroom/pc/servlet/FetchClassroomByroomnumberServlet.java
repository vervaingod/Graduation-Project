package com.classroom.pc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classroom.bean.ClassroomBean;
import com.classroom.biz.ClassroomBiz;
import com.classroom.biz.ClassroomBizImpl;

/**
 * Servlet implementation class FetchClassroomByServlet
 */
@WebServlet("/FetchClassroomByroomnumberServlet")
public class FetchClassroomByroomnumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchClassroomByroomnumberServlet() {
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
		response.setContentType("text/html");
		String classroomnumber = request.getParameter("id");

		try {
			ClassroomBiz classroomBiz = new ClassroomBizImpl();
			ClassroomBean classroomBean = classroomBiz
					.fetchClassroomByRoomnumber(classroomnumber);
			request.setAttribute("classroomBean", classroomBean);
			request.getRequestDispatcher("classroomdetail.jsp").forward(
					request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
