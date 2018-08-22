
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.SysRolesResMapper;
import com.anl.user.persistence.po.SysRolesRes;

@Service
public class SysRolesResServiceImpl implements SysRolesResService {

	@Autowired
	SysRolesResMapper sysRolesResMapper;
	
	@Override
	public int insert(SysRolesRes record) throws SQLException {
		return sysRolesResMapper.insert(record);
	}

	@Override
	public int update(SysRolesRes record) throws SQLException {
		return sysRolesResMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return sysRolesResMapper.deleteById(id);
	}

	@Override
	public SysRolesRes getById(Integer id) throws SQLException {
		return sysRolesResMapper.getById(id);
	}

	@Override
	public List<SysRolesRes> getListByMap(Map<String, Object> condition) throws SQLException {
		return sysRolesResMapper.getListByMap(condition);
	}
	
	@Override
	public List<SysRolesRes> getListByPo(SysRolesRes record) throws SQLException {
		return sysRolesResMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return sysRolesResMapper.count(condition);
	}
	
}

