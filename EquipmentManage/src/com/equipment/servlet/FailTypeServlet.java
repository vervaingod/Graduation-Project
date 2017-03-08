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
 * Servlet implementation class FailTypeServlet
 */
@WebServlet("/FailTypeServlet")
public class FailTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FailTypeServlet() {
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
		FailTypeDao failTypeDao = new FailTypeDaoImpl();
		List<FailTypeBean> failTypeBeanlist = new ArrayList<FailTypeBean>();
		String display = "none";
		String type = request.getParameter("type");
		String pagenoString = request.getParameter("pageno");
		int pageno = 1;
		if (pagenoString != null) {
			pageno = Integer.parseInt(pagenoString);
		}
		try {
			int failtyperows = failTypeDao.countFailType();
			int maxpage = failtyperows % PageBean.ROWS_PRO_PAGE == 0 ? failtyperows
					/ PageBean.ROWS_PRO_PAGE
					: (failtyperows / PageBean.ROWS_PRO_PAGE + 1);
			if (pageno < 1) {
				pageno = 1;
			}
			if (pageno > maxpage) {
				pageno = maxpage;
			}
			PageBean pageBean = new PageBean();
			pageBean.setMaxpage(maxpage);
			pageBean.setPageno(pageno);
			request.setAttribute("pageBean", pageBean);
			failTypeBeanlist = failTypeDao.fillAllFailType(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("failTypeBeanlist", failTypeBeanlist);
		if ("1".equals(type)) {
			request.getRequestDispatcher("fail_type_add.jsp").forward(request,
					response);
		} else {
			// 某个故障的具体信息
			String fail_type_id = request.getParameter("id");
			if (null == fail_type_id) {
			} else {
				display = "block";
				FailTypeBean failTypeBean = new FailTypeBean();
				try {
					failTypeBean = failTypeDao.fetchFailTypeById(Integer
							.parseInt(fail_type_id));
					String fail_type_name = failTypeBean.getFail_type_name();
					String fail_type_service_fee = failTypeBean
							.getFail_type_service_fee();
					request.setAttribute("fail_type_id", fail_type_id);
					request.setAttribute("fail_type_name", fail_type_name);
					request.setAttribute("fail_type_service_fee",
							fail_type_service_fee);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("display", display);
			request.getRequestDispatcher("fail_type.jsp").forward(request,
					response);
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
