package com.linpeirou.www.view;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linpeirou.www.dao.OrderImpl;
import com.linpeirou.www.dao.PageBean;
import com.linpeirou.www.po.Hotel;
import com.linpeirou.www.po.Order;
import com.linpeirou.www.service.ServiceForOrder;
import com.linpeirou.www.util.Final;
import com.linpeirou.www.util.ServiceUtil;

/**
 * Servlet implementation class CancelOrderServlet
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	ServiceForOrder svo = new ServiceForOrder();
	List<String> cancelList;
	
	public String addCancel(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (svo.canCancel(request.getParameter("startDate"), request.getParameter("startTime"))) {
				if( request.getServletContext().getAttribute("cancelList") == null ) {
					cancelList = new ArrayList<String>();
					request.getServletContext().setAttribute("cancelList", cancelList);
				}else {
					cancelList = (List<String>) request.getServletContext().getAttribute("cancelList");
				}
				cancelList.add(request.getParameter("orderId"));
				request.getServletContext().setAttribute("cancelList", cancelList);
				System.out.println((List<String>) request.getServletContext().getAttribute("cancelList"));
				request.setAttribute("tag", "申请请求已发送，请静待处理");
				return "/ForCoustomer/userOrder.jsp";
			} else {
				request.setAttribute("tag", "抱歉，您申请取消预约的时间不符合规定，无法申请");
				return "/ForCoustomer/userOrder.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getSomeOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageBean<Order> pb = new PageBean<Order>();
		ServiceUtil.getPb(request, OrderImpl.class, "findSomeOrder", pb);
		return "/ForAdministrator/userOrder.jsp";
	}

	public int getPc(HttpServletRequest request) {
		String value = request.getParameter("pc");
		if (value == null || value.trim().isEmpty()) {
			return 1;
		}
		return Integer.parseInt(value);
	}

	public String getCancelOrder(HttpServletRequest request, HttpServletResponse response) {
		if( request.getServletContext().getAttribute("cancelList") == null ) {
			return "/ForAdministrator/cancelOrder.jsp";
		}
		cancelList = (List<String>) request.getServletContext().getAttribute("cancelList");
		List<Order> orders = svo.getCancelOrder(cancelList);
		request.setAttribute("orders", orders);
		return "/ForAdministrator/cancelOrder.jsp";
	}

	public String delCancel(HttpServletRequest request, HttpServletResponse response) {
		try {
			//未失效就删除订单，并移除cancelList
			if (!svo.isValid(request.getParameter("startDate"), request.getParameter("startTime"))) {
				List<String> cancelList = (List<String>) request.getServletContext().getAttribute("cancelList");
				cancelList.remove(request.getParameter("orderId"));
				request.getServletContext().setAttribute("cancelList", cancelList);
				svo.delCancelOrder(request.getParameter("orderId"));
				request.setAttribute("tag", "取消成功！");
				return "/OrderServlet?method=getCancelOrder";
			} else {
				request.setAttribute("tag", "抱歉，您处理的请求已经过时");
				return "/OrderServlet?method=getCancelOrder";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
