
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.MiPushMapper;
import com.anl.user.persistence.po.MiPush;

@Service
public class MiPushServiceImpl implements MiPushService {

	@Autowired
	MiPushMapper miPushMapper;
	
	@Override
	public int insert(MiPush record) throws SQLException {
		return miPushMapper.insert(record);
	}

	@Override
	public int update(MiPush record) throws SQLException {
		return miPushMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return miPushMapper.deleteById(id);
	}

	@Override
	public MiPush getById(Integer id) throws SQLException {
		return miPushMapper.getById(id);
	}

	@Override
	public List<MiPush> getListByMap(Map<String, Object> condition) throws SQLException {
		return miPushMapper.getListByMap(condition);
	}
	
	@Override
	public List<MiPush> getListByPo(MiPush record) throws SQLException {
		return miPushMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return miPushMapper.count(condition);
	}
	
}

