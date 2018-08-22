
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.CardOwnerMapper;
import com.anl.user.persistence.po.CardOwner;

@Service
public class CardOwnerServiceImpl implements CardOwnerService {

	@Autowired
	CardOwnerMapper cardOwnerMapper;
	
	@Override
	public int insert(CardOwner record) throws SQLException {
		return cardOwnerMapper.insert(record);
	}

	@Override
	public int update(CardOwner record) throws SQLException {
		return cardOwnerMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return cardOwnerMapper.deleteById(id);
	}

	@Override
	public CardOwner getById(Integer id) throws SQLException {
		return cardOwnerMapper.getById(id);
	}

	@Override
	public List<CardOwner> getListByMap(Map<String, Object> condition) throws SQLException {
		return cardOwnerMapper.getListByMap(condition);
	}
	
	@Override
	public List<CardOwner> getListByPo(CardOwner record) throws SQLException {
		return cardOwnerMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return cardOwnerMapper.count(condition);
	}
	
}

