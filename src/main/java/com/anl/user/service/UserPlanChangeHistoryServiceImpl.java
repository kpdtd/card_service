
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.UserPlanChangeHistoryMapper;
import com.anl.user.persistence.po.UserPlanChangeHistory;

@Service
public class UserPlanChangeHistoryServiceImpl implements UserPlanChangeHistoryService {

	@Autowired
	UserPlanChangeHistoryMapper userPlanChangeHistoryMapper;
	
	@Override
	public int insert(UserPlanChangeHistory record) throws SQLException {
		return userPlanChangeHistoryMapper.insert(record);
	}

	@Override
	public int update(UserPlanChangeHistory record) throws SQLException {
		return userPlanChangeHistoryMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return userPlanChangeHistoryMapper.deleteById(id);
	}

	@Override
	public UserPlanChangeHistory getById(Integer id) throws SQLException {
		return userPlanChangeHistoryMapper.getById(id);
	}

	@Override
	public List<UserPlanChangeHistory> getListByMap(Map<String, Object> condition) throws SQLException {
		return userPlanChangeHistoryMapper.getListByMap(condition);
	}
	
	@Override
	public List<UserPlanChangeHistory> getListByPo(UserPlanChangeHistory record) throws SQLException {
		return userPlanChangeHistoryMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return userPlanChangeHistoryMapper.count(condition);
	}
	
}

