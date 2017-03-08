package com.classroom.pc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classroom.biz.PeopleBiz;
import com.classroom.biz.PeopleBizImpl;

/**
 * Servlet implementation class StartStudentAccountServlet
 */
@WebServlet("/StartStudentAccountServlet")
public class StartStudentAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartStudentAccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		String id = request.getParameter("id");

		PeopleBiz peopleBiz = new PeopleBizImpl();
		try {
			int rows = peopleBiz.StartStudent(id);

			if (rows >= 1) {
				request.getRequestDispatcher("./FetchAllStudentServlet").forward(request, response);
			}else{
				request.getRequestDispatcher("./FetchAllStudentServlet").forward(request, response);
			}

		} catch (Exception e) {
			request.getRequestDispatcher("./FetchAllStudentServlet").forward(request, response);
		}
	}

}
