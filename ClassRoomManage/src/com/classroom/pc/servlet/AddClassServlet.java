package com.classroom.pc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classroom.bean.ClassBean;
import com.classroom.biz.ClassBiz;
import com.classroom.biz.ClassBizImpl;
import com.classroom.biz.ClassroomBiz;
import com.classroom.biz.ClassroomBizImpl;

/**
 * Servlet implementation class AddClassServlet
 */
@WebServlet("/AddClassServlet")
public class AddClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		ClassBean classBean =new ClassBean();
		ClassBiz classBiz =new ClassBizImpl();
		ClassroomBiz classroomBiz =new ClassroomBizImpl();
		String classnum=request.getParameter("classnum");
		String classname=request.getParameter("classname");
		String classroom=request.getParameter("classroom");
	 classBean.setClassname(classname);
	 classBean.setClassnum(classnum);
	 classBean.setClassroom(classroom);
	
	try {
		int addclassrows=classBiz.addClass(classBean);
		if(addclassrows==1){
			int changeclassroomrows=classroomBiz.changeClassroomToBusy(classroom);
			if(changeclassroomrows==1){
				System.out.println("haha");
				request.getRequestDispatcher("./FetchAllClassServlet").forward(request, response);
			}
		}else{
			System.out.println("tianjiashibai");
		}
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	}

}
