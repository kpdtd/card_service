package com.anl.user.persistence.po;

import java.util.*;

public class SysConfig {
	private Integer id;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private String name;
	private String value;
	private java.util.Date begintime;
	private java.util.Date endtime;
	private Integer state;
	private String memo;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
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
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	public void setBegintime(java.util.Date value) {
		this.begintime = value;
	}
	
	public java.util.Date getBegintime() {
		return this.begintime;
	}
	public void setEndtime(java.util.Date value) {
		this.endtime = value;
	}
	
	public java.util.Date getEndtime() {
		return this.endtime;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}
	public void setMemo(String value) {
		this.memo = value;
	}
	
	public String getMemo() {
		return this.memo;
	}
}

