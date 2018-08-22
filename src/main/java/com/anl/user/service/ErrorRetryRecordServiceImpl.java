
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.ErrorRetryRecordMapper;
import com.anl.user.persistence.po.ErrorRetryRecord;

@Service
public class ErrorRetryRecordServiceImpl implements ErrorRetryRecordService {

	@Autowired
	ErrorRetryRecordMapper errorRetryRecordMapper;
	
	@Override
	public int insert(ErrorRetryRecord record) throws SQLException {
		return errorRetryRecordMapper.insert(record);
	}

	@Override
	public int update(ErrorRetryRecord record) throws SQLException {
		return errorRetryRecordMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return errorRetryRecordMapper.deleteById(id);
	}

	@Override
	public ErrorRetryRecord getById(Integer id) throws SQLException {
		return errorRetryRecordMapper.getById(id);
	}

	@Override
	public List<ErrorRetryRecord> getListByMap(Map<String, Object> condition) throws SQLException {
		return errorRetryRecordMapper.getListByMap(condition);
	}
	
	@Override
	public List<ErrorRetryRecord> getListByPo(ErrorRetryRecord record) throws SQLException {
		return errorRetryRecordMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return errorRetryRecordMapper.count(condition);
	}
	
}

