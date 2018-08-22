
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.UserRefundRecordMapper;
import com.anl.user.persistence.po.UserRefundRecord;

@Service
public class UserRefundRecordServiceImpl implements UserRefundRecordService {

	@Autowired
	UserRefundRecordMapper userRefundRecordMapper;
	
	@Override
	public int insert(UserRefundRecord record) throws SQLException {
		return userRefundRecordMapper.insert(record);
	}

	@Override
	public int update(UserRefundRecord record) throws SQLException {
		return userRefundRecordMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return userRefundRecordMapper.deleteById(id);
	}

	@Override
	public UserRefundRecord getById(Integer id) throws SQLException {
		return userRefundRecordMapper.getById(id);
	}

	@Override
	public List<UserRefundRecord> getListByMap(Map<String, Object> condition) throws SQLException {
		return userRefundRecordMapper.getListByMap(condition);
	}
	
	@Override
	public List<UserRefundRecord> getListByPo(UserRefundRecord record) throws SQLException {
		return userRefundRecordMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return userRefundRecordMapper.count(condition);
	}
	
}

