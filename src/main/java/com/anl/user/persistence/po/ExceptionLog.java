package com.anl.user.persistence.po;

import java.util.*;

public class ExceptionLog {
	private Integer id;
	private String service;
	private String module;
	private String function;
	private Integer type;
	private String message;
	private String ip;
	private String info;
	private java.util.Date createTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setService(String value) {
		this.service = value;
	}
	
	public String getService() {
		return this.service;
	}
	public void setModule(String value) {
		this.module = value;
	}
	
	public String getModule() {
		return this.module;
	}
	public void setFunction(String value) {
		this.function = value;
	}
	
	public String getFunction() {
		return this.function;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setMessage(String value) {
		this.message = value;
	}
	
	public String getMessage() {
		return this.message;
	}
	public void setIp(String value) {
		this.ip = value;
	}
	
	public String getIp() {
		return this.ip;
	}
	public void setInfo(String value) {
		this.info = value;
	}
	
	public String getInfo() {
		return this.info;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
}

