package com.chenx.model;

import java.io.Serializable;

/**
 * Created by ChenX on 2017/8/8.
 */
public class SearchDTO implements Serializable {
	private String title;
	private String price;
	private int lowPrice;
	private int hightPrice;
	private int districtId;
	private int houseTypeId;
	private String area;
	private int lowArea;
	private int hightArea;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(int lowPrice) {
		this.lowPrice = lowPrice;
	}

	public int getHightPrice() {
		return hightPrice;
	}

	public void setHightPrice(int hightPrice) {
		this.hightPrice = hightPrice;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public int getHouseTypeId() {
		return houseTypeId;
	}

	public void setHouseTypeId(int houseTypeId) {
		this.houseTypeId = houseTypeId;
	}

	public int getLowArea() {
		return lowArea;
	}

	public void setLowArea(int lowArea) {
		this.lowArea = lowArea;
	}

	public int getHightArea() {
		return hightArea;
	}

	public void setHightArea(int hightArea) {
		this.hightArea = hightArea;
	}
}
