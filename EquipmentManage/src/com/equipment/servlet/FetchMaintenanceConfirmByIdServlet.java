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
import com.equipment.dao.CostDao;
import com.equipment.dao.CostDaoImpl;
import com.equipment.dao.DispatchDao;
import com.equipment.dao.DispatchDaoImpl;
import com.equipment.dao.MaintenanceDao;
import com.equipment.dao.MaintenanceDaoImpl;
import com.equipment.dao.PeopleDao;
import com.equipment.dao.PeopleDaoImpl;

/**
 * 
 * @author 许嘉阳
 * @功能 通过id查看某一个具体的维修信息
 */
@WebServlet("/FetchMaintenanceConfirmByIdServlet")
public class FetchMaintenanceConfirmByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchMaintenanceConfirmByIdServlet() {
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
		int maintenance_id = Integer.parseInt(request
				.getParameter("maintenance_id"));
		String user_name = null;
		String record = null;
		MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
		MaintenanceBean maintenanceBean = new MaintenanceBean();
		DispatchDao dispatchDao = new DispatchDaoImpl();
		DispatchBean dispatchBean = new DispatchBean();
		CostDao costDao = new CostDaoImpl();
		List<CostBean> costBeanlist = new ArrayList<CostBean>();
		PeopleDao peopleDao = new PeopleDaoImpl();
		try {
			costBeanlist = costDao.fetchAllCostByMaintenanceID(maintenance_id);
			maintenanceBean = maintenanceDao
					.findMaintenanceById(maintenance_id);
			dispatchBean = dispatchDao
					.fetchDispatchByMaintenanceId(maintenance_id);
			user_name = peopleDao.findNameByID(dispatchBean
					.getDispatch_user_id());
			if (maintenanceBean.getMaintenance_record() == 0) {
				record = "否";
			} else {
				record = "是";
			}
			request.setAttribute("record", record);
			request.setAttribute("costBeanlist", costBeanlist);
			request.setAttribute("user_name", user_name);
			request.setAttribute("maintenanceBean", maintenanceBean);
			request.setAttribute("dispatchBean", dispatchBean);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "请检查数据库连接");
		}
		request.getRequestDispatcher("maintenance_confirm_detailed.jsp")
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
