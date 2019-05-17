package com.linpeirou.www.dao;

import java.util.List;

import com.linpeirou.www.po.Order;
import com.linpeirou.www.util.BeanHandler;
import com.linpeirou.www.util.BeanListHandler;
import com.linpeirou.www.util.JdbcUtil;

public class OrderImpl {

	public  OrderImpl() {
	}
	
	public List<Order> canOrder(String startDate, String endDate, String roomId) {
		String sql = "select * from userOrder where roomId = ? and (startDate <= ? and endDate >= ? or  startDate between ? and ? or endDate between ? and ?) ";
		return JdbcUtil.executeQueary(sql, new BeanListHandler<Order>(Order.class), roomId, startDate, endDate,
				startDate, endDate, startDate, endDate);
	}

	public Boolean addOrder(String startDate, String endDate, String type, String startTime, String endTime,
			String hotelId, String userId, String roomId) {
		String sql = "insert into userOrder (startDate, endDate, type, startTime, endTime, hotelId, userId, roomId) values(?,?,?,?,?,?,?,?)";
		JdbcUtil.executeUpdate(sql, startDate, endDate, type, startTime, endTime, hotelId, userId, roomId);
		return true;
	}

	public List<Order> getOrder(Integer id) {
		String sql = "select * from userOrder where userId = ?";
		return JdbcUtil.executeQueary(sql, new BeanListHandler<Order>(Order.class), id);
	}

	public List<Order> getAllOrder() {
		String sql = "select * from userOrder";
		return JdbcUtil.executeQueary(sql, new BeanListHandler<Order>(Order.class));
	}

	public int totalPage() {
		String sql = "select count(*) from userOrder";
		return JdbcUtil.totalPage(sql);
	}

	public List<Order> findSomeOrder(Integer pc, Integer ps) {
		String sql = "select * from userOrder limit ?,?";
		return JdbcUtil.executeQueary(sql, new BeanListHandler<Order>(Order.class), (pc - 1) * ps, ps);
	}

	public Order getCancelOrder(String orderId) {
		String sql = "select * from userOrder where id = ?";
		return JdbcUtil.executeQueary(sql, new BeanHandler<Order>(Order.class), orderId);
	}

	public void delCancelOrder(String orderId) {
		String sql = "delete from userOrder where id = ?";
		JdbcUtil.executeUpdate(sql, orderId);
	}
}
