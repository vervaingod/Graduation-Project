package com.equipment.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.dao.CostDao;
import com.equipment.dao.CostDaoImpl;

/**
 * Servlet implementation class DeleteCostServlet
 */
@WebServlet("/DeleteCostServlet")
public class DeleteCostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCostServlet() {
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
		int cost_id = Integer.parseInt(request.getParameter("cost_id"));
		String maintenance_id = request.getParameter("maintenance_id");
		CostDao costDao = new CostDaoImpl();
		try {
			int rows = costDao.deleteCostByID(cost_id);
			if (rows >= 1) {
				request.setAttribute("message", "删除成功");
			} else {
				request.setAttribute("message", "删除失败");
			}
		} catch (Exception e) {
			request.setAttribute("message", "请检查数据库的连接");
			e.printStackTrace();
		}
		request.setAttribute("maintenance_id", maintenance_id);
		request.getRequestDispatcher("FetchMaintenanceAcceptByIdServlet")
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
