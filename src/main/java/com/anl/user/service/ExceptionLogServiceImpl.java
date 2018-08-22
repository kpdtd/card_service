
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.ExceptionLogMapper;
import com.anl.user.persistence.po.ExceptionLog;

@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {

	@Autowired
	ExceptionLogMapper exceptionLogMapper;
	
	@Override
	public int insert(ExceptionLog record) throws SQLException {
		return exceptionLogMapper.insert(record);
	}

	@Override
	public int update(ExceptionLog record) throws SQLException {
		return exceptionLogMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return exceptionLogMapper.deleteById(id);
	}

	@Override
	public ExceptionLog getById(Integer id) throws SQLException {
		return exceptionLogMapper.getById(id);
	}

	@Override
	public List<ExceptionLog> getListByMap(Map<String, Object> condition) throws SQLException {
		return exceptionLogMapper.getListByMap(condition);
	}
	
	@Override
	public List<ExceptionLog> getListByPo(ExceptionLog record) throws SQLException {
		return exceptionLogMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return exceptionLogMapper.count(condition);
	}
	
}

