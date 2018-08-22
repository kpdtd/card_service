
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.SysUsersRolesMapper;
import com.anl.user.persistence.po.SysUsersRoles;

@Service
public class SysUsersRolesServiceImpl implements SysUsersRolesService {

	@Autowired
	SysUsersRolesMapper sysUsersRolesMapper;
	
	@Override
	public int insert(SysUsersRoles record) throws SQLException {
		return sysUsersRolesMapper.insert(record);
	}

	@Override
	public int update(SysUsersRoles record) throws SQLException {
		return sysUsersRolesMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return sysUsersRolesMapper.deleteById(id);
	}

	@Override
	public SysUsersRoles getById(Integer id) throws SQLException {
		return sysUsersRolesMapper.getById(id);
	}

	@Override
	public List<SysUsersRoles> getListByMap(Map<String, Object> condition) throws SQLException {
		return sysUsersRolesMapper.getListByMap(condition);
	}
	
	@Override
	public List<SysUsersRoles> getListByPo(SysUsersRoles record) throws SQLException {
		return sysUsersRolesMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return sysUsersRolesMapper.count(condition);
	}
	
}

