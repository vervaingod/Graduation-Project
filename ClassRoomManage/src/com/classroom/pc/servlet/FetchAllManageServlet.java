package com.classroom.pc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classroom.bean.PeopleBean;
import com.classroom.biz.PeopleBiz;
import com.classroom.biz.PeopleBizImpl;

/**
 * Servlet implementation class FetchAllManageServlet
 */
@WebServlet("/FetchAllManageServlet")
public class FetchAllManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchAllManageServlet() {
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
		PeopleBiz peopleBiz = new PeopleBizImpl();
		List<PeopleBean> peopleBeanList = peopleBiz.fetchAllManageList();
		request.setAttribute("peopleBeanList", peopleBeanList);
		request.getRequestDispatcher("searchmanage.jsp").forward(request,
				response);
	}

}
