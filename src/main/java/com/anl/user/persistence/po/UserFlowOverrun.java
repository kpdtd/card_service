package com.anl.user.persistence.po;

import java.util.*;

public class UserFlowOverrun {
	private Integer id;
	private Integer cardId;
	private Integer flow;
	private Integer balance;
	private java.util.Date billDate;
	private Integer state;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setCardId(Integer value) {
		this.cardId = value;
	}
	
	public Integer getCardId() {
		return this.cardId;
	}
	public void setFlow(Integer value) {
		this.flow = value;
	}
	
	public Integer getFlow() {
		return this.flow;
	}
	public void setBalance(Integer value) {
		this.balance = value;
	}
	
	public Integer getBalance() {
		return this.balance;
	}
	public void setBillDate(java.util.Date value) {
		this.billDate = value;
	}
	
	public java.util.Date getBillDate() {
		return this.billDate;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
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

