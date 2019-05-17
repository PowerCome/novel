package com.linpeirou.www.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.linpeirou.www.dao.OrderImpl;
import com.linpeirou.www.dao.PageBean;
import com.linpeirou.www.po.Hotel;
import com.linpeirou.www.po.Order;

public class ServiceForOrder {
	private OrderImpl ord = new OrderImpl();
	private Calendar cal = Calendar.getInstance();
	private static HashMap<Integer, Integer> map = new HashMap<>();
	static {
		map.put(1, 31);
		map.put(3, 31);
		map.put(4, 30);
		map.put(5, 31);
		map.put(6, 30);
		map.put(7, 31);
		map.put(8, 31);
		map.put(9, 30);
		map.put(10, 31);
		map.put(11, 30);
		map.put(12, 31);

	}

	public Boolean isRightInput(String startDate, String endDate, String startMonth, String startDay, String startYear,
			String endDay, String endMonth) {
		int year = Integer.parseInt(startYear);
		// 判断平闰年
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			map.put(2, 29);
		} else {
			map.put(2, 28);
		}
		// 入住日期是否小于退房日期
		if (Integer.parseInt(startDate) < Integer.parseInt(endDate)) {
			// 当月入住日期是否大于或等于今日日期
			if (Integer.parseInt(startMonth) == cal.get(Calendar.MONTH + 1)) {
				if (Integer.parseInt(startDay) >= cal.get(Calendar.DATE)) {
					// 选择的日期是否存在
					if (map.get(Integer.parseInt(startMonth)) > Integer.parseInt(startDay)
							&& map.get(Integer.parseInt(endMonth)) > Integer.parseInt(endDay)) {
						return true;
					}
					return false;
				} else {
					return false;
				}
			} else if (map.get(Integer.parseInt(startMonth)) > Integer.parseInt(startDay)
					&& map.get(Integer.parseInt(endMonth)) > Integer.parseInt(endDay)) {
				return true;
			}
			return true;
		}
		return false;
	}

	public Boolean canOrder(String startDate, String endDate, String roomId) throws Exception {
		if (ord.canOrder(startDate, endDate, roomId).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean putOrder(String startDate, String endDate, String type, String startTime, String endTime,
			String hotelId, String userId, String roomId) {
		return ord.addOrder(startDate, endDate, type, startTime, endTime, hotelId, userId, roomId);

	}

	public boolean canCancel(String startDate, String startTime) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh");
		Date start = formatter.parse(startDate + " " + startTime);
		Date now = new Date();
		//判断入住时间是否比当前时间晚3个小时以上
		if (((start.getTime() - now.getTime()) / (1000 * 60 * 60)) >= 3) {
			return true;
		}
		return false;
	}

	public List<Order> getOrder(Integer id) {
		return ord.getOrder(id);

	}
	
	public PageBean<Order> findSomeOrder(int pc, int ps) {
		PageBean<Order> pb = new PageBean<Order>();
		pb.setPageCode(pc);
		pb.setPageSize(ps);
		pb.setTotalRecord(ord.totalPage());
		pb.setBeanList(ord.findSomeOrder(pc, ps));
		double tp = 1.0*pb.getTotalRecord()/pb.getPageSize();
		pb.setTotalPage((int) Math.ceil(tp));
		return pb;
	}

	public List<Order> getCancelOrder(List<String> cancelList) {
		List<Order> orders = new ArrayList<>();
		for( int i = 0 ; i < cancelList.size(); i++) {
			String orderId = cancelList.get(i);
			orders.add(ord.getCancelOrder(orderId));
		}
		return orders;
	}

	public boolean isValid(String startDate, String startTime) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh");
		Date start = formatter.parse(startDate + " " + startTime);
		Date now = new Date();
		//入住时间是否比处理时间晚
		if (start.compareTo(now) > 0 ) {
			return false;
		}
		return true;
	}

	public void delCancelOrder(String orderId) {
		ord.delCancelOrder(orderId);
		
	}

}
