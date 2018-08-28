
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.UserFlowUsedDayMapper;
import com.anl.user.persistence.po.UserFlowUsedDay;

@Service
public class UserFlowUsedDayServiceImpl implements UserFlowUsedDayService {

	@Autowired
	UserFlowUsedDayMapper userFlowUsedDayMapper;
	
	@Override
	public int insert(UserFlowUsedDay record) throws SQLException {
		return userFlowUsedDayMapper.insert(record);
	}

	@Override
	public int update(UserFlowUsedDay record) throws SQLException {
		return userFlowUsedDayMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return userFlowUsedDayMapper.deleteById(id);
	}

	@Override
	public UserFlowUsedDay getById(Integer id) throws SQLException {
		return userFlowUsedDayMapper.getById(id);
	}

	@Override
	public List<UserFlowUsedDay> getListByMap(Map<String, Object> condition) throws SQLException {
		return userFlowUsedDayMapper.getListByMap(condition);
	}
	
	@Override
	public List<UserFlowUsedDay> getListByPo(UserFlowUsedDay record) throws SQLException {
		return userFlowUsedDayMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return userFlowUsedDayMapper.count(condition);
	}

	@Override
	public Integer getBeforeUsedFlow(Integer cardId, Date fristDayOfMonth, Date currentDay) {
		return userFlowUsedDayMapper.getBeforeUsedFlow(cardId,fristDayOfMonth,currentDay);
	}

	@Override
	public Integer countFlowByCardId(Integer cardId) throws Exception {
		return userFlowUsedDayMapper.countFlowByCardId(cardId);
	}
}

