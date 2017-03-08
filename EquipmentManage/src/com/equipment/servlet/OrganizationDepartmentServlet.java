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
 * Servlet implementation class OrganizationDepartmentServlet
 */
@WebServlet("/OrganizationDepartmentServlet")
public class OrganizationDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrganizationDepartmentServlet() {
		super();
	}

	DepartmentDao departmentDao = new DepartmentDaoImpl();
	List<DepartmentBean> departmentBeanlist = null;
	String display = "none";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		// 分页的实现
		String pagenoString = request.getParameter("pageno");
		int pageno = 1;
		if (pagenoString != null) {
			pageno = Integer.parseInt(pagenoString);
		}
		try {
			int localpolicerows = departmentDao.countLocalPoliceStation();
			int maxpage = localpolicerows % PageBean.ROWS_PRO_PAGE == 0 ? localpolicerows
					/ PageBean.ROWS_PRO_PAGE
					: (localpolicerows / PageBean.ROWS_PRO_PAGE + 1);
			if (pageno < 1) {
				pageno = 1;
			}
			if (pageno > maxpage) {
				pageno = maxpage;
			}
			departmentBeanlist = departmentDao
					.fetchAllLocalPoliceStation(pageno);
			PageBean pageBean = new PageBean();
			pageBean.setMaxpage(maxpage);
			pageBean.setPageno(pageno);
			request.setAttribute("pageBean", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "请检查数据库连接！");
		}
		// 判断是添加新部门还是修改部门
		String type = request.getParameter("type");
		if ("1".equals(type)) {
			request.setAttribute("departmentBeanlist", departmentBeanlist);
			request.getRequestDispatcher("organization_department_add.jsp")
					.forward(request, response);
		} else {
			String department_id = request.getParameter("id");
			DepartmentBean departmentBean = new DepartmentBean();
			// 判断是否查看具体部门的信息
			if (null == department_id) {
			} else {
				// 查看某个部门的详细信息
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
					request.setAttribute("department_username",
							department_username);
					request.setAttribute("department_name", department_name);
					request.setAttribute("department_linkman",
							department_linkman);
					request.setAttribute("department_tel", department_tel);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("message", "请检查数据库连接");
				}
			}
			request.setAttribute("display", display);
			request.setAttribute("departmentBeanlist", departmentBeanlist);
			request.getRequestDispatcher("organization_department.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
