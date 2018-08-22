package com.anl.user.persistence.po;

import java.util.*;

public class AutoTaskExecHistory {
	private Integer id;
	private Integer taskId;
	private String taskName;
	private String category;
	private Integer itemTotal;
	private Integer itemSuccess;
	private Integer returnResult;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private Integer timeConsuming;
	private java.util.Date createTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setTaskId(Integer value) {
		this.taskId = value;
	}
	
	public Integer getTaskId() {
		return this.taskId;
	}
	public void setTaskName(String value) {
		this.taskName = value;
	}
	
	public String getTaskName() {
		return this.taskName;
	}
	public void setCategory(String value) {
		this.category = value;
	}
	
	public String getCategory() {
		return this.category;
	}
	public void setItemTotal(Integer value) {
		this.itemTotal = value;
	}
	
	public Integer getItemTotal() {
		return this.itemTotal;
	}
	public void setItemSuccess(Integer value) {
		this.itemSuccess = value;
	}
	
	public Integer getItemSuccess() {
		return this.itemSuccess;
	}
	public void setReturnResult(Integer value) {
		this.returnResult = value;
	}
	
	public Integer getReturnResult() {
		return this.returnResult;
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
	public void setTimeConsuming(Integer value) {
		this.timeConsuming = value;
	}
	
	public Integer getTimeConsuming() {
		return this.timeConsuming;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
}

