package com.anl.user.persistence.po;

import java.util.*;

public class ErrorRetryRecord {
	private Integer id;
	private Integer cardId;
	private String iccid;
	private String interfaceTag;
	private Integer errorPoint;
	private Integer errorType;
	private String message;
	private Integer cardState;
	private Integer times;
	private Integer result;
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
	public void setIccid(String value) {
		this.iccid = value;
	}
	
	public String getIccid() {
		return this.iccid;
	}
	public void setInterfaceTag(String value) {
		this.interfaceTag = value;
	}
	
	public String getInterfaceTag() {
		return this.interfaceTag;
	}
	public void setErrorPoint(Integer value) {
		this.errorPoint = value;
	}
	
	public Integer getErrorPoint() {
		return this.errorPoint;
	}
	public void setErrorType(Integer value) {
		this.errorType = value;
	}
	
	public Integer getErrorType() {
		return this.errorType;
	}
	public void setMessage(String value) {
		this.message = value;
	}
	
	public String getMessage() {
		return this.message;
	}
	public void setCardState(Integer value) {
		this.cardState = value;
	}
	
	public Integer getCardState() {
		return this.cardState;
	}
	public void setTimes(Integer value) {
		this.times = value;
	}
	
	public Integer getTimes() {
		return this.times;
	}
	public void setResult(Integer value) {
		this.result = value;
	}
	
	public Integer getResult() {
		return this.result;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
}

