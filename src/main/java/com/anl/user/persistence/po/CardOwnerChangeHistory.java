package com.anl.user.persistence.po;

import java.util.*;

public class CardOwnerChangeHistory {
	private Integer id;
	private Integer supplierId;
	private Integer fromOwnerId;
	private Integer toOwnerId;
	private Integer stockId;
	private String batchNumber;
	private Integer cardId;
	private Integer type;
	private java.util.Date createTime;
	
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
	public void setFromOwnerId(Integer value) {
		this.fromOwnerId = value;
	}
	
	public Integer getFromOwnerId() {
		return this.fromOwnerId;
	}
	public void setToOwnerId(Integer value) {
		this.toOwnerId = value;
	}
	
	public Integer getToOwnerId() {
		return this.toOwnerId;
	}
	public void setStockId(Integer value) {
		this.stockId = value;
	}
	
	public Integer getStockId() {
		return this.stockId;
	}
	public void setBatchNumber(String value) {
		this.batchNumber = value;
	}
	
	public String getBatchNumber() {
		return this.batchNumber;
	}
	public void setCardId(Integer value) {
		this.cardId = value;
	}
	
	public Integer getCardId() {
		return this.cardId;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
}

