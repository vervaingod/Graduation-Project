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
import com.equipment.bean.PageBean;
import com.equipment.dao.FailTypeDao;
import com.equipment.dao.FailTypeDaoImpl;

/**
 * Servlet implementation class FetchAllCostServlet
 */
@WebServlet("/FetchAllCostServlet")
public class FetchAllCostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchAllCostServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	FailTypeDao failTypeDao = new FailTypeDaoImpl();
	FailTypeBean failTypeBean = new FailTypeBean();
	List<FailTypeBean> failTypeBeanlist = new ArrayList<FailTypeBean>();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String maintenance_id = request.getParameter("maintenance_id");
		String type = request.getParameter("type");
		// 分页的实现
		String pagenoString = request.getParameter("pageno");
		int pageno = 1;
		if (pagenoString != null) {
			pageno = Integer.parseInt(pagenoString);
		}

		try {
			int rows = failTypeDao.countFailType();
			int maxpage = rows % PageBean.ROWS_PRO_PAGE == 0 ? rows
					/ PageBean.ROWS_PRO_PAGE
					: (rows / PageBean.ROWS_PRO_PAGE + 1);
			if (pageno < 1) {
				pageno = 1;
			}
			if (pageno > maxpage) {
				pageno = maxpage;
			}

			failTypeBeanlist = failTypeDao.fillAllFailType(pageno);
			request.setAttribute("failTypeBeanlist", failTypeBeanlist);
			PageBean pageBean = new PageBean();
			pageBean.setMaxpage(maxpage);
			pageBean.setPageno(pageno);
			request.setAttribute("pageBean", pageBean);
		} catch (Exception e) {
			request.setAttribute("message", "请检查数据库连接");
			e.printStackTrace();
		}
		String fail_type_id = request.getParameter("id");
		if (fail_type_id == null) {
		} else {
			try {
				failTypeBean = failTypeDao.fetchFailTypeById(Integer
						.parseInt(fail_type_id));
				String cost_project = failTypeBean.getFail_type_name();
				String cost_service = failTypeBean.getFail_type_service_fee();
				request.setAttribute("cost_project", cost_project);
				request.setAttribute("cost_service", cost_service);
			} catch (Exception e) {
				request.setAttribute("message", "添加失败，请检查数据库连接");
				e.printStackTrace();
			}
		}
		request.setAttribute("maintenance_id", maintenance_id);
		request.getRequestDispatcher("cost_add.jsp").forward(request, response);
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
