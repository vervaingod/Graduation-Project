package com.classroom.pc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classroom.bean.ApplyInfoBean;
import com.classroom.biz.ApplyInfoBiz;
import com.classroom.biz.ApplyInfoBizImpl;

/**
 * Servlet implementation class FetchWaitingApplyServlet
 */
@WebServlet("/FetchWaitingApplyServlet")
public class FetchWaitingApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchWaitingApplyServlet() {
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
		ApplyInfoBiz applyInfoBiz = new ApplyInfoBizImpl();
		List<ApplyInfoBean> applyInfoBeanList = applyInfoBiz
				.fetchWaitingApply();
		request.setAttribute("applyInfoBeanList", applyInfoBeanList);
		request.getRequestDispatcher("waitingapply.jsp").forward(request, response);
	}

}
