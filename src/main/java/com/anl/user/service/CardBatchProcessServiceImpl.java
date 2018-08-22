
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.CardBatchProcessMapper;
import com.anl.user.persistence.po.CardBatchProcess;

@Service
public class CardBatchProcessServiceImpl implements CardBatchProcessService {

	@Autowired
	CardBatchProcessMapper cardBatchProcessMapper;
	
	@Override
	public int insert(CardBatchProcess record) throws SQLException {
		return cardBatchProcessMapper.insert(record);
	}

	@Override
	public int update(CardBatchProcess record) throws SQLException {
		return cardBatchProcessMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return cardBatchProcessMapper.deleteById(id);
	}

	@Override
	public CardBatchProcess getById(Integer id) throws SQLException {
		return cardBatchProcessMapper.getById(id);
	}

	@Override
	public List<CardBatchProcess> getListByMap(Map<String, Object> condition) throws SQLException {
		return cardBatchProcessMapper.getListByMap(condition);
	}
	
	@Override
	public List<CardBatchProcess> getListByPo(CardBatchProcess record) throws SQLException {
		return cardBatchProcessMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return cardBatchProcessMapper.count(condition);
	}
	
}

