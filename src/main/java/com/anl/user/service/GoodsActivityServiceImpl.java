
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.GoodsActivityMapper;
import com.anl.user.persistence.po.GoodsActivity;

@Service
public class GoodsActivityServiceImpl implements GoodsActivityService {

	@Autowired
	GoodsActivityMapper goodsActivityMapper;
	
	@Override
	public int insert(GoodsActivity record) throws SQLException {
		return goodsActivityMapper.insert(record);
	}

	@Override
	public int update(GoodsActivity record) throws SQLException {
		return goodsActivityMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return goodsActivityMapper.deleteById(id);
	}

	@Override
	public GoodsActivity getById(Integer id) throws SQLException {
		return goodsActivityMapper.getById(id);
	}

	@Override
	public List<GoodsActivity> getListByMap(Map<String, Object> condition) throws SQLException {
		return goodsActivityMapper.getListByMap(condition);
	}
	
	@Override
	public List<GoodsActivity> getListByPo(GoodsActivity record) throws SQLException {
		return goodsActivityMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return goodsActivityMapper.count(condition);
	}
	
}

