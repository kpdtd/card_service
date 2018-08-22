
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.SysConfigMapper;
import com.anl.user.persistence.po.SysConfig;

@Service
public class SysConfigServiceImpl implements SysConfigService {

	@Autowired
	SysConfigMapper sysConfigMapper;
	
	@Override
	public int insert(SysConfig record) throws SQLException {
		return sysConfigMapper.insert(record);
	}

	@Override
	public int update(SysConfig record) throws SQLException {
		return sysConfigMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return sysConfigMapper.deleteById(id);
	}

	@Override
	public SysConfig getById(Integer id) throws SQLException {
		return sysConfigMapper.getById(id);
	}

	@Override
	public List<SysConfig> getListByMap(Map<String, Object> condition) throws SQLException {
		return sysConfigMapper.getListByMap(condition);
	}
	
	@Override
	public List<SysConfig> getListByPo(SysConfig record) throws SQLException {
		return sysConfigMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return sysConfigMapper.count(condition);
	}
	
}

