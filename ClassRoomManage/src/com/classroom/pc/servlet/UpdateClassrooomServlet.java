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
 * Servlet implementation class UpdateClassrooomServlet
 */
@WebServlet("/UpdateClassrooomServlet")
public class UpdateClassrooomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateClassrooomServlet() {
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
		String classroomnumber = request.getParameter("classroomnumber");
		String room = request.getParameter("room");
		int state = Integer.parseInt(request.getParameter("state"));
		String note = request.getParameter("note");
		ClassroomBiz classroomBiz = new ClassroomBizImpl();
		ClassroomBean classroomBean = new ClassroomBean();
		classroomBean.setClassroomnumber(classroomnumber);
		classroomBean.setState(state);
		classroomBean.setRoom(room);
		classroomBean.setNote(note);
		try {
			if (state != -1) {
				int rows = classroomBiz.updateClassroom(classroomBean);
				if (rows >= 1) {

				} else {

				}
			} else {
				int row = classroomBiz.deleteClassroom(classroomnumber);
				if (row >= 1) {

				} else {

				}
			}
			request.getRequestDispatcher("./FetchAllClassroomServlet").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
