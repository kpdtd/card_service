
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.AutoTaskDefinitionMapper;
import com.anl.user.persistence.po.AutoTaskDefinition;

@Service
public class AutoTaskDefinitionServiceImpl implements AutoTaskDefinitionService {

	@Autowired
	AutoTaskDefinitionMapper autoTaskDefinitionMapper;
	
	@Override
	public int insert(AutoTaskDefinition record) throws SQLException {
		return autoTaskDefinitionMapper.insert(record);
	}

	@Override
	public int update(AutoTaskDefinition record) throws SQLException {
		return autoTaskDefinitionMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return autoTaskDefinitionMapper.deleteById(id);
	}

	@Override
	public AutoTaskDefinition getById(Integer id) throws SQLException {
		return autoTaskDefinitionMapper.getById(id);
	}

	@Override
	public List<AutoTaskDefinition> getListByMap(Map<String, Object> condition) throws SQLException {
		return autoTaskDefinitionMapper.getListByMap(condition);
	}
	
	@Override
	public List<AutoTaskDefinition> getListByPo(AutoTaskDefinition record) throws SQLException {
		return autoTaskDefinitionMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return autoTaskDefinitionMapper.count(condition);
	}
	
}

