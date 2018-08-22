
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.UserPlanMapper;
import com.anl.user.persistence.po.UserPlan;

@Service
public class UserPlanServiceImpl implements UserPlanService {

	@Autowired
	UserPlanMapper userPlanMapper;
	
	@Override
	public int insert(UserPlan record) throws SQLException {
		return userPlanMapper.insert(record);
	}

	@Override
	public int update(UserPlan record) throws SQLException {
		return userPlanMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return userPlanMapper.deleteById(id);
	}

	@Override
	public UserPlan getById(Integer id) throws SQLException {
		return userPlanMapper.getById(id);
	}

	@Override
	public List<UserPlan> getListByMap(Map<String, Object> condition) throws SQLException {
		return userPlanMapper.getListByMap(condition);
	}
	
	@Override
	public List<UserPlan> getListByPo(UserPlan record) throws SQLException {
		return userPlanMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return userPlanMapper.count(condition);
	}
	
}

