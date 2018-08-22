
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.PlanDefinitionMapper;
import com.anl.user.persistence.po.PlanDefinition;

@Service
public class PlanDefinitionServiceImpl implements PlanDefinitionService {

	@Autowired
	PlanDefinitionMapper planDefinitionMapper;
	
	@Override
	public int insert(PlanDefinition record) throws SQLException {
		return planDefinitionMapper.insert(record);
	}

	@Override
	public int update(PlanDefinition record) throws SQLException {
		return planDefinitionMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return planDefinitionMapper.deleteById(id);
	}

	@Override
	public PlanDefinition getById(Integer id) throws SQLException {
		return planDefinitionMapper.getById(id);
	}

	@Override
	public List<PlanDefinition> getListByMap(Map<String, Object> condition) throws SQLException {
		return planDefinitionMapper.getListByMap(condition);
	}
	
	@Override
	public List<PlanDefinition> getListByPo(PlanDefinition record) throws SQLException {
		return planDefinitionMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return planDefinitionMapper.count(condition);
	}
	
}

