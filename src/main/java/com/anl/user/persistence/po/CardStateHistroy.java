package com.anl.user.persistence.po;

import java.util.*;

public class CardStateHistroy {
	private Integer id;
	private Integer cardId;
	private Integer originalState;
	private Integer state;
	private String triggerPoint;
	private String detail;
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
	public void setOriginalState(Integer value) {
		this.originalState = value;
	}
	
	public Integer getOriginalState() {
		return this.originalState;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}
	public void setTriggerPoint(String value) {
		this.triggerPoint = value;
	}
	
	public String getTriggerPoint() {
		return this.triggerPoint;
	}
	public void setDetail(String value) {
		this.detail = value;
	}
	
	public String getDetail() {
		return this.detail;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
}

