
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.UserMapper;
import com.anl.user.persistence.po.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public int insert(User record) throws SQLException {
		return userMapper.insert(record);
	}

	@Override
	public int update(User record) throws SQLException {
		return userMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return userMapper.deleteById(id);
	}

	@Override
	public User getById(Integer id) throws SQLException {
		return userMapper.getById(id);
	}

	@Override
	public List<User> getListByMap(Map<String, Object> condition) throws SQLException {
		return userMapper.getListByMap(condition);
	}
	
	@Override
	public List<User> getListByPo(User record) throws SQLException {
		return userMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return userMapper.count(condition);
	}
	
}

