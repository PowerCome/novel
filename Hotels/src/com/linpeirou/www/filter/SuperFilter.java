package com.linpeirou.www.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linpeirou.www.po.User;
import com.linpeirou.www.util.Final;
import com.linpeirou.www.util.JdbcUtil;

@WebFilter("/SuperFilter")
public class SuperFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		User user = (User) req.getSession().getAttribute("user");

		try {
			if (user == null) {
				if ("/Hotel/login.jsp".equals(req.getRequestURI()) || "/Hotel/register.jsp".equals(req.getRequestURI())
						|| "/Hotel/forgetPwd.jsp".equals(req.getRequestURI())) {
					chain.doFilter(request, response);
				} else {
					JdbcUtil.setTag("您还未登陆，请先登陆再访问", "/login.jsp", req, (HttpServletResponse) response);
				}
			} else {
				if (user.getRoleId() == Final.superRoleId) {
					chain.doFilter(request, response);
				} else {
					JdbcUtil.setTag("您不是超级管理员，请不要随意访问", "/login.jsp", req, (HttpServletResponse) response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
