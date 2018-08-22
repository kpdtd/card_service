package com.anl.user.persistence.po;

import java.util.*;

public class SupplierPool {
	private Integer id;
	private Integer supplierId;
	private String poolName;
	private Integer poolValue;
	private String poolCode;
	private Integer cardNumber;
	private Integer poolUsed;
	private Integer threshold;
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
	public void setPoolName(String value) {
		this.poolName = value;
	}
	
	public String getPoolName() {
		return this.poolName;
	}
	public void setPoolValue(Integer value) {
		this.poolValue = value;
	}
	
	public Integer getPoolValue() {
		return this.poolValue;
	}
	public void setPoolCode(String value) {
		this.poolCode = value;
	}
	
	public String getPoolCode() {
		return this.poolCode;
	}
	public void setCardNumber(Integer value) {
		this.cardNumber = value;
	}
	
	public Integer getCardNumber() {
		return this.cardNumber;
	}
	public void setPoolUsed(Integer value) {
		this.poolUsed = value;
	}
	
	public Integer getPoolUsed() {
		return this.poolUsed;
	}
	public void setThreshold(Integer value) {
		this.threshold = value;
	}
	
	public Integer getThreshold() {
		return this.threshold;
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

