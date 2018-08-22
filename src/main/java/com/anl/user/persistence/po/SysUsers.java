package com.anl.user.persistence.po;

import java.util.*;

public class SysUsers {
	private Integer usersId;
	private String userAccount;
	private String userName;
	private String userPassword;
	private String userDesc;
	private Integer enabled;
	private Integer isDelete;
	private java.util.Date createTime;
	private java.util.Date loginTime;
	
	public void setUsersId(Integer value) {
		this.usersId = value;
	}
	
	public Integer getUsersId() {
		return this.usersId;
	}
	public void setUserAccount(String value) {
		this.userAccount = value;
	}
	
	public String getUserAccount() {
		return this.userAccount;
	}
	public void setUserName(String value) {
		this.userName = value;
	}
	
	public String getUserName() {
		return this.userName;
	}
	public void setUserPassword(String value) {
		this.userPassword = value;
	}
	
	public String getUserPassword() {
		return this.userPassword;
	}
	public void setUserDesc(String value) {
		this.userDesc = value;
	}
	
	public String getUserDesc() {
		return this.userDesc;
	}
	public void setEnabled(Integer value) {
		this.enabled = value;
	}
	
	public Integer getEnabled() {
		return this.enabled;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setLoginTime(java.util.Date value) {
		this.loginTime = value;
	}
	
	public java.util.Date getLoginTime() {
		return this.loginTime;
	}
}

