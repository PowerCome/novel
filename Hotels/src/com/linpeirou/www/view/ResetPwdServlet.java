package com.linpeirou.www.view;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linpeirou.www.po.User;
import com.linpeirou.www.service.Service;
import com.linpeirou.www.util.Final;


/**
 * Servlet implementation class HomePageServlet
 */
@WebServlet("/ResetPwdServlet")
public class ResetPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		User user = (User) request.getSession().getAttribute("user");
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String newPwd2 = request.getParameter("newPwd2");
		Service sv = new Service();

		try { 
			if (Final.wrongInput.equals( oldPwd ) || Final.wrongInput.equals( newPwd )
					|| Final.wrongInput.equals( newPwd2 )) {
				setTag("信息不能为空", request, response);
			} else if ( sv.isRightPassword(newPwd) == 0 ) {
				setTag("密码格式错误，请输入6-25位数字英文组合", request, response);
			} else if (sv.reSetPwd(oldPwd, newPwd, newPwd2, user.getName())) {
				request.setAttribute("tag", "密码修改成功，请重新登陆");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			try {
				String wrong = e.getMessage();
				if ("两次输入的密码不一致".equals(wrong)) {
					setTag(wrong, request, response);
				} else if ("旧密码不存在".equals(wrong)) {
					setTag(wrong, request, response);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/**
	 * 将信息存到request域中
	 * 
	 * @param tag
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void setTag(String tag, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = getUrl(request, response);
		request.setAttribute("tag", tag);
		request.getRequestDispatcher(url).forward(request, response);
		return;
	}

	/**
	 * 根据用户类型获取不同的路径
	 * 
	 * @param request
	 * @param response
	 * @return 路径
	 */
	public String getUrl(HttpServletRequest request, HttpServletResponse response) {
		String url = null;
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			if (user.getRoleId() == 1) {
				url = "/ForCoustomer/resetPwd.jsp";
			} else if( user.getRoleId() == 2 ) {
				url = "/ForAdministrator/resetPwd.jsp";
			}else if( user.getRoleId() == 3 ) {
				url = "/ForSuper/resetPwd.jsp";
			}
		}
		return url;
	}
}
