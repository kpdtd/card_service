
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.RealnameAuthenticationMapper;
import com.anl.user.persistence.po.RealnameAuthentication;

@Service
public class RealnameAuthenticationServiceImpl implements RealnameAuthenticationService {

	@Autowired
	RealnameAuthenticationMapper realnameAuthenticationMapper;
	
	@Override
	public int insert(RealnameAuthentication record) throws SQLException {
		return realnameAuthenticationMapper.insert(record);
	}

	@Override
	public int update(RealnameAuthentication record) throws SQLException {
		return realnameAuthenticationMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return realnameAuthenticationMapper.deleteById(id);
	}

	@Override
	public RealnameAuthentication getById(Integer id) throws SQLException {
		return realnameAuthenticationMapper.getById(id);
	}

	@Override
	public List<RealnameAuthentication> getListByMap(Map<String, Object> condition) throws SQLException {
		return realnameAuthenticationMapper.getListByMap(condition);
	}
	
	@Override
	public List<RealnameAuthentication> getListByPo(RealnameAuthentication record) throws SQLException {
		return realnameAuthenticationMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return realnameAuthenticationMapper.count(condition);
	}
	
}

