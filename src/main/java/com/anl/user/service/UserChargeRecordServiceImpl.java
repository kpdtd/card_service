
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.UserChargeRecordMapper;
import com.anl.user.persistence.po.UserChargeRecord;

@Service
public class UserChargeRecordServiceImpl implements UserChargeRecordService {

	@Autowired
	UserChargeRecordMapper userChargeRecordMapper;
	
	@Override
	public int insert(UserChargeRecord record) throws SQLException {
		return userChargeRecordMapper.insert(record);
	}

	@Override
	public int update(UserChargeRecord record) throws SQLException {
		return userChargeRecordMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return userChargeRecordMapper.deleteById(id);
	}

	@Override
	public UserChargeRecord getById(Integer id) throws SQLException {
		return userChargeRecordMapper.getById(id);
	}

	@Override
	public List<UserChargeRecord> getListByMap(Map<String, Object> condition) throws SQLException {
		return userChargeRecordMapper.getListByMap(condition);
	}
	
	@Override
	public List<UserChargeRecord> getListByPo(UserChargeRecord record) throws SQLException {
		return userChargeRecordMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return userChargeRecordMapper.count(condition);
	}
	
}

