package com.anl.user.persistence.po;

import java.util.*;

public class UserAccount {
	private Integer userId;
	private Integer primaryAccount;
	private Integer subAccount;
	private Integer credit;
	private Integer membership;
	private Date createTime;
	private Date updateTime;
	
	public void setUserId(Integer value) {
		this.userId = value;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	public void setPrimaryAccount(Integer value) {
		this.primaryAccount = value;
	}
	
	public Integer getPrimaryAccount() {
		return this.primaryAccount;
	}
	public void setSubAccount(Integer value) {
		this.subAccount = value;
	}
	
	public Integer getSubAccount() {
		return this.subAccount;
	}
	public void setCredit(Integer value) {
		this.credit = value;
	}
	
	public Integer getCredit() {
		return this.credit;
	}
	public void setMembership(Integer value) {
		this.membership = value;
	}
	
	public Integer getMembership() {
		return this.membership;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
}

