
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.ResultOverrunRecordMapper;
import com.anl.user.persistence.po.ResultOverrunRecord;

@Service
public class ResultOverrunRecordServiceImpl implements ResultOverrunRecordService {

	@Autowired
	ResultOverrunRecordMapper resultOverrunRecordMapper;
	
	@Override
	public int insert(ResultOverrunRecord record) throws SQLException {
		return resultOverrunRecordMapper.insert(record);
	}

	@Override
	public int update(ResultOverrunRecord record) throws SQLException {
		return resultOverrunRecordMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return resultOverrunRecordMapper.deleteById(id);
	}

	@Override
	public ResultOverrunRecord getById(Integer id) throws SQLException {
		return resultOverrunRecordMapper.getById(id);
	}

	@Override
	public List<ResultOverrunRecord> getListByMap(Map<String, Object> condition) throws SQLException {
		return resultOverrunRecordMapper.getListByMap(condition);
	}
	
	@Override
	public List<ResultOverrunRecord> getListByPo(ResultOverrunRecord record) throws SQLException {
		return resultOverrunRecordMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return resultOverrunRecordMapper.count(condition);
	}
	
}

