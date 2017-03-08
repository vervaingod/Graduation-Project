package com.classroom.pc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classroom.bean.ClassroomBean;
import com.classroom.biz.ClassroomBiz;
import com.classroom.biz.ClassroomBizImpl;

/**
 * Servlet implementation class SearchClassroomServlet
 */
@WebServlet("/SearchClassroomServlet")
public class SearchClassroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchClassroomServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String classroomnumber = request.getParameter("classroomnumber");
		String room = request.getParameter("room");
		ClassroomBiz classroomBiz = new ClassroomBizImpl();
		try {
			List<ClassroomBean> classroomBeanlist = classroomBiz
					.searchClassroom(classroomnumber, room);
			request.setAttribute("classroomBeanlist", classroomBeanlist);
			request.getRequestDispatcher("searchclassroom.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
