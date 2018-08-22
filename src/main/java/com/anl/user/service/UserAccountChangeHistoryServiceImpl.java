
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.UserAccountChangeHistoryMapper;
import com.anl.user.persistence.po.UserAccountChangeHistory;

@Service
public class UserAccountChangeHistoryServiceImpl implements UserAccountChangeHistoryService {

	@Autowired
	UserAccountChangeHistoryMapper userAccountChangeHistoryMapper;
	
	@Override
	public int insert(UserAccountChangeHistory record) throws SQLException {
		return userAccountChangeHistoryMapper.insert(record);
	}

	@Override
	public int update(UserAccountChangeHistory record) throws SQLException {
		return userAccountChangeHistoryMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return userAccountChangeHistoryMapper.deleteById(id);
	}

	@Override
	public UserAccountChangeHistory getById(Integer id) throws SQLException {
		return userAccountChangeHistoryMapper.getById(id);
	}

	@Override
	public List<UserAccountChangeHistory> getListByMap(Map<String, Object> condition) throws SQLException {
		return userAccountChangeHistoryMapper.getListByMap(condition);
	}
	
	@Override
	public List<UserAccountChangeHistory> getListByPo(UserAccountChangeHistory record) throws SQLException {
		return userAccountChangeHistoryMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return userAccountChangeHistoryMapper.count(condition);
	}
	
}

