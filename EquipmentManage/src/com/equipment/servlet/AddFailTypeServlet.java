package com.equipment.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.equipment.dao.FailTypeDao;
import com.equipment.dao.FailTypeDaoImpl;

/**
 * Servlet implementation class AddFailTypeServlet
 */
@WebServlet("/AddFailTypeServlet")
public class AddFailTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFailTypeServlet() {
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
		String fail_type_name = request.getParameter("fail_type_name");
		String fail_type_service_fee = request
				.getParameter("fail_type_service_fee");
		FailTypeDao failTypeDao = new FailTypeDaoImpl();
		int count = 0;
		int rows = 0;
		try {
			count = failTypeDao.countFailTypeByName(fail_type_name);
			if (count >= 1) {
				request.setAttribute("message", "该故障已经存在");
				request.getRequestDispatcher("./FailTypeServlet").forward(
						request, response);
			} else {
				rows = failTypeDao.addFailType(fail_type_name,
						fail_type_service_fee);
				if (rows >= 1) {
					request.setAttribute("message", "添加成功");
					request.getRequestDispatcher("./FailTypeServlet").forward(
							request, response);
				} else {
					request.setAttribute("message", "添加失败，请检查数据库连接");
					request.getRequestDispatcher("./FailTypeServlet").forward(
							request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "请检查数据库连接");
			request.getRequestDispatcher("./FailTypeServlet").forward(request,
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
