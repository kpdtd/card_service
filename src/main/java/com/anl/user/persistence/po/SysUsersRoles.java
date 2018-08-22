package com.anl.user.persistence.po;

import java.util.*;

public class SysUsersRoles {
	private Integer usersRolesId;
	private Integer usersId;
	private Integer rolesId;
	
	public void setUsersRolesId(Integer value) {
		this.usersRolesId = value;
	}
	
	public Integer getUsersRolesId() {
		return this.usersRolesId;
	}
	public void setUsersId(Integer value) {
		this.usersId = value;
	}
	
	public Integer getUsersId() {
		return this.usersId;
	}
	public void setRolesId(Integer value) {
		this.rolesId = value;
	}
	
	public Integer getRolesId() {
		return this.rolesId;
	}
}

