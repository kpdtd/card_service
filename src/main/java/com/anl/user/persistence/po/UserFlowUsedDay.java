package com.anl.user.persistence.po;

import java.util.*;

public class UserFlowUsedDay {
	private Integer id;
	private Integer cardId;
	private Integer flow;
	private java.util.Date recordTime;
	private java.util.Date createTime;
	
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
	public void setRecordTime(java.util.Date value) {
		this.recordTime = value;
	}
	
	public java.util.Date getRecordTime() {
		return this.recordTime;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
}

