
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.ChargeListMapper;
import com.anl.user.persistence.po.ChargeList;

@Service
public class ChargeListServiceImpl implements ChargeListService {

	@Autowired
	ChargeListMapper chargeListMapper;
	
	@Override
	public int insert(ChargeList record) throws SQLException {
		return chargeListMapper.insert(record);
	}

	@Override
	public int update(ChargeList record) throws SQLException {
		return chargeListMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return chargeListMapper.deleteById(id);
	}

	@Override
	public ChargeList getById(Integer id) throws SQLException {
		return chargeListMapper.getById(id);
	}

	@Override
	public List<ChargeList> getListByMap(Map<String, Object> condition) throws SQLException {
		return chargeListMapper.getListByMap(condition);
	}
	
	@Override
	public List<ChargeList> getListByPo(ChargeList record) throws SQLException {
		return chargeListMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return chargeListMapper.count(condition);
	}
	
}

