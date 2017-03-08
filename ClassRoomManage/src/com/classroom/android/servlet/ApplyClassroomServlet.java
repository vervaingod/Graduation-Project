package com.classroom.android.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classroom.bean.ApplyInfoBean;
import com.classroom.biz.ApplyInfoBiz;
import com.classroom.biz.ApplyInfoBizImpl;

/**
 * Servlet implementation class ApplyClassroomServlet
 */
@WebServlet("/ApplyClassroomServlet")
public class ApplyClassroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplyClassroomServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplyInfoBean applyInfoBean = new ApplyInfoBean();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String classnum = request.getParameter("classnum");
		String classname = request.getParameter("classname");
		String classnote = request.getParameter("classnote");
		applyInfoBean.setApplyclassroom(classname);
		applyInfoBean.setApplystudent(username);

		System.out.println(username + "  " + classnum + "  " + classname + "  " + classnote);
		ApplyInfoBiz applyInfoBiz = new ApplyInfoBizImpl();
		try {
			int rows = applyInfoBiz.addApply(applyInfoBean);
			if (rows >= 1) {
				System.out.println("yes");
				response.getOutputStream().write(("yes").toString().getBytes());
			} else {
				System.out.println("");
				response.getOutputStream().write(("no").toString().getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
