
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.AutoTaskExecHistoryMapper;
import com.anl.user.persistence.po.AutoTaskExecHistory;

@Service
public class AutoTaskExecHistoryServiceImpl implements AutoTaskExecHistoryService {

	@Autowired
	AutoTaskExecHistoryMapper autoTaskExecHistoryMapper;
	
	@Override
	public int insert(AutoTaskExecHistory record) throws SQLException {
		return autoTaskExecHistoryMapper.insert(record);
	}

	@Override
	public int update(AutoTaskExecHistory record) throws SQLException {
		return autoTaskExecHistoryMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return autoTaskExecHistoryMapper.deleteById(id);
	}

	@Override
	public AutoTaskExecHistory getById(Integer id) throws SQLException {
		return autoTaskExecHistoryMapper.getById(id);
	}

	@Override
	public List<AutoTaskExecHistory> getListByMap(Map<String, Object> condition) throws SQLException {
		return autoTaskExecHistoryMapper.getListByMap(condition);
	}
	
	@Override
	public List<AutoTaskExecHistory> getListByPo(AutoTaskExecHistory record) throws SQLException {
		return autoTaskExecHistoryMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return autoTaskExecHistoryMapper.count(condition);
	}
	
}

