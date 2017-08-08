package com.chenx.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Pattern(regexp = "^[a-zA-Z\\d]\\w{3,11}[a-zA-Z\\d]$")
	private String username;
	@Pattern(regexp = "^(\\w){6,20}$")
	private String password;
	@Size(min = 11,max = 11)
	private String tel;
	private String realname;
	
	public User() {
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String tel, String realname) {
		this.username = username;
		this.password = password;
		this.tel = tel;
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

}
