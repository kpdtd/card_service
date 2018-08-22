
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.CardMapper;
import com.anl.user.persistence.po.Card;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	CardMapper cardMapper;
	
	@Override
	public int insert(Card record) throws SQLException {
		return cardMapper.insert(record);
	}

	@Override
	public int update(Card record) throws SQLException {
		return cardMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return cardMapper.deleteById(id);
	}

	@Override
	public Card getById(Integer id) throws SQLException {
		return cardMapper.getById(id);
	}

	@Override
	public List<Card> getListByMap(Map<String, Object> condition) throws SQLException {
		return cardMapper.getListByMap(condition);
	}
	
	@Override
	public List<Card> getListByPo(Card record) throws SQLException {
		return cardMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return cardMapper.count(condition);
	}
	
}

