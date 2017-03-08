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
 * Servlet implementation class UpdateEvaluateServlet
 */
@WebServlet("/UpdateEvaluateServlet")
public class UpdateEvaluateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateEvaluateServlet() {
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
		String remark = request.getParameter("remark");
		ApplyInfoBiz applyInfoBiz = new ApplyInfoBizImpl();
		try {
			int rows = applyInfoBiz.updateEvaluate(id, remark);
			if (rows >= 1) {
				System.out.println("success!");
				request.getRequestDispatcher("./FetchAppledInfoServlet").forward(request, response);
			}else{
				System.out.println("fail");
				request.getRequestDispatcher("./FetchAppledInfoServlet").forward(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
