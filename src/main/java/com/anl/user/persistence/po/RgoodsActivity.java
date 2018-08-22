package com.anl.user.persistence.po;

import java.util.*;

public class RgoodsActivity {
	private Integer id;
	private Integer goodsId;
	private Integer activityId;
	private java.util.Date createTime;
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setGoodsId(Integer value) {
		this.goodsId = value;
	}
	
	public Integer getGoodsId() {
		return this.goodsId;
	}
	public void setActivityId(Integer value) {
		this.activityId = value;
	}
	
	public Integer getActivityId() {
		return this.activityId;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
}

