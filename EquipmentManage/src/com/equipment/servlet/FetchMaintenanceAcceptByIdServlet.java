package com.equipment.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.bean.CostBean;
import com.equipment.bean.DispatchBean;
import com.equipment.bean.MaintenanceBean;
import com.equipment.bean.PeopleBean;
import com.equipment.dao.CostDao;
import com.equipment.dao.CostDaoImpl;
import com.equipment.dao.DispatchDao;
import com.equipment.dao.DispatchDaoImpl;
import com.equipment.dao.MaintenanceDao;
import com.equipment.dao.MaintenanceDaoImpl;
import com.equipment.dao.PeopleDao;
import com.equipment.dao.PeopleDaoImpl;

/**
 * Servlet implementation class FetchMaintenanceAcceptByIdServlet
 */
@WebServlet("/FetchMaintenanceAcceptByIdServlet")
public class FetchMaintenanceAcceptByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchMaintenanceAcceptByIdServlet() {
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
		if (maintenance_id == null) {
			maintenance_id = request.getAttribute("maintenance_id").toString();
		}
		MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
		MaintenanceBean maintenanceBean = new MaintenanceBean();
		DispatchDao dispatchDao = new DispatchDaoImpl();
		DispatchBean dispatchBean = new DispatchBean();
		PeopleBean peopleBean = new PeopleBean();
		PeopleDao peopleDao = new PeopleDaoImpl();
		List<CostBean> costBeanlist = new ArrayList<CostBean>();
		CostDao costDao = new CostDaoImpl();
		int sum = 0;
		try {
			maintenanceBean = maintenanceDao.findMaintenanceById(Integer
					.parseInt(maintenance_id));
			if (0 == maintenanceBean.getMaintenance_record()) {
				String nochecked = "checked='checked'";
				request.setAttribute("nochecked", nochecked);
			} else {
				String yeschecked = "checked='checked'";
				request.setAttribute("yeschecked", yeschecked);
			}
			dispatchBean = dispatchDao.fetchDispatchByMaintenanceId(Integer
					.parseInt(maintenance_id));
			peopleBean = peopleDao.findUserInformationByID(dispatchBean
					.getDispatch_user_id());
			sum = costDao.countSumCost(Integer.parseInt(maintenance_id));
			costBeanlist = costDao.fetchAllCostByMaintenanceID(Integer
					.parseInt(maintenance_id));
			String dispatch_user_name = peopleBean.getUser_name();
			String dispatch_user_phone = peopleBean.getUser_phone();
			request.setAttribute("costBeanlist", costBeanlist);
			request.setAttribute("dispatch_user_phone", dispatch_user_phone);
			request.setAttribute("dispatch_user_name", dispatch_user_name);
			request.setAttribute("maintenanceBean", maintenanceBean);
			request.setAttribute("dispatchBean", dispatchBean);
			request.setAttribute("sum", sum);
		} catch (Exception e) {
			request.setAttribute("message", "请检查数据库连接");
			e.printStackTrace();
		}
		request.getRequestDispatcher("maintenance_accept_detailed.jsp")
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
