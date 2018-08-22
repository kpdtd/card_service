
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.SysRolesMapper;
import com.anl.user.persistence.po.SysRoles;

@Service
public class SysRolesServiceImpl implements SysRolesService {

	@Autowired
	SysRolesMapper sysRolesMapper;
	
	@Override
	public int insert(SysRoles record) throws SQLException {
		return sysRolesMapper.insert(record);
	}

	@Override
	public int update(SysRoles record) throws SQLException {
		return sysRolesMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return sysRolesMapper.deleteById(id);
	}

	@Override
	public SysRoles getById(Integer id) throws SQLException {
		return sysRolesMapper.getById(id);
	}

	@Override
	public List<SysRoles> getListByMap(Map<String, Object> condition) throws SQLException {
		return sysRolesMapper.getListByMap(condition);
	}
	
	@Override
	public List<SysRoles> getListByPo(SysRoles record) throws SQLException {
		return sysRolesMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return sysRolesMapper.count(condition);
	}
	
}

