package com.chenx.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Contacter implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private String contacterName;
	@Size(min = 11,max = 11)
	private String contacterTel;

	private String contacterQQ;
	@Email
	private String contacterEmail;

	public String getContacterName() {
		return contacterName;
	}

	public void setContacterName(String contacterName) {
		this.contacterName = contacterName;
	}

	public String getContacterTel() {
		return contacterTel;
	}

	public void setContacterTel(String contacterTel) {
		this.contacterTel = contacterTel;
	}

	public String getContacterQQ() {
		return contacterQQ;
	}

	public void setContacterQQ(String contacterQQ) {
		this.contacterQQ = contacterQQ;
	}

	public String getContacterEmail() {
		return contacterEmail;
	}

	public void setContacterEmail(String contacterEmail) {
		this.contacterEmail = contacterEmail;
	}

}
