package com.anl.user.persistence.po;

import java.util.*;

public class TaskBatchRecord {
	private Integer id;
	private Integer taskRecordId;
	private String cardIccid;
	private Integer result;
	private Integer execCount;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setTaskRecordId(Integer value) {
		this.taskRecordId = value;
	}
	
	public Integer getTaskRecordId() {
		return this.taskRecordId;
	}
	public void setCardIccid(String value) {
		this.cardIccid = value;
	}
	
	public String getCardIccid() {
		return this.cardIccid;
	}
	public void setResult(Integer value) {
		this.result = value;
	}
	
	public Integer getResult() {
		return this.result;
	}
	public void setExecCount(Integer value) {
		this.execCount = value;
	}
	
	public Integer getExecCount() {
		return this.execCount;
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

