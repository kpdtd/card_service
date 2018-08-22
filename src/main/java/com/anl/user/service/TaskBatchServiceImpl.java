
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.TaskBatchMapper;
import com.anl.user.persistence.po.TaskBatch;

@Service
public class TaskBatchServiceImpl implements TaskBatchService {

	@Autowired
	TaskBatchMapper taskBatchMapper;
	
	@Override
	public int insert(TaskBatch record) throws SQLException {
		return taskBatchMapper.insert(record);
	}

	@Override
	public int update(TaskBatch record) throws SQLException {
		return taskBatchMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return taskBatchMapper.deleteById(id);
	}

	@Override
	public TaskBatch getById(Integer id) throws SQLException {
		return taskBatchMapper.getById(id);
	}

	@Override
	public List<TaskBatch> getListByMap(Map<String, Object> condition) throws SQLException {
		return taskBatchMapper.getListByMap(condition);
	}
	
	@Override
	public List<TaskBatch> getListByPo(TaskBatch record) throws SQLException {
		return taskBatchMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return taskBatchMapper.count(condition);
	}
	
}

