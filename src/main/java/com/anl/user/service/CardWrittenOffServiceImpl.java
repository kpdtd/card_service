
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.CardWrittenOffMapper;
import com.anl.user.persistence.po.CardWrittenOff;

@Service
public class CardWrittenOffServiceImpl implements CardWrittenOffService {

	@Autowired
	CardWrittenOffMapper cardWrittenOffMapper;
	
	@Override
	public int insert(CardWrittenOff record) throws SQLException {
		return cardWrittenOffMapper.insert(record);
	}

	@Override
	public int update(CardWrittenOff record) throws SQLException {
		return cardWrittenOffMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return cardWrittenOffMapper.deleteById(id);
	}

	@Override
	public CardWrittenOff getById(Integer id) throws SQLException {
		return cardWrittenOffMapper.getById(id);
	}

	@Override
	public List<CardWrittenOff> getListByMap(Map<String, Object> condition) throws SQLException {
		return cardWrittenOffMapper.getListByMap(condition);
	}
	
	@Override
	public List<CardWrittenOff> getListByPo(CardWrittenOff record) throws SQLException {
		return cardWrittenOffMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return cardWrittenOffMapper.count(condition);
	}
	
}

