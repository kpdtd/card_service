package com.anl.user.persistence.po;

import java.util.*;

public class CardOwner {
	private Integer id;
	private String company;
	private String username;
	private String password;
	private Integer ownerType;
	private Integer state;
	private String ecExtensionInfo;
	private String info;
	private String creator;
	private java.util.Date createTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setCompany(String value) {
		this.company = value;
	}
	
	public String getCompany() {
		return this.company;
	}
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return this.username;
	}
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setOwnerType(Integer value) {
		this.ownerType = value;
	}
	
	public Integer getOwnerType() {
		return this.ownerType;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}
	public void setEcExtensionInfo(String value) {
		this.ecExtensionInfo = value;
	}
	
	public String getEcExtensionInfo() {
		return this.ecExtensionInfo;
	}
	public void setInfo(String value) {
		this.info = value;
	}
	
	public String getInfo() {
		return this.info;
	}
	public void setCreator(String value) {
		this.creator = value;
	}
	
	public String getCreator() {
		return this.creator;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
}

