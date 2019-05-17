package com.linpeirou.www.dao;

import java.util.List;
import java.util.Map;

import com.linpeirou.www.po.Room;
import com.linpeirou.www.util.BeanListHandler;
import com.linpeirou.www.util.JdbcUtil;

public class RoomDaoImpl {

	public  RoomDaoImpl() {
	}
	
	public int totalPage(String hotelId) {
		String sql = "select count(*) from room where hotelId=?";
		return JdbcUtil.totalPage(sql, hotelId);
	}

	public List<Room> findSomeRoom(String hotelId, Integer pc, Integer ps) {
		String sql = "select * from room where hotelId=? limit ?,? ";
		List<Room> rooms = null;
		rooms = JdbcUtil.executeQueary(sql, new BeanListHandler<Room>(Room.class), hotelId, (pc - 1) * ps, ps);
		return rooms;
	}

	public Boolean delRoom(int roomId) {
		String sql = "delete from room where id=?";
		JdbcUtil.executeUpdate(sql, roomId);
		return true;
	}

	public Boolean resetRoom(String id, String type, String area, String bedWidth, String price, String picture) {
		String sql = "update room set type = ?, area = ?, bedWidth = ?,price = ?,picture = ? where id=?";
		JdbcUtil.executeUpdate(sql, type, area, bedWidth, price, picture, id);
		return true;
	}

	public Boolean addRoom(Map<String, String> map, String newFileName) {
		String sql = "insert into room (type, area, bedWidth, price, picture, hotelId) values(?,?,?,?,?,?)";
		JdbcUtil.executeUpdate(sql, map.get("type"), map.get("area"), map.get("bedWidth"), map.get("price"), newFileName , map.get("hotelId"));
		return true;
	}
}
