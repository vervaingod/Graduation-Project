package com.classroom.pc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classroom.bean.ClassBean;
import com.classroom.biz.ClassBiz;
import com.classroom.biz.ClassBizImpl;

/**
 * Servlet implementation class FetchAllClassServlet
 */
@WebServlet("/FetchAllClassServlet")
public class FetchAllClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchAllClassServlet() {
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
		ClassBiz classBiz = new ClassBizImpl();
		List<ClassBean> classBeanList = classBiz.fetchAllClass();
		request.setAttribute("classBeanList", classBeanList);
		request.getRequestDispatcher("searchclass.jsp").forward(request,
				response);
	}

}
