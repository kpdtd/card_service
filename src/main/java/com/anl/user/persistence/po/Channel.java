package com.anl.user.persistence.po;

import java.util.*;

public class Channel {
	private Integer id;
	private String name;
	private String code;
	private String adUrl;
	private Integer category;
	private Integer state;
	private String linker;
	private String info;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return this.code;
	}
	public void setAdUrl(String value) {
		this.adUrl = value;
	}
	
	public String getAdUrl() {
		return this.adUrl;
	}
	public void setCategory(Integer value) {
		this.category = value;
	}
	
	public Integer getCategory() {
		return this.category;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}
	public void setLinker(String value) {
		this.linker = value;
	}
	
	public String getLinker() {
		return this.linker;
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
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
}

