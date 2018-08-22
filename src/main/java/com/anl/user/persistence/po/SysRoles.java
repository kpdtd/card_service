package com.anl.user.persistence.po;

import java.util.*;

public class SysRoles {
	private Integer rolesId;
	private String roleName;
	private String roleDesc;
	private String module;
	private Integer parentId;
	
	public void setRolesId(Integer value) {
		this.rolesId = value;
	}
	
	public Integer getRolesId() {
		return this.rolesId;
	}
	public void setRoleName(String value) {
		this.roleName = value;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	public void setRoleDesc(String value) {
		this.roleDesc = value;
	}
	
	public String getRoleDesc() {
		return this.roleDesc;
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

