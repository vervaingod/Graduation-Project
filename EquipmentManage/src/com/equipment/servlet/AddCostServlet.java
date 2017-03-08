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
 * Servlet implementation class AddCostServlet
 */
@WebServlet("/AddCostServlet")
public class AddCostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCostServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		String cost_project = request.getParameter("cost_project");
		String cost_service = request.getParameter("cost_service");
		String cost_material = request.getParameter("cost_material");
		int cost_maintenance_id = Integer.parseInt(request
				.getParameter("cost_maintenance_id"));
		CostDao costDao = new CostDaoImpl();
		try {
			int rows = costDao.addCost(cost_project, cost_service,
					cost_material, cost_maintenance_id);
			if (rows >= 1) {
				request.setAttribute("message", "添加成功");
			} else {
				request.setAttribute("message", "添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "请检查数据库连接！");
		}
		request.setAttribute("maintenance_id", cost_maintenance_id);
		request.getRequestDispatcher("./FetchMaintenanceAcceptByIdServlet")
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
