package com.anl.user.persistence.po;

import java.util.*;

public class GoodsActivity {
	private Integer id;
	private String title;
	private String execLogic;
	private String info;
	private Integer province;
	private Integer type;
	private Integer operators;
	private String extJson;
	private Integer state;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setTitle(String value) {
		this.title = value;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setExecLogic(String value) {
		this.execLogic = value;
	}
	
	public String getExecLogic() {
		return this.execLogic;
	}
	public void setInfo(String value) {
		this.info = value;
	}
	
	public String getInfo() {
		return this.info;
	}
	public void setProvince(Integer value) {
		this.province = value;
	}
	
	public Integer getProvince() {
		return this.province;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setOperators(Integer value) {
		this.operators = value;
	}
	
	public Integer getOperators() {
		return this.operators;
	}
	public void setExtJson(String value) {
		this.extJson = value;
	}
	
	public String getExtJson() {
		return this.extJson;
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

