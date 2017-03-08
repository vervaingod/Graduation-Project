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
 * Servlet implementation class AddClassRoomServlet
 */
@WebServlet("/AddClassRoomServlet")
public class AddClassRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddClassRoomServlet() {
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
		ClassroomBean classroomBean = new ClassroomBean();
		ClassroomBiz classroomBiz = new ClassroomBizImpl();

		String classroomnumber = request.getParameter("classroomnumber");
		String room = request.getParameter("room");
		int status = Integer.parseInt(request.getParameter("status"));
		String note = request.getParameter("note");
		classroomBean.setClassroomnumber(classroomnumber);
		classroomBean.setRoom(room);
		classroomBean.setState(status);
		classroomBean.setNote(note);
		int rows = classroomBiz.addClassroom(classroomBean);
		if (rows == 1) {
			request.getRequestDispatcher("./FetchAllClassroomServlet").forward(
					request, response);
		} else {
			request.getRequestDispatcher("./FetchAllClassroomServlet").forward(
					request, response);
		}
	}
}
