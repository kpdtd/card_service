
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.CashCouponDefinitionMapper;
import com.anl.user.persistence.po.CashCouponDefinition;

@Service
public class CashCouponDefinitionServiceImpl implements CashCouponDefinitionService {

	@Autowired
	CashCouponDefinitionMapper cashCouponDefinitionMapper;
	
	@Override
	public int insert(CashCouponDefinition record) throws SQLException {
		return cashCouponDefinitionMapper.insert(record);
	}

	@Override
	public int update(CashCouponDefinition record) throws SQLException {
		return cashCouponDefinitionMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return cashCouponDefinitionMapper.deleteById(id);
	}

	@Override
	public CashCouponDefinition getById(Integer id) throws SQLException {
		return cashCouponDefinitionMapper.getById(id);
	}

	@Override
	public List<CashCouponDefinition> getListByMap(Map<String, Object> condition) throws SQLException {
		return cashCouponDefinitionMapper.getListByMap(condition);
	}
	
	@Override
	public List<CashCouponDefinition> getListByPo(CashCouponDefinition record) throws SQLException {
		return cashCouponDefinitionMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return cashCouponDefinitionMapper.count(condition);
	}
	
}

