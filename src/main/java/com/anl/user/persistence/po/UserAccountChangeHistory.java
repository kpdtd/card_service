package com.anl.user.persistence.po;

import java.util.*;

public class UserAccountChangeHistory {
	private Integer id;
	private Integer userId;
	private Integer type;
	private Integer money;
	private Integer paChangeBefore;
	private Integer paChangeAfter;
	private Integer saChangeBefore;
	private Integer saChangeAfter;
	private Integer creditChangeBefore;
	private Integer creditChangeAfter;
	private String source;
	private java.util.Date createTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setUserId(Integer value) {
		this.userId = value;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setMoney(Integer value) {
		this.money = value;
	}
	
	public Integer getMoney() {
		return this.money;
	}
	public void setPaChangeBefore(Integer value) {
		this.paChangeBefore = value;
	}
	
	public Integer getPaChangeBefore() {
		return this.paChangeBefore;
	}
	public void setPaChangeAfter(Integer value) {
		this.paChangeAfter = value;
	}
	
	public Integer getPaChangeAfter() {
		return this.paChangeAfter;
	}
	public void setSaChangeBefore(Integer value) {
		this.saChangeBefore = value;
	}
	
	public Integer getSaChangeBefore() {
		return this.saChangeBefore;
	}
	public void setSaChangeAfter(Integer value) {
		this.saChangeAfter = value;
	}
	
	public Integer getSaChangeAfter() {
		return this.saChangeAfter;
	}
	public void setCreditChangeBefore(Integer value) {
		this.creditChangeBefore = value;
	}
	
	public Integer getCreditChangeBefore() {
		return this.creditChangeBefore;
	}
	public void setCreditChangeAfter(Integer value) {
		this.creditChangeAfter = value;
	}
	
	public Integer getCreditChangeAfter() {
		return this.creditChangeAfter;
	}
	public void setSource(String value) {
		this.source = value;
	}
	
	public String getSource() {
		return this.source;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
}

