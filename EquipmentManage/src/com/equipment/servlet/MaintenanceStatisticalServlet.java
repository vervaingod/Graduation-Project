package com.equipment.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.bean.MaintenanceBean;
import com.equipment.bean.PageBean;
import com.equipment.dao.CountInfoDao;
import com.equipment.dao.CountInfoDaoImpl;
import com.equipment.dao.MaintenanceDao;
import com.equipment.dao.MaintenanceDaoImpl;

/**
 * @author 许嘉阳
 * @功能 报修统计
 */
@WebServlet("/MaintenanceStatisticalServlet")
public class MaintenanceStatisticalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaintenanceStatisticalServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
	List<MaintenanceBean> maintenanceBeanlist = new ArrayList<MaintenanceBean>();
	CountInfoDao countInfoDao = new CountInfoDaoImpl();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		int maintenance_id = 0;
		if (request.getParameter("maintenance_id") != null
				&& request.getParameter("maintenance_id") != "") {
			maintenance_id = Integer.parseInt(request
					.getParameter("maintenance_id"));
		}
		String maintenance_department = request
				.getParameter("maintenance_department");
		if (maintenance_department == null) {
			maintenance_department = "";
		}

		String maintenance_fail_type = request
				.getParameter("maintenance_fail_type");
		if (maintenance_fail_type == null) {
			maintenance_fail_type = "";
		}
		int maintenance_dispatch_state = 3;
		if (request.getParameter("maintenance_dispatch_state") != null
				&& request.getParameter("maintenance_dispatch_state") != "") {
			maintenance_dispatch_state = Integer.parseInt(request
					.getParameter("maintenance_dispatch_state"));
		}
		String maintenance_time = request.getParameter("maintenance_time");
		if (maintenance_time == null) {
			maintenance_time = "";
		}
		int maintenance_accept_state = 3;
		if (request.getParameter("maintenance_accept_state") != null
				&& request.getParameter("maintenance_accept_state") != "") {
			maintenance_accept_state = Integer.parseInt(request
					.getParameter("maintenance_accept_state"));
		}
		int maintenance_confirm = 3;
		if (request.getParameter("maintenance_confirm") != null
				&& request.getParameter("maintenance_confirm") != "") {
			maintenance_confirm = Integer.parseInt(request
					.getParameter("maintenance_confirm"));
		}
		String maintenance_unit = request.getParameter("maintenance_unit");
		if (maintenance_unit == null) {
			maintenance_unit = "";
		}

		// 分页的实现
		String pagenoString = request.getParameter("pageno");
		int pageno = 1;
		if (pagenoString != null) {
			pageno = Integer.parseInt(pagenoString);
		}
		try {
			int maintenancerows = maintenanceDao.countAllMaintenance(
					maintenance_id, maintenance_department,
					maintenance_fail_type, maintenance_dispatch_state,
					maintenance_time, maintenance_accept_state,
					maintenance_confirm, maintenance_unit);
			int maxpage = maintenancerows % PageBean.ROWS_PRO_PAGE == 0 ? maintenancerows
					/ PageBean.ROWS_PRO_PAGE
					: (maintenancerows / PageBean.ROWS_PRO_PAGE + 1);
			if (pageno < 1) {
				pageno = 1;
			}
			if (pageno > maxpage) {
				pageno = maxpage;
			}
			int count_sum = countInfoDao.countSum();
			int count_sent_worker = countInfoDao.countSentWorker();
			int count_cancellation = countInfoDao.countCancellation();
			int countWaitAccept = countInfoDao.countWaitAccept();
			int countAccepted = countInfoDao.countAccepted();
			int countWaitConfirm = countInfoDao.countWaitConfirm();
			int countConfirmed = countInfoDao.countConfirmed();
			maintenanceBeanlist = maintenanceDao.fetchAllMaintenance(pageno,
					maintenance_id, maintenance_department,
					maintenance_fail_type, maintenance_dispatch_state,
					maintenance_time, maintenance_accept_state,
					maintenance_confirm, maintenance_unit);
			PageBean pageBean = new PageBean();
			pageBean.setMaxpage(maxpage);
			pageBean.setPageno(pageno);
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("count_sum", count_sum);
			request.setAttribute("count_sent_worker", count_sent_worker);
			request.setAttribute("count_cancellation", count_cancellation);
			request.setAttribute("countWaitAccept", countWaitAccept);
			request.setAttribute("countAccepted", countAccepted);
			request.setAttribute("countWaitConfirm", countWaitConfirm);
			request.setAttribute("countConfirmed", countConfirmed);
			request.setAttribute("maintenanceBeanlist", maintenanceBeanlist);

			request.setAttribute("maintenance_id", maintenance_id);
			request.setAttribute("maintenance_department",
					maintenance_department);
			request.setAttribute("maintenance_fail_type", maintenance_fail_type);
			request.setAttribute("maintenance_dispatch_state",
					maintenance_dispatch_state);
			request.setAttribute("maintenance_time", maintenance_time);
			request.setAttribute("maintenance_accept_state",
					maintenance_accept_state);
			request.setAttribute("maintenance_confirm", maintenance_confirm);
			request.setAttribute("maintenance_unit", maintenance_unit);
		} catch (Exception e) {
			request.setAttribute("message", "暂无统计信息");
			e.printStackTrace();
		}
		request.getRequestDispatcher("maintenance_statistical.jsp").forward(
				request, response);
	}
}
