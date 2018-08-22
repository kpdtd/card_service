package com.anl.user.persistence.po;

import java.util.*;

public class PurchasingOrder {
	private Integer id;
	private String batchNumber;
	private Integer supplierId;
	private Integer type;
	private Integer planId;
	private Integer cost;
	
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
	public void setSupplierId(Integer value) {
		this.supplierId = value;
	}
	
	public Integer getSupplierId() {
		return this.supplierId;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setPlanId(Integer value) {
		this.planId = value;
	}
	
	public Integer getPlanId() {
		return this.planId;
	}
	public void setCost(Integer value) {
		this.cost = value;
	}
	
	public Integer getCost() {
		return this.cost;
	}
}

