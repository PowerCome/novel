package com.linpeirou.www.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linpeirou.www.service.ServiceForHotel;
import com.linpeirou.www.util.Final;

/**
 * Servlet implementation class ResetHotelServlet
 */
@WebServlet("/ResetHotelServlet")
public class ResetHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ServiceForHotel sv = new ServiceForHotel();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String level = request.getParameter("level");
		Double score = Double.parseDouble(request.getParameter("score"));
		String type = request.getParameter("type");

		try {
			if (Final.wrongInput.equals(id) || Final.wrongInput.equals(name) || Final.wrongInput.equals(level)
					|| Final.wrongInput.equals(type) || Final.wrongInput.equals(score)) {
				setTag("信息不能为空", request, response);
			} else if (sv.resetHotel(id, name, level, score, type)) {
				setTag("信息修改成功", request, response);
			}
		} catch (Exception e) {
			try {
				String wrong = e.getMessage();
				if ("酒店不存在".equals(wrong)) {
					setTag(wrong, request, response);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void setTag(String tag, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("tag", tag);
		request.getRequestDispatcher("/ForSuper/resetHotelInformation.jsp").forward(request, response);
		return;
	}
}
