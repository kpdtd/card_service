
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.CardPoolChangeHistoryMapper;
import com.anl.user.persistence.po.CardPoolChangeHistory;

@Service
public class CardPoolChangeHistoryServiceImpl implements CardPoolChangeHistoryService {

	@Autowired
	CardPoolChangeHistoryMapper cardPoolChangeHistoryMapper;
	
	@Override
	public int insert(CardPoolChangeHistory record) throws SQLException {
		return cardPoolChangeHistoryMapper.insert(record);
	}

	@Override
	public int update(CardPoolChangeHistory record) throws SQLException {
		return cardPoolChangeHistoryMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return cardPoolChangeHistoryMapper.deleteById(id);
	}

	@Override
	public CardPoolChangeHistory getById(Integer id) throws SQLException {
		return cardPoolChangeHistoryMapper.getById(id);
	}

	@Override
	public List<CardPoolChangeHistory> getListByMap(Map<String, Object> condition) throws SQLException {
		return cardPoolChangeHistoryMapper.getListByMap(condition);
	}
	
	@Override
	public List<CardPoolChangeHistory> getListByPo(CardPoolChangeHistory record) throws SQLException {
		return cardPoolChangeHistoryMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return cardPoolChangeHistoryMapper.count(condition);
	}
	
}

