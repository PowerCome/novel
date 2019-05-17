package com.linpeirou.www.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linpeirou.www.service.Service;
import com.linpeirou.www.util.Final;


/**
 * Servlet implementation class ForgetPwdServlet
 */
@WebServlet("/ForgetPwdServlet")
public class ForgetPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String phone = request.getParameter("phone");
		String idcard = request.getParameter("idcard");
		String newPwd = request.getParameter("newPwd");
		String newPwd2 = request.getParameter("newPwd2");
		Service sv = new Service();
		
		try {
			if(Final.wrongInput.equals(phone) || Final.wrongInput.equals(idcard) 
					|| Final.wrongInput.equals(newPwd) || Final.wrongInput.equals(newPwd2)) {
				setTag("信息不能为空", request, response);
			}else if(sv.isRightPassword(newPwd) == 0) {
				setTag("密码格式错误，请输入6-25位数字英文组合", request, response);
			}else if(sv.forgetPwd(phone, idcard, newPwd, newPwd2)) {
				request.setAttribute("tag", "密码设置成功，请重新登陆");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}catch (Exception e) {
			try {
				String wrong = e.getMessage();
				if ("两次输入的密码不一致".equals(wrong)) {
					setTag(wrong, request, response);
				} else if ("该用户不存在".equals(wrong)) {
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
		request.getRequestDispatcher("/forgetPwd.jsp").forward(request, response);
		return;
	}

}
