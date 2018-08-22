
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.anl.user.persistence.po.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.CardStateHistroyMapper;
import com.anl.user.persistence.po.CardStateHistroy;

@Service
public class CardStateHistroyServiceImpl implements CardStateHistroyService {

	@Autowired
	CardStateHistroyMapper cardStateHistroyMapper;
	
	@Override
	public int insert(CardStateHistroy record) throws SQLException {
		return cardStateHistroyMapper.insert(record);
	}

	@Override
	public int update(CardStateHistroy record) throws SQLException {
		return cardStateHistroyMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return cardStateHistroyMapper.deleteById(id);
	}

	@Override
	public CardStateHistroy getById(Integer id) throws SQLException {
		return cardStateHistroyMapper.getById(id);
	}

	@Override
	public List<CardStateHistroy> getListByMap(Map<String, Object> condition) throws SQLException {
		return cardStateHistroyMapper.getListByMap(condition);
	}
	
	@Override
	public List<CardStateHistroy> getListByPo(CardStateHistroy record) throws SQLException {
		return cardStateHistroyMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return cardStateHistroyMapper.count(condition);
	}

	@Override
	public void insertHistoryByIotCard(Card card, Integer originalState, String operateInfo) throws Exception {
		CardStateHistroy cardStateHistroy=new CardStateHistroy();
		cardStateHistroy.setCardId(card.getId());
		cardStateHistroy.setCreateTime(new Date());
		cardStateHistroy.setTriggerPoint(operateInfo);
		cardStateHistroy.setOriginalState(originalState);
		cardStateHistroy.setState(card.getCardState());
		insert(cardStateHistroy);
	}
}

