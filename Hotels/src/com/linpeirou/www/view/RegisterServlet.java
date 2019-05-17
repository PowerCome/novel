package com.linpeirou.www.view;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linpeirou.www.service.Service;
import com.linpeirou.www.util.Final;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");

		String pwd1 = request.getParameter("pwd1");
		String pwd2 = request.getParameter("pwd2");
		String name = request.getParameter("name");
		String idcard = request.getParameter("idcard");
		String phone = request.getParameter("phone");
		Service sv = new Service();

		try {
			if(Final.wrongInput.equals(name) || Final.wrongInput.equals(pwd1) 
					|| Final.wrongInput.equals(idcard) || Final.wrongInput.equals(phone)) {
				setTag("信息不能为空", request, response);
			}else if(sv.isRightPhone(phone)== 0) {
				setTag("手机号格式错误", request, response);
			}else if(sv.isRightPassword(pwd1) == 0) {
				setTag("密码格式错误，请输入6-25位数字英文组合", request, response);
			}if (sv.register(name, pwd1, pwd2, idcard, phone, 1)) {
				setTag( "注册成功", request, response);
			}
			
		} catch (Exception e) {
			try {
				String wrong = e.getMessage();
				if ("两次输入的密码不一致".equals(wrong)) {
					setTag( wrong, request, response);
				} else if ("该用户已存在".equals(wrong)) {
					setTag( wrong, request, response);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	
	public void setTag(String tag, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("tag", tag);
		request.getRequestDispatcher("/register.jsp").forward(request, response);
		return;
	}

}
