package com.anl.user.persistence.po;

import java.util.*;

public class FlowPacketDefinition {
	private Integer id;
	private String code;
	private String name;
	private Integer flowNumber;
	private Integer price;
	private Integer displaySort;
	private Integer useSort;
	private Integer startTime;
	private Integer validDays;
	private String tips;
	private String deatil;
	private Integer state;
	private String creator;
	private java.util.Date createTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return this.code;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setFlowNumber(Integer value) {
		this.flowNumber = value;
	}
	
	public Integer getFlowNumber() {
		return this.flowNumber;
	}
	public void setPrice(Integer value) {
		this.price = value;
	}
	
	public Integer getPrice() {
		return this.price;
	}
	public void setDisplaySort(Integer value) {
		this.displaySort = value;
	}
	
	public Integer getDisplaySort() {
		return this.displaySort;
	}
	public void setUseSort(Integer value) {
		this.useSort = value;
	}
	
	public Integer getUseSort() {
		return this.useSort;
	}
	public void setStartTime(Integer value) {
		this.startTime = value;
	}
	
	public Integer getStartTime() {
		return this.startTime;
	}
	public void setValidDays(Integer value) {
		this.validDays = value;
	}
	
	public Integer getValidDays() {
		return this.validDays;
	}
	public void setTips(String value) {
		this.tips = value;
	}
	
	public String getTips() {
		return this.tips;
	}
	public void setDeatil(String value) {
		this.deatil = value;
	}
	
	public String getDeatil() {
		return this.deatil;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}
	public void setCreator(String value) {
		this.creator = value;
	}
	
	public String getCreator() {
		return this.creator;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
}

