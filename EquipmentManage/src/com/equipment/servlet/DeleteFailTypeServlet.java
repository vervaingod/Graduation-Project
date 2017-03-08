package com.equipment.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.bean.FailTypeBean;
import com.equipment.dao.FailTypeDao;
import com.equipment.dao.FailTypeDaoImpl;

/**
 * Servlet implementation class DeleteFailTypeServlet
 */
@WebServlet("/DeleteFailTypeServlet")
public class DeleteFailTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteFailTypeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		int rows = 0;
		int fail_type_id = Integer.parseInt(request.getParameter("id"));
		FailTypeDao failTypeDao = new FailTypeDaoImpl();
		List<FailTypeBean> failTypeBeanlist = new ArrayList<FailTypeBean>();
		String display = "none";
		try {
			rows = failTypeDao.deleteFailTypeById(fail_type_id);
			failTypeBeanlist = failTypeDao.fillAllFailType(1);
			if (rows >= 1) {
				request.setAttribute("message", "删除成功");
			} else {
				request.setAttribute("message", "删除失败");
			}
		} catch (Exception e) {
			request.setAttribute("message", "请检查数据库连接！");
			e.printStackTrace();
		}
		request.setAttribute("display", display);
		request.setAttribute("failTypeBeanlist", failTypeBeanlist);
		request.getRequestDispatcher("fail_type.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
