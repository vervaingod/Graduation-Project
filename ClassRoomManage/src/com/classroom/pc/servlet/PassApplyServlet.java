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
 * Servlet implementation class PassApplyServlet
 */
@WebServlet("/PassApplyServlet")
public class PassApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PassApplyServlet() {
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
			int rows = applyInfoBiz.passApply(id);
			if (rows >= 1) {
				request.getRequestDispatcher("./FetchWaitingApplyServlet").forward(
						request, response);
			} else {
				request.getRequestDispatcher("./FetchWaitingApplyServlet").forward(
						request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
