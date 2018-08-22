
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.ExpressMessageMapper;
import com.anl.user.persistence.po.ExpressMessage;

@Service
public class ExpressMessageServiceImpl implements ExpressMessageService {

	@Autowired
	ExpressMessageMapper expressMessageMapper;
	
	@Override
	public int insert(ExpressMessage record) throws SQLException {
		return expressMessageMapper.insert(record);
	}

	@Override
	public int update(ExpressMessage record) throws SQLException {
		return expressMessageMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return expressMessageMapper.deleteById(id);
	}

	@Override
	public ExpressMessage getById(Integer id) throws SQLException {
		return expressMessageMapper.getById(id);
	}

	@Override
	public List<ExpressMessage> getListByMap(Map<String, Object> condition) throws SQLException {
		return expressMessageMapper.getListByMap(condition);
	}
	
	@Override
	public List<ExpressMessage> getListByPo(ExpressMessage record) throws SQLException {
		return expressMessageMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return expressMessageMapper.count(condition);
	}
	
}

