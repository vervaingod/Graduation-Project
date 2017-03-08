package com.equipment.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.equipment.bean.PageBean;
import com.equipment.bean.PeopleBean;
import com.equipment.dao.PeopleDao;
import com.equipment.dao.PeopleDaoImpl;

/**
 * Servlet implementation class FetchUserByRoleServlet
 */
@WebServlet("/FetchUserByRoleServlet")
public class FetchUserByRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchUserByRoleServlet() {
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

		HttpSession session_peoplebean = request.getSession();
		PeopleBean peopleBean = (PeopleBean) session_peoplebean
				.getAttribute("session_peoplebean");
		int role = peopleBean.getUser_role();
		int user_department_id = peopleBean.getUser_department_id();
		PeopleDao peopleDao = new PeopleDaoImpl();
		PageBean pageBean = new PageBean();
		List<PeopleBean> peopleBeanlist = null;
		String display = "none";
		// 分页的实现
		String pagenoString = request.getParameter("pageno");
		int pageno = 1;
		if (pagenoString != null) {
			pageno = Integer.parseInt(pagenoString);
		}
		// 根据登录身份 返回有相应部门的所有用户
		if (role == 0) {
			try {
				int userrows = peopleDao.countUserRowsByRole(1);
				int maxpage = userrows % PageBean.ROWS_PRO_PAGE == 0 ? userrows
						/ PageBean.ROWS_PRO_PAGE : (userrows
						/ PageBean.ROWS_PRO_PAGE + 1);
				if (pageno < 1) {
					pageno = 1;
				}
				if (pageno > maxpage) {
					pageno = maxpage;
				}
				peopleBeanlist = peopleDao.FindAllUserByRole(1, pageno);
				pageBean.setMaxpage(maxpage);
				pageBean.setPageno(pageno);
				request.setAttribute("pageBean", pageBean);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "请检查数据库连接");
			}
		} else {
			try {
				int userrows = peopleDao
						.countUserRowsByDepartment(user_department_id);
				int maxpage = userrows % PageBean.ROWS_PRO_PAGE == 0 ? userrows
						/ PageBean.ROWS_PRO_PAGE : (userrows
						/ PageBean.ROWS_PRO_PAGE + 1);
				if (pageno < 1) {
					pageno = 1;
				}
				if (pageno > maxpage) {
					pageno = maxpage;
				}
				peopleBeanlist = peopleDao.FindAllUserByDepeartment(
						user_department_id, pageno);
				pageBean.setMaxpage(maxpage);
				pageBean.setPageno(pageno);
				request.setAttribute("pageBean", pageBean);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "请检查数据库连接");
			}
		}
		// 如果有ID传入，返回对应编号的用户信息
		String user_id = request.getParameter("id");
		if (null == user_id) {
		} else {
			display = "block";
			PeopleBean peopleBean2 = new PeopleBean();
			PeopleDao peopleDao2 = new PeopleDaoImpl();
			try {
				peopleBean2 = peopleDao2.findUserInformationByID(Integer
						.parseInt(user_id));
				String user_username2 = peopleBean2.getUser_username();
				String user_name2 = peopleBean2.getUser_name();
				String user_phone2 = peopleBean2.getUser_phone();
				request.setAttribute("user_username", user_username2);
				request.setAttribute("user_name", user_name2);
				request.setAttribute("user_phone", user_phone2);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// 判断是添加还是修改
		String type = request.getParameter("type");
		if ("1".equals(type)) {
			request.setAttribute("peopleBeanlist", peopleBeanlist);
			request.getRequestDispatcher("internal_account_add.jsp").forward(
					request, response);
		} else {
			request.setAttribute("display", display);
			request.setAttribute("peopleBeanlist", peopleBeanlist);
			request.getRequestDispatcher("internal_account.jsp").forward(
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
