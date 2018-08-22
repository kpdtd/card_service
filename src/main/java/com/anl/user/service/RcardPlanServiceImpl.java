
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.RcardPlanMapper;
import com.anl.user.persistence.po.RcardPlan;

@Service
public class RcardPlanServiceImpl implements RcardPlanService {

	@Autowired
	RcardPlanMapper rcardPlanMapper;
	
	@Override
	public int insert(RcardPlan record) throws SQLException {
		return rcardPlanMapper.insert(record);
	}

	@Override
	public int update(RcardPlan record) throws SQLException {
		return rcardPlanMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return rcardPlanMapper.deleteById(id);
	}

	@Override
	public RcardPlan getById(Integer id) throws SQLException {
		return rcardPlanMapper.getById(id);
	}

	@Override
	public List<RcardPlan> getListByMap(Map<String, Object> condition) throws SQLException {
		return rcardPlanMapper.getListByMap(condition);
	}
	
	@Override
	public List<RcardPlan> getListByPo(RcardPlan record) throws SQLException {
		return rcardPlanMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return rcardPlanMapper.count(condition);
	}
	
}

