package com.anl.user.persistence.po;

import java.util.*;

public class ValidationCode {
	private Integer id;
	private String userPhone;
	private String validationCode;
	private java.util.Date createTime;
	private String sendCode;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setUserPhone(String value) {
		this.userPhone = value;
	}
	
	public String getUserPhone() {
		return this.userPhone;
	}
	public void setValidationCode(String value) {
		this.validationCode = value;
	}
	
	public String getValidationCode() {
		return this.validationCode;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setSendCode(String value) {
		this.sendCode = value;
	}
	
	public String getSendCode() {
		return this.sendCode;
	}
}

