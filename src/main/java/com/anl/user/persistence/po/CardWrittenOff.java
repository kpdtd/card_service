package com.anl.user.persistence.po;

import java.util.*;

public class CardWrittenOff {
	private Integer id;
	private Integer supplierId;
	private Integer cardOwnerId;
	private String iccid;
	private String msisdn;
	private String imsi;
	private String bindDevice;
	private Integer cardState;
	private Integer gprsState;
	private Integer opState;
	private Integer poolId;
	private Integer operator;
	private String apn;
	private java.util.Date activationTime;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private String reason;
	private java.util.Date writtenOffTime;
	
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
	public void setCardOwnerId(Integer value) {
		this.cardOwnerId = value;
	}
	
	public Integer getCardOwnerId() {
		return this.cardOwnerId;
	}
	public void setIccid(String value) {
		this.iccid = value;
	}
	
	public String getIccid() {
		return this.iccid;
	}
	public void setMsisdn(String value) {
		this.msisdn = value;
	}
	
	public String getMsisdn() {
		return this.msisdn;
	}
	public void setImsi(String value) {
		this.imsi = value;
	}
	
	public String getImsi() {
		return this.imsi;
	}
	public void setBindDevice(String value) {
		this.bindDevice = value;
	}
	
	public String getBindDevice() {
		return this.bindDevice;
	}
	public void setCardState(Integer value) {
		this.cardState = value;
	}
	
	public Integer getCardState() {
		return this.cardState;
	}
	public void setGprsState(Integer value) {
		this.gprsState = value;
	}
	
	public Integer getGprsState() {
		return this.gprsState;
	}
	public void setOpState(Integer value) {
		this.opState = value;
	}
	
	public Integer getOpState() {
		return this.opState;
	}
	public void setPoolId(Integer value) {
		this.poolId = value;
	}
	
	public Integer getPoolId() {
		return this.poolId;
	}
	public void setOperator(Integer value) {
		this.operator = value;
	}
	
	public Integer getOperator() {
		return this.operator;
	}
	public void setApn(String value) {
		this.apn = value;
	}
	
	public String getApn() {
		return this.apn;
	}
	public void setActivationTime(java.util.Date value) {
		this.activationTime = value;
	}
	
	public java.util.Date getActivationTime() {
		return this.activationTime;
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
	public void setReason(String value) {
		this.reason = value;
	}
	
	public String getReason() {
		return this.reason;
	}
	public void setWrittenOffTime(java.util.Date value) {
		this.writtenOffTime = value;
	}
	
	public java.util.Date getWrittenOffTime() {
		return this.writtenOffTime;
	}
}

