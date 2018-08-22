
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.CardOwnerChangeHistoryMapper;
import com.anl.user.persistence.po.CardOwnerChangeHistory;

@Service
public class CardOwnerChangeHistoryServiceImpl implements CardOwnerChangeHistoryService {

	@Autowired
	CardOwnerChangeHistoryMapper cardOwnerChangeHistoryMapper;
	
	@Override
	public int insert(CardOwnerChangeHistory record) throws SQLException {
		return cardOwnerChangeHistoryMapper.insert(record);
	}

	@Override
	public int update(CardOwnerChangeHistory record) throws SQLException {
		return cardOwnerChangeHistoryMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return cardOwnerChangeHistoryMapper.deleteById(id);
	}

	@Override
	public CardOwnerChangeHistory getById(Integer id) throws SQLException {
		return cardOwnerChangeHistoryMapper.getById(id);
	}

	@Override
	public List<CardOwnerChangeHistory> getListByMap(Map<String, Object> condition) throws SQLException {
		return cardOwnerChangeHistoryMapper.getListByMap(condition);
	}
	
	@Override
	public List<CardOwnerChangeHistory> getListByPo(CardOwnerChangeHistory record) throws SQLException {
		return cardOwnerChangeHistoryMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return cardOwnerChangeHistoryMapper.count(condition);
	}
	
}

