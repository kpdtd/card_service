
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.PurchasingOrderMapper;
import com.anl.user.persistence.po.PurchasingOrder;

@Service
public class PurchasingOrderServiceImpl implements PurchasingOrderService {

	@Autowired
	PurchasingOrderMapper purchasingOrderMapper;
	
	@Override
	public int insert(PurchasingOrder record) throws SQLException {
		return purchasingOrderMapper.insert(record);
	}

	@Override
	public int update(PurchasingOrder record) throws SQLException {
		return purchasingOrderMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return purchasingOrderMapper.deleteById(id);
	}

	@Override
	public PurchasingOrder getById(Integer id) throws SQLException {
		return purchasingOrderMapper.getById(id);
	}

	@Override
	public List<PurchasingOrder> getListByMap(Map<String, Object> condition) throws SQLException {
		return purchasingOrderMapper.getListByMap(condition);
	}
	
	@Override
	public List<PurchasingOrder> getListByPo(PurchasingOrder record) throws SQLException {
		return purchasingOrderMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return purchasingOrderMapper.count(condition);
	}
	
}

