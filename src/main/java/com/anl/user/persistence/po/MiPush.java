package com.anl.user.persistence.po;

import java.util.*;

public class MiPush {
	private Integer id;
	private Integer targetType;
	private String extra;
	private String title;
	private String description;
	private String content;
	private String target;
	private String msgId;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setTargetType(Integer value) {
		this.targetType = value;
	}
	
	public Integer getTargetType() {
		return this.targetType;
	}
	public void setExtra(String value) {
		this.extra = value;
	}
	
	public String getExtra() {
		return this.extra;
	}
	public void setTitle(String value) {
		this.title = value;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setContent(String value) {
		this.content = value;
	}
	
	public String getContent() {
		return this.content;
	}
	public void setTarget(String value) {
		this.target = value;
	}
	
	public String getTarget() {
		return this.target;
	}
	public void setMsgId(String value) {
		this.msgId = value;
	}
	
	public String getMsgId() {
		return this.msgId;
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

