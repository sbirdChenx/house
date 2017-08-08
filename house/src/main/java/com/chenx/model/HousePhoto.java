package com.chenx.model;

import java.io.Serializable;

public class HousePhoto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private House house;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

}
