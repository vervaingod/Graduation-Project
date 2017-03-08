package com.classroom.pc.servlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
 * Servlet implementation class FetchAllClassroom
 */
@WebServlet("/FetchAllClassroomServlet")
public class FetchAllClassroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchAllClassroomServlet() {
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
		ClassroomBiz classroomBiz = new ClassroomBizImpl();
		List<ClassroomBean> classroomBeanlist = classroomBiz
				.fetchAllClassroom();
		String message = (String) request.getAttribute("message");
		request.setAttribute("message", message);
		request.setAttribute("classroomBeanlist", classroomBeanlist);
		request.getRequestDispatcher("searchclassroom.jsp").forward(request,
				response);

	}

	protected void sendObject(Object obj, HttpServletResponse response) {
		try {
			OutputStream os = response.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
