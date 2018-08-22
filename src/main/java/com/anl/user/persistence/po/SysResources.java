package com.anl.user.persistence.po;

import java.util.*;

public class SysResources {
	private Integer resourceId;
	private String resourceName;
	private String resourceDesc;
	private String resourceType;
	private String resourceString;
	private Integer priority;
	private String module;
	private Integer parentId;
	
	public void setResourceId(Integer value) {
		this.resourceId = value;
	}
	
	public Integer getResourceId() {
		return this.resourceId;
	}
	public void setResourceName(String value) {
		this.resourceName = value;
	}
	
	public String getResourceName() {
		return this.resourceName;
	}
	public void setResourceDesc(String value) {
		this.resourceDesc = value;
	}
	
	public String getResourceDesc() {
		return this.resourceDesc;
	}
	public void setResourceType(String value) {
		this.resourceType = value;
	}
	
	public String getResourceType() {
		return this.resourceType;
	}
	public void setResourceString(String value) {
		this.resourceString = value;
	}
	
	public String getResourceString() {
		return this.resourceString;
	}
	public void setPriority(Integer value) {
		this.priority = value;
	}
	
	public Integer getPriority() {
		return this.priority;
	}
	public void setModule(String value) {
		this.module = value;
	}
	
	public String getModule() {
		return this.module;
	}
	public void setParentId(Integer value) {
		this.parentId = value;
	}
	
	public Integer getParentId() {
		return this.parentId;
	}
}

