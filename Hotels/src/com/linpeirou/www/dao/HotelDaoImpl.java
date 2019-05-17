package com.linpeirou.www.dao;

import java.util.List;

import com.linpeirou.www.po.Hotel;
import com.linpeirou.www.util.BeanHandler;
import com.linpeirou.www.util.BeanListHandler;
import com.linpeirou.www.util.JdbcUtil;

public class HotelDaoImpl {

	public HotelDaoImpl() {
	}

	public int totalPage() {
		String sql = "select count(*) from hotel";
		return JdbcUtil.totalPage(sql);
	}

	public List<Hotel> findSomeHotel(Integer pc, Integer ps) {
		String sql = "select * from hotel limit ?,?";
		return JdbcUtil.executeQueary(sql, new BeanListHandler<Hotel>(Hotel.class), (pc - 1) * ps, ps);
	}

	public Hotel getHotel(String id) {
		String sql = "select * from hotel where id=?";
		return JdbcUtil.executeQueary(sql, new BeanHandler<Hotel>(Hotel.class), id);
	}

	public Boolean resetHotel(String id, String name, String level, Double score, String type) {
		String sql = "update hotel set name=? ,level = ? ,score=? ,type=? where id=?";
		return JdbcUtil.executeUpdate(sql, name, level, score, type, id);

	}

	public Boolean addHotel(String name, String level, Double score, String type) {
		String sql = "insert into hotel (name,level,score,type) values(?,?,?,?)";
		return JdbcUtil.executeUpdate(sql, name, level, score, type);
	}

	public Boolean delHotel(int id) {
		String sql = "delete from hotel where id=?";
		JdbcUtil.executeUpdate(sql, id);
		return true;
	}

}
