
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.FlowPacketDefinitionMapper;
import com.anl.user.persistence.po.FlowPacketDefinition;

@Service
public class FlowPacketDefinitionServiceImpl implements FlowPacketDefinitionService {

	@Autowired
	FlowPacketDefinitionMapper flowPacketDefinitionMapper;
	
	@Override
	public int insert(FlowPacketDefinition record) throws SQLException {
		return flowPacketDefinitionMapper.insert(record);
	}

	@Override
	public int update(FlowPacketDefinition record) throws SQLException {
		return flowPacketDefinitionMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return flowPacketDefinitionMapper.deleteById(id);
	}

	@Override
	public FlowPacketDefinition getById(Integer id) throws SQLException {
		return flowPacketDefinitionMapper.getById(id);
	}

	@Override
	public List<FlowPacketDefinition> getListByMap(Map<String, Object> condition) throws SQLException {
		return flowPacketDefinitionMapper.getListByMap(condition);
	}
	
	@Override
	public List<FlowPacketDefinition> getListByPo(FlowPacketDefinition record) throws SQLException {
		return flowPacketDefinitionMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return flowPacketDefinitionMapper.count(condition);
	}
	
}

