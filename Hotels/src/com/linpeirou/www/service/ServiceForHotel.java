package com.linpeirou.www.service;

import com.linpeirou.www.dao.HotelDaoImpl;
import com.linpeirou.www.dao.PageBean;
import com.linpeirou.www.po.Hotel;

public class ServiceForHotel {
	private HotelDaoImpl hotel = new HotelDaoImpl();
	
	public  PageBean<Hotel> findSomeHotel(int pc, int ps) {
		PageBean<Hotel> pb = new PageBean<Hotel>();
		pb.setPageCode(pc);
		pb.setPageSize(ps);
		pb.setTotalRecord(hotel.totalPage());
		pb.setBeanList(hotel.findSomeHotel(pc, ps));
		double tp = 1.0*pb.getTotalRecord()/pb.getPageSize();
		pb.setTotalPage((int) Math.ceil(tp));
		return pb;
	}

	public Boolean deletHotel(int id) {
		return hotel.delHotel(id);
	}

	public Boolean resetHotel(String id, String name, String level, Double score, String type) {
			if( hotel.getHotel(id) != null) {
				hotel.resetHotel(id,name, level,score,type);
				return true;
			}else {
				throw new RuntimeException("酒店不存在");
			}
	}

	public boolean addHotel(String name, String level, Double score, String type) {
		hotel.addHotel(name,level,score,type);
		return true;
	}

	
}
