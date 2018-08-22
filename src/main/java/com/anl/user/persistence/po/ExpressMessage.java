package com.anl.user.persistence.po;

import java.util.*;

public class ExpressMessage {
	private Integer id;
	private String expressNo;
	private String consigneeRealname;
	private String consigneeTelphone;
	private String consigneeAddress;
	private String logisticsResultLast;
	private String logisticsResult;
	private Integer collectionValue;
	private Integer collectionMoney;
	private Integer expressState;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setExpressNo(String value) {
		this.expressNo = value;
	}
	
	public String getExpressNo() {
		return this.expressNo;
	}
	public void setConsigneeRealname(String value) {
		this.consigneeRealname = value;
	}
	
	public String getConsigneeRealname() {
		return this.consigneeRealname;
	}
	public void setConsigneeTelphone(String value) {
		this.consigneeTelphone = value;
	}
	
	public String getConsigneeTelphone() {
		return this.consigneeTelphone;
	}
	public void setConsigneeAddress(String value) {
		this.consigneeAddress = value;
	}
	
	public String getConsigneeAddress() {
		return this.consigneeAddress;
	}
	public void setLogisticsResultLast(String value) {
		this.logisticsResultLast = value;
	}
	
	public String getLogisticsResultLast() {
		return this.logisticsResultLast;
	}
	public void setLogisticsResult(String value) {
		this.logisticsResult = value;
	}
	
	public String getLogisticsResult() {
		return this.logisticsResult;
	}
	public void setCollectionValue(Integer value) {
		this.collectionValue = value;
	}
	
	public Integer getCollectionValue() {
		return this.collectionValue;
	}
	public void setCollectionMoney(Integer value) {
		this.collectionMoney = value;
	}
	
	public Integer getCollectionMoney() {
		return this.collectionMoney;
	}
	public void setExpressState(Integer value) {
		this.expressState = value;
	}
	
	public Integer getExpressState() {
		return this.expressState;
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

