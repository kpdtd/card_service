package com.anl.user.persistence.po;

import java.util.*;

public class WxAccessToken {
	private Integer id;
	private String appId;
	private String accessToken;
	private Date createTime;
	private Date updateTime;
	private Date expireTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setAppId(String value) {
		this.appId = value;
	}
	
	public String getAppId() {
		return this.appId;
	}
	public void setAccessToken(String value) {
		this.accessToken = value;
	}
	
	public String getAccessToken() {
		return this.accessToken;
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
	public void setExpireTime(Date value) {
		this.expireTime = value;
	}
	
	public Date getExpireTime() {
		return this.expireTime;
	}
}

