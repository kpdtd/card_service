package com.anl.user.persistence.po;

import java.util.*;

public class UserPlan {
	private Integer id;
	private Integer userId;
	private Integer planId;
	private String planName;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private Integer newPlanId;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	
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
	public void setPlanId(Integer value) {
		this.planId = value;
	}
	
	public Integer getPlanId() {
		return this.planId;
	}
	public void setPlanName(String value) {
		this.planName = value;
	}
	
	public String getPlanName() {
		return this.planName;
	}
	public void setStartTime(java.util.Date value) {
		this.startTime = value;
	}
	
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}
	
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	public void setNewPlanId(Integer value) {
		this.newPlanId = value;
	}
	
	public Integer getNewPlanId() {
		return this.newPlanId;
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

