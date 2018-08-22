
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.UserFlowUsedMonthMapper;
import com.anl.user.persistence.po.UserFlowUsedMonth;

@Service
public class UserFlowUsedMonthServiceImpl implements UserFlowUsedMonthService {

	@Autowired
	UserFlowUsedMonthMapper userFlowUsedMonthMapper;
	
	@Override
	public int insert(UserFlowUsedMonth record) throws SQLException {
		return userFlowUsedMonthMapper.insert(record);
	}

	@Override
	public int update(UserFlowUsedMonth record) throws SQLException {
		return userFlowUsedMonthMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return userFlowUsedMonthMapper.deleteById(id);
	}

	@Override
	public UserFlowUsedMonth getById(Integer id) throws SQLException {
		return userFlowUsedMonthMapper.getById(id);
	}

	@Override
	public List<UserFlowUsedMonth> getListByMap(Map<String, Object> condition) throws SQLException {
		return userFlowUsedMonthMapper.getListByMap(condition);
	}
	
	@Override
	public List<UserFlowUsedMonth> getListByPo(UserFlowUsedMonth record) throws SQLException {
		return userFlowUsedMonthMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return userFlowUsedMonthMapper.count(condition);
	}
	
}

