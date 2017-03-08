package com.classroom.pc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classroom.biz.ApplyInfoBiz;
import com.classroom.biz.ApplyInfoBizImpl;

/**
 * Servlet implementation class DeleteApplyServlet
 */
@WebServlet("/DeleteApplyServlet")
public class DeleteApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteApplyServlet() {
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
		
		int id = Integer.parseInt(request.getParameter("id"));
		ApplyInfoBiz applyInfoBiz = new ApplyInfoBizImpl();
		try {
			int rows = applyInfoBiz.deleteApply(id);
			request.getRequestDispatcher("./FetchAppledInfoServlet").forward(
					request, response);

		} catch (Exception e) {
			request.getRequestDispatcher("./FetchAppledInfoServlet").forward(
					request, response);
		}
	}

}
