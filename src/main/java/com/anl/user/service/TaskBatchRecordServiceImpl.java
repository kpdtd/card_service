
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.TaskBatchRecordMapper;
import com.anl.user.persistence.po.TaskBatchRecord;

@Service
public class TaskBatchRecordServiceImpl implements TaskBatchRecordService {

	@Autowired
	TaskBatchRecordMapper taskBatchRecordMapper;
	
	@Override
	public int insert(TaskBatchRecord record) throws SQLException {
		return taskBatchRecordMapper.insert(record);
	}

	@Override
	public int update(TaskBatchRecord record) throws SQLException {
		return taskBatchRecordMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return taskBatchRecordMapper.deleteById(id);
	}

	@Override
	public TaskBatchRecord getById(Integer id) throws SQLException {
		return taskBatchRecordMapper.getById(id);
	}

	@Override
	public List<TaskBatchRecord> getListByMap(Map<String, Object> condition) throws SQLException {
		return taskBatchRecordMapper.getListByMap(condition);
	}
	
	@Override
	public List<TaskBatchRecord> getListByPo(TaskBatchRecord record) throws SQLException {
		return taskBatchRecordMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return taskBatchRecordMapper.count(condition);
	}
	
}

