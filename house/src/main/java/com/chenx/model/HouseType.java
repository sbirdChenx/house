package com.chenx.model;

import java.io.Serializable;


public class HouseType implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;

	public HouseType() {
	}
	
	public HouseType(String name) {
		this.name = name;
	}

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

}
