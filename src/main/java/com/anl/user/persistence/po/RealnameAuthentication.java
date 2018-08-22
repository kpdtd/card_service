package com.anl.user.persistence.po;

import java.util.*;

public class RealnameAuthentication {
	private Integer id;
	private String phone;
	private String name;
	private String identificationNumber;
	private Integer cardId;
	private String msisdn;
	private String imsi;
	private String iccid;
	private String attachment;
	private Integer state;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setPhone(String value) {
		this.phone = value;
	}
	
	public String getPhone() {
		return this.phone;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setIdentificationNumber(String value) {
		this.identificationNumber = value;
	}
	
	public String getIdentificationNumber() {
		return this.identificationNumber;
	}
	public void setCardId(Integer value) {
		this.cardId = value;
	}
	
	public Integer getCardId() {
		return this.cardId;
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
	public void setIccid(String value) {
		this.iccid = value;
	}
	
	public String getIccid() {
		return this.iccid;
	}
	public void setAttachment(String value) {
		this.attachment = value;
	}
	
	public String getAttachment() {
		return this.attachment;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
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

