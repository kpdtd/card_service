package com.anl.user.persistence.po;

import java.util.*;

public class UserFlowUsedMonth {
	private Integer id;
	private String batchNumber;
	private Integer suplierId;
	private Integer cardId;
	private String iccid;
	private String phone;
	private Integer total;
	private Integer actualUse;
	private Integer cardState;
	private java.util.Date billDate;
	private java.util.Date createTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setBatchNumber(String value) {
		this.batchNumber = value;
	}
	
	public String getBatchNumber() {
		return this.batchNumber;
	}
	public void setSuplierId(Integer value) {
		this.suplierId = value;
	}
	
	public Integer getSuplierId() {
		return this.suplierId;
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
	public void setPhone(String value) {
		this.phone = value;
	}
	
	public String getPhone() {
		return this.phone;
	}
	public void setTotal(Integer value) {
		this.total = value;
	}
	
	public Integer getTotal() {
		return this.total;
	}
	public void setActualUse(Integer value) {
		this.actualUse = value;
	}
	
	public Integer getActualUse() {
		return this.actualUse;
	}
	public void setCardState(Integer value) {
		this.cardState = value;
	}
	
	public Integer getCardState() {
		return this.cardState;
	}
	public void setBillDate(java.util.Date value) {
		this.billDate = value;
	}
	
	public java.util.Date getBillDate() {
		return this.billDate;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
}

