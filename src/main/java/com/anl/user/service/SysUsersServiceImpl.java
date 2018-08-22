
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.SysUsersMapper;
import com.anl.user.persistence.po.SysUsers;

@Service
public class SysUsersServiceImpl implements SysUsersService {

	@Autowired
	SysUsersMapper sysUsersMapper;
	
	@Override
	public int insert(SysUsers record) throws SQLException {
		return sysUsersMapper.insert(record);
	}

	@Override
	public int update(SysUsers record) throws SQLException {
		return sysUsersMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return sysUsersMapper.deleteById(id);
	}

	@Override
	public SysUsers getById(Integer id) throws SQLException {
		return sysUsersMapper.getById(id);
	}

	@Override
	public List<SysUsers> getListByMap(Map<String, Object> condition) throws SQLException {
		return sysUsersMapper.getListByMap(condition);
	}
	
	@Override
	public List<SysUsers> getListByPo(SysUsers record) throws SQLException {
		return sysUsersMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return sysUsersMapper.count(condition);
	}
	
}

