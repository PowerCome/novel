package com.linpeirou.www.view;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.linpeirou.www.po.User;
import com.linpeirou.www.service.Service;
import com.linpeirou.www.util.Final;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		String pwdTwo = pwd;
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		Service sv = new Service();
		
		
		try {
			if(Final.wrongInput.equals(name) || Final.wrongInput.equals(pwd) || Final.wrongInput.equals(code)) {
				setTag("信息不能为空", request, response);
			}else if(sv.isRightPassword(pwd) == 0) {
				setTag("密码格式错误，请输入6-25位数字英文组合", request, response);
			}else if( code.equalsIgnoreCase((String) request.getSession().getAttribute("code")) ){
				User user = sv.isExist(name, pwd);
				if( user != null ) {
					session.setAttribute("pwd",pwdTwo );
					session.setAttribute("user", user);
					if(user.getRoleId() == 1) {
						response.sendRedirect("/Hotels/MainServlet?method=getHotel&role=coustomer");
					}
					else if(user.getRoleId() == 2) {
						response.sendRedirect("/Hotels/ForAdministrator/homePage.jsp");
					}else if(user.getRoleId() == 3) {
						response.sendRedirect("/Hotels/ForSuper/homePage.jsp");
					}
				}
				else {
					setTag("用户名或密码输入错误", request, response);
				}
			}
			else {
				setTag("验证码输入错误", request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
			
	public void setTag(String tag, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("tag", tag);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		return;
	}
}
		
		



