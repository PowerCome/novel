package com.linpeirou.www.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linpeirou.www.po.User;
import com.linpeirou.www.service.ServiceForOrder;

/**
 * Servlet implementation class ReservaionServlet
 */
public class ReservaionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceForOrder svorder = new ServiceForOrder();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String startYear =  request.getParameter("startYear");
		String endYear =  request.getParameter("endYear");
		String startMonth =  request.getParameter("startMonth");
		String endMonth =  request.getParameter("endMonth");
		String startDay =  request.getParameter("startDay");
		String endDay =  request.getParameter("endDay");
		String startTime=  request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String hotelId =  (String) request.getSession().getAttribute("hotelId");
		User user =  (User) request.getSession().getAttribute("user");
		String userId = user.getId()+"";
		String roomId = (String) request.getSession().getAttribute("roomId");
		String type = (String) request.getSession().getAttribute("type");
		startMonth = pulsZero(startMonth);
		startDay = pulsZero(startDay);
		endMonth = pulsZero(endMonth);
		endDay = pulsZero(endDay);
		startTime = pulsZero(startTime);
		endTime = pulsZero(endTime);
		System.out.println("endTime="+endTime);
		String startDate = startYear + startMonth + startDay;
		String endDate = endYear + endMonth + endDay;
		
		try {
			if( svorder.isRightInput(startDate, endDate, startMonth, startDay, startYear, endDay, endMonth) ) {
				if( svorder.canOrder(startDate,endDate, roomId) ) {
					svorder.putOrder(startDate, endDate, type ,startTime, endTime, hotelId, userId, roomId );
					request.setAttribute("tag", "预定成功！");
					request.getRequestDispatcher("/ForCoustomer/roomOrder.jsp").forward(request, response);
				}else {
						request.setAttribute("tag", "该房间该时间段已被预定，请选择其他房间或修改预定时间");
						request.getRequestDispatcher("/ForCoustomer/roomOrder.jsp").forward(request, response);
				}
			}else {
					request.setAttribute("tag", "选择错误!可能是几点原因：1.选择的日期不存在 2.选择的入住时间比退房时间晚 3.选择的日期已经过去");
					request.getRequestDispatcher("/ForCoustomer/roomOrder.jsp").forward(request, response);
				}
			}catch (Exception e) {
					e.printStackTrace();
				}
		}
	
	public String pulsZero(String item) {
		if(item.length() == 1) {
			return "0"+item;
		}
		return item;
	}

}
