package com.linpeirou.www.service;

import java.util.Map;

import com.linpeirou.www.dao.PageBean;
import com.linpeirou.www.dao.RoomDaoImpl;
import com.linpeirou.www.po.Room;
import com.linpeirou.www.util.ServiceUtil;

public class ServiceForRoom {
	private RoomDaoImpl room = new RoomDaoImpl();

	public Boolean delRoom(int roomId) {
		return room.delRoom(roomId);

	}

	public Boolean resetRoom(Map<String, Object> map, String newFileName) {
		String id = (String) map.get("id");
		String type = (String) map.get("type");
		String area = (String) map.get("area");
		String bedWidth = (String) map.get("bedWidth");
		String price = (String) map.get("price");
		String picture = newFileName;
		return room.resetRoom(id, type, area, bedWidth, price, picture);

	}

	public Boolean addRoom(Map<String, String> map, String newFileName) {
		return room.addRoom(map, newFileName);

	}

}
