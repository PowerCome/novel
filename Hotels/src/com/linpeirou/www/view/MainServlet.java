package com.linpeirou.www.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.linpeirou.www.dao.HotelDaoImpl;
import com.linpeirou.www.dao.PageBean;
import com.linpeirou.www.dao.RoomDaoImpl;
import com.linpeirou.www.dao.UserDaoImpl;
import com.linpeirou.www.dao.VerifyCode;
import com.linpeirou.www.po.Hotel;
import com.linpeirou.www.po.Room;
import com.linpeirou.www.po.User;
import com.linpeirou.www.service.Service;
import com.linpeirou.www.service.ServiceForHotel;
import com.linpeirou.www.service.ServiceForOrder;
import com.linpeirou.www.service.ServiceForRoom;
import com.linpeirou.www.util.Final;
import com.linpeirou.www.util.ServiceUtil;

@WebServlet("/MainServlet")
public class MainServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	ServiceForHotel svh = new ServiceForHotel();
	ServiceForRoom svr = new ServiceForRoom();
	Service sv = new Service();
	ServiceForOrder svo = new ServiceForOrder();

	public String delHotel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		svh.deletHotel(id);
		return "/MainServlet?method=getHotel&role=super";
	}

	public String getUser(HttpServletRequest request, HttpServletResponse response) {
		PageBean<User> pb = new PageBean<User>();
		ServiceUtil.getPb(request, UserDaoImpl.class, "findAllUser", pb);
		return "/ForSuper/userLevel.jsp";
	}

	public String getHotel(HttpServletRequest request, HttpServletResponse response)  {
		PageBean<Hotel> pb = new PageBean<Hotel>();
		ServiceUtil.getPb(request, HotelDaoImpl.class, "findSomeHotel", pb);
		String role = request.getParameter("role");
		if (Final.adm.equals(role)) {
			return "/ForAdministrator/allHotelInformation.jsp";
		} else if (Final.superAdm.equals(role)) {
			return "/ForSuper/allHotelInformation.jsp";
		} else if (Final.coustomer.equals(role)) {
			return "/ForCoustomer/homePage.jsp";
		}
		return null;
	}

	public String userLevel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String wdo = request.getParameter("wdo");
		String id = request.getParameter("id");
		String roleId = request.getParameter("roleId");
		sv.handleLevel(wdo, id, roleId);
		return "/MainServlet?method=getUser";
	}

	public String getVerifyCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();
		request.getSession().setAttribute("code", vc.getText());
		VerifyCode.output(image, response.getOutputStream());
		return null;
	}

	public String getCoustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageBean<User> pb = new PageBean<User>();
		ServiceUtil.getPb(request, UserDaoImpl.class, "findSomeUser",pb);
		return "/ForAdministrator/allCoustomer.jsp";
	}

	public String getRoom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String hotelId = null;
		if (request.getParameter("hotelId") != null) {
			hotelId = request.getParameter("hotelId");
		} else {
			hotelId = (String) request.getSession().getAttribute("hotelId");
			System.out.println(hotelId);
		}
		request.getSession().setAttribute("hotelId", hotelId);
		PageBean<Room> pb = new PageBean<Room>();
		ServiceUtil.getPb(request, RoomDaoImpl.class, "findSomeRoom", pb, hotelId);
		String role = request.getParameter("role");
		if (Final.adm.equals(role)) {
			return "/ForAdministrator/allRoom.jsp";
		} else if (Final.coustomer.equals(role)) {
			return "/ForCoustomer/allRoom.jsp";
		}
		return null;
	}

	public String delRoom(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 将酒店id保存，便于getRoom
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		request.setAttribute("hotelId", request.getParameter("hotelId"));
		svr.delRoom(roomId);
		return "/MainServlet?method=getRoom&role=adm";
	}

	public String getHotelInitialValue(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 保存后便于修改酒店的jsp界面获得原始参数
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("name", request.getParameter("name"));
		request.setAttribute("level", request.getParameter("level"));
		request.setAttribute("score", request.getParameter("score"));
		request.setAttribute("type", request.getParameter("type"));
		return "/ForSuper/resetHotelInformation.jsp";
	}

	public String getRoomInitialValue(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 保存后便于修改酒店的jsp界面获得原始参数
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("type", request.getParameter("type"));
		request.setAttribute("area", request.getParameter("area"));
		request.setAttribute("picture", request.getParameter("picture"));
		request.setAttribute("bedWidth", request.getParameter("bedWidth"));
		request.setAttribute("price", request.getParameter("price"));
		request.setAttribute("hotelId", request.getParameter("hotelId"));
		return "/ForAdministrator/resetRoom.jsp";
	}

	public String getRoomInformation(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			String roomId = request.getParameter("roomId");
			String type = request.getParameter("type");
			Calendar cal = Calendar.getInstance();
			request.getSession().setAttribute("year", cal.get(Calendar.YEAR));
			request.getSession().setAttribute("month", cal.get(Calendar.MONTH) + 1);
			request.getSession().setAttribute("roomId", roomId);
			request.getSession().setAttribute("type", type);
			return "/ForCoustomer/roomOrder.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getOrder(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		request.getSession().setAttribute("orders", svo.getOrder(user.getId()));
		return "/ForCoustomer/userOrder.jsp";
	}

}