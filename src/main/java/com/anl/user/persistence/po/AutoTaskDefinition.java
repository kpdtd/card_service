package com.anl.user.persistence.po;

import java.util.*;

public class AutoTaskDefinition {
	private Integer id;
	private Integer supplierId;
	private String taskName;
	private String taskDesc;
	private String taskExecLogic;
	private String taskTimeCron;
	private Integer noticeType;
	private String noticePerson;
	private Integer executeState;
	private Integer state;
	private Integer timeline;
	private String hostList;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setSupplierId(Integer value) {
		this.supplierId = value;
	}
	
	public Integer getSupplierId() {
		return this.supplierId;
	}
	public void setTaskName(String value) {
		this.taskName = value;
	}
	
	public String getTaskName() {
		return this.taskName;
	}
	public void setTaskDesc(String value) {
		this.taskDesc = value;
	}
	
	public String getTaskDesc() {
		return this.taskDesc;
	}
	public void setTaskExecLogic(String value) {
		this.taskExecLogic = value;
	}
	
	public String getTaskExecLogic() {
		return this.taskExecLogic;
	}
	public void setTaskTimeCron(String value) {
		this.taskTimeCron = value;
	}
	
	public String getTaskTimeCron() {
		return this.taskTimeCron;
	}
	public void setNoticeType(Integer value) {
		this.noticeType = value;
	}
	
	public Integer getNoticeType() {
		return this.noticeType;
	}
	public void setNoticePerson(String value) {
		this.noticePerson = value;
	}
	
	public String getNoticePerson() {
		return this.noticePerson;
	}
	public void setExecuteState(Integer value) {
		this.executeState = value;
	}
	
	public Integer getExecuteState() {
		return this.executeState;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}
	public void setTimeline(Integer value) {
		this.timeline = value;
	}
	
	public Integer getTimeline() {
		return this.timeline;
	}
	public void setHostList(String value) {
		this.hostList = value;
	}
	
	public String getHostList() {
		return this.hostList;
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

