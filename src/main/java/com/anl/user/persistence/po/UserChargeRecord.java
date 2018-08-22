package com.anl.user.persistence.po;

import java.util.*;

public class UserChargeRecord {
	private Integer id;
	private Integer userId;
	private Integer chargeListId;
	private String chargeListName;
	private String phone;
	private String outTradeNo;
	private String tradeNo;
	private String iccid;
	private String openId;
	private Integer payType;
	private Integer money;
	private String payer;
	private Integer state;
	private String causes;
	private String ip;
	private String aid;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setUserId(Integer value) {
		this.userId = value;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	public void setChargeListId(Integer value) {
		this.chargeListId = value;
	}
	
	public Integer getChargeListId() {
		return this.chargeListId;
	}
	public void setChargeListName(String value) {
		this.chargeListName = value;
	}
	
	public String getChargeListName() {
		return this.chargeListName;
	}
	public void setPhone(String value) {
		this.phone = value;
	}
	
	public String getPhone() {
		return this.phone;
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
	public void setIccid(String value) {
		this.iccid = value;
	}
	
	public String getIccid() {
		return this.iccid;
	}
	public void setOpenId(String value) {
		this.openId = value;
	}
	
	public String getOpenId() {
		return this.openId;
	}
	public void setPayType(Integer value) {
		this.payType = value;
	}
	
	public Integer getPayType() {
		return this.payType;
	}
	public void setMoney(Integer value) {
		this.money = value;
	}
	
	public Integer getMoney() {
		return this.money;
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
	public void setIp(String value) {
		this.ip = value;
	}
	
	public String getIp() {
		return this.ip;
	}
	public void setAid(String value) {
		this.aid = value;
	}
	
	public String getAid() {
		return this.aid;
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

