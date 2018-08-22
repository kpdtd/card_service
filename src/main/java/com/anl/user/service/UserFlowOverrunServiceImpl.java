
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.UserFlowOverrunMapper;
import com.anl.user.persistence.po.UserFlowOverrun;

@Service
public class UserFlowOverrunServiceImpl implements UserFlowOverrunService {

	@Autowired
	UserFlowOverrunMapper userFlowOverrunMapper;
	
	@Override
	public int insert(UserFlowOverrun record) throws SQLException {
		return userFlowOverrunMapper.insert(record);
	}

	@Override
	public int update(UserFlowOverrun record) throws SQLException {
		return userFlowOverrunMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return userFlowOverrunMapper.deleteById(id);
	}

	@Override
	public UserFlowOverrun getById(Integer id) throws SQLException {
		return userFlowOverrunMapper.getById(id);
	}

	@Override
	public List<UserFlowOverrun> getListByMap(Map<String, Object> condition) throws SQLException {
		return userFlowOverrunMapper.getListByMap(condition);
	}
	
	@Override
	public List<UserFlowOverrun> getListByPo(UserFlowOverrun record) throws SQLException {
		return userFlowOverrunMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return userFlowOverrunMapper.count(condition);
	}
	
}

