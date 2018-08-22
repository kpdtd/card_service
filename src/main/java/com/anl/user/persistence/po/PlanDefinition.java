package com.anl.user.persistence.po;

import java.util.*;

public class PlanDefinition {
	private Integer id;
	private String code;
	private String name;
	private String subName;
	private Integer monthlyPlanPrice;
	private Integer flowUnit;
	private Integer flowUnitPrice;
	private Integer type;
	private Integer state;
	private String info;
	private Integer effectiveTime;
	private Integer displaySort;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	
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
	public void setSubName(String value) {
		this.subName = value;
	}
	
	public String getSubName() {
		return this.subName;
	}
	public void setMonthlyPlanPrice(Integer value) {
		this.monthlyPlanPrice = value;
	}
	
	public Integer getMonthlyPlanPrice() {
		return this.monthlyPlanPrice;
	}
	public void setFlowUnit(Integer value) {
		this.flowUnit = value;
	}
	
	public Integer getFlowUnit() {
		return this.flowUnit;
	}
	public void setFlowUnitPrice(Integer value) {
		this.flowUnitPrice = value;
	}
	
	public Integer getFlowUnitPrice() {
		return this.flowUnitPrice;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}
	public void setInfo(String value) {
		this.info = value;
	}
	
	public String getInfo() {
		return this.info;
	}
	public void setEffectiveTime(Integer value) {
		this.effectiveTime = value;
	}
	
	public Integer getEffectiveTime() {
		return this.effectiveTime;
	}
	public void setDisplaySort(Integer value) {
		this.displaySort = value;
	}
	
	public Integer getDisplaySort() {
		return this.displaySort;
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

