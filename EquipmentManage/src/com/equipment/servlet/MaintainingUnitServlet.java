package com.equipment.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.equipment.bean.DepartmentBean;
import com.equipment.bean.PageBean;
import com.equipment.dao.DepartmentDao;
import com.equipment.dao.DepartmentDaoImpl;

/**
 * Servlet implementation class MaintainingUnitServlet
 */
@WebServlet("/MaintainingUnitServlet")
public class MaintainingUnitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaintainingUnitServlet() {
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
		DepartmentDao departmentDao = new DepartmentDaoImpl();
		List<DepartmentBean> departmentBeanlist = null;
		String type = request.getParameter("type");
		String display = "none";
		// 分页的实现
		String pagenoString = request.getParameter("pageno");
		int pageno = 1;
		if (pagenoString != null) {
			pageno = Integer.parseInt(pagenoString);
		}
		try {
			int unitrows = departmentDao.countMaintainingUnit();
			int maxpage = unitrows % PageBean.ROWS_PRO_PAGE == 0 ? unitrows
					/ PageBean.ROWS_PRO_PAGE : (unitrows
					/ PageBean.ROWS_PRO_PAGE + 1);
			if (pageno < 1) {
				pageno = 1;
			}
			if (pageno > maxpage) {
				pageno = maxpage;
			}
			departmentBeanlist = departmentDao.fetchAllMaintainingUnit(pageno);
			PageBean pageBean = new PageBean();
			pageBean.setMaxpage(maxpage);
			pageBean.setPageno(pageno);
			request.setAttribute("pageBean", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "请检查数据库的连接");
		}
		request.setAttribute("departmentBeanlist", departmentBeanlist);
		if ("1".equals(type)) {
			display = "block";
			request.setAttribute("display", display);
			request.getRequestDispatcher("maintaining_unit_add.jsp").forward(
					request, response);
		} else {
			// 如果有id传入，返回某个公司的具体信息
			String department_id = request.getParameter("id");
			DepartmentBean departmentBean = new DepartmentBean();
			if (department_id == null) {
				request.setAttribute("display", display);
			} else {
				try {
					display = "block";
					departmentBean = departmentDao.fetchDepartmentById(Integer
							.parseInt(department_id));
					String department_username = departmentBean
							.getDepartment_username();
					String department_name = departmentBean
							.getDepartment_name();
					String department_linkman = departmentBean
							.getDepartment_linkman();
					String department_tel = departmentBean.getDepartment_tel();
					request.setAttribute("display", display);
					request.setAttribute("department_username",
							department_username);
					request.setAttribute("department_name", department_name);
					request.setAttribute("department_linkman",
							department_linkman);
					request.setAttribute("department_tel", department_tel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("maintaining_unit.jsp").forward(
					request, response);
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
