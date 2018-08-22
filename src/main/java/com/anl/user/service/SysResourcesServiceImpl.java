
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.SysResourcesMapper;
import com.anl.user.persistence.po.SysResources;

@Service
public class SysResourcesServiceImpl implements SysResourcesService {

	@Autowired
	SysResourcesMapper sysResourcesMapper;
	
	@Override
	public int insert(SysResources record) throws SQLException {
		return sysResourcesMapper.insert(record);
	}

	@Override
	public int update(SysResources record) throws SQLException {
		return sysResourcesMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return sysResourcesMapper.deleteById(id);
	}

	@Override
	public SysResources getById(Integer id) throws SQLException {
		return sysResourcesMapper.getById(id);
	}

	@Override
	public List<SysResources> getListByMap(Map<String, Object> condition) throws SQLException {
		return sysResourcesMapper.getListByMap(condition);
	}
	
	@Override
	public List<SysResources> getListByPo(SysResources record) throws SQLException {
		return sysResourcesMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return sysResourcesMapper.count(condition);
	}
	
}

