package com.classroom.android.servlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
 * Servlet implementation class FetchStudentApplyInfoServlet
 */
@WebServlet("/FetchStudentApplyInfoServlet")
public class FetchStudentApplyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchStudentApplyInfoServlet() {
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
		System.out.println("haha");
		String applystudent=request.getParameter("username");
		System.out.println(applystudent);
		ApplyInfoBiz applyInfoBiz =new ApplyInfoBizImpl();
		try {
			List<ApplyInfoBean> applyInfoBeanlist=applyInfoBiz.fetchApplyByUsername(applystudent);
			sendObject(applyInfoBeanlist, response);
			System.out.println(applyInfoBeanlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
