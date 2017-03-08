package com.equipment.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.bean.MaintenanceBean;
import com.equipment.dao.MaintenanceDao;
import com.equipment.dao.MaintenanceDaoImpl;

/**
 * Servlet implementation class FetchMaintenanceDispatchByIdServlet
 */
@WebServlet("/FetchMaintenanceDispatchByIdServlet")
public class FetchMaintenanceDispatchByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchMaintenanceDispatchByIdServlet() {
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
		String maintenance_id = request.getParameter("id");
		MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
		MaintenanceBean maintenanceBean = new MaintenanceBean();
		try {
			maintenanceBean = maintenanceDao.findMaintenanceById(Integer
					.parseInt(maintenance_id));
			request.setAttribute("maintenanceBean", maintenanceBean);
			request.getRequestDispatcher("maintenance_dispatch_detailed.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
