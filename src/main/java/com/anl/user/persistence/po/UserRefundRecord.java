package com.anl.user.persistence.po;

import java.util.*;

public class UserRefundRecord {
	private Integer id;
	private Integer cardId;
	private String payType;
	private Integer goodsId;
	private Integer money;
	private String outTradeNo;
	private String tradeNo;
	private String payer;
	private Integer state;
	private String causes;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	
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
	public void setPayType(String value) {
		this.payType = value;
	}
	
	public String getPayType() {
		return this.payType;
	}
	public void setGoodsId(Integer value) {
		this.goodsId = value;
	}
	
	public Integer getGoodsId() {
		return this.goodsId;
	}
	public void setMoney(Integer value) {
		this.money = value;
	}
	
	public Integer getMoney() {
		return this.money;
	}
	public void setOutTradeNo(String value) {
		this.outTradeNo = value;
	}
	
	public String getOutTradeNo() {
		return this.outTradeNo;
	}
	public void setTradeNo(String value) {
		this.tradeNo = value;
	}
	
	public String getTradeNo() {
		return this.tradeNo;
	}
	public void setPayer(String value) {
		this.payer = value;
	}
	
	public String getPayer() {
		return this.payer;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}
	public void setCauses(String value) {
		this.causes = value;
	}
	
	public String getCauses() {
		return this.causes;
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

