package com.linpeirou.www.po;

public class Room {
	private Integer id;
	private String type;
	private Integer area;
	private Integer bedWidth ;
	private Integer price;
	private Integer hotelId ;
	private String picture;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getBedWidth() {
		return bedWidth;
	}
	public void setBedWidth(Integer bedWidth) {
		this.bedWidth = bedWidth;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	

}
