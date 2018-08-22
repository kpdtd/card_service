
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.RgoodsActivityMapper;
import com.anl.user.persistence.po.RgoodsActivity;

@Service
public class RgoodsActivityServiceImpl implements RgoodsActivityService {

	@Autowired
	RgoodsActivityMapper rgoodsActivityMapper;
	
	@Override
	public int insert(RgoodsActivity record) throws SQLException {
		return rgoodsActivityMapper.insert(record);
	}

	@Override
	public int update(RgoodsActivity record) throws SQLException {
		return rgoodsActivityMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return rgoodsActivityMapper.deleteById(id);
	}

	@Override
	public RgoodsActivity getById(Integer id) throws SQLException {
		return rgoodsActivityMapper.getById(id);
	}

	@Override
	public List<RgoodsActivity> getListByMap(Map<String, Object> condition) throws SQLException {
		return rgoodsActivityMapper.getListByMap(condition);
	}
	
	@Override
	public List<RgoodsActivity> getListByPo(RgoodsActivity record) throws SQLException {
		return rgoodsActivityMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return rgoodsActivityMapper.count(condition);
	}
	
}

