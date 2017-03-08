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
 * Servlet implementation class UpdateFailTypeServlet
 */
@WebServlet("/UpdateFailTypeServlet")
public class UpdateFailTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateFailTypeServlet() {
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
		int fail_type_id = Integer.parseInt(request
				.getParameter("fail_type_id"));
		String fail_type_name = request.getParameter("fail_type_name");
		String fail_type_service_fee = request
				.getParameter("fail_type_service_fee");
		try {
			FailTypeDao failTypeDao = new FailTypeDaoImpl();
			int rows = failTypeDao.updateFailTypeById(fail_type_id,
					fail_type_name, fail_type_service_fee);
			if (rows >= 1) {
				request.setAttribute("message", "更新成功");
			} else {
				request.setAttribute("message", "更新失败，请重试");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("./FailTypeServlet").forward(request,
				response);
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
