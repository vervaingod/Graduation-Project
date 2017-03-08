package com.classroom.pc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classroom.biz.ClassBiz;
import com.classroom.biz.ClassBizImpl;

/**
 * Servlet implementation class StopClassServlet
 */
@WebServlet("/StopClassServlet")
public class StopClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StopClassServlet() {
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
		String classnum = request.getParameter("classnum");
		ClassBiz classBiz = new ClassBizImpl();
		try {
			
			int rows=classBiz.stopClass(classnum);
			if(rows>=1){
				request.getRequestDispatcher("./FetchAllClassServlet").forward(request, response);
			}else{
				request.getRequestDispatcher("./FetchAllClassServlet").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
