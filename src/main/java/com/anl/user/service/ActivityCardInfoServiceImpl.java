
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.ActivityCardInfoMapper;
import com.anl.user.persistence.po.ActivityCardInfo;

@Service
public class ActivityCardInfoServiceImpl implements ActivityCardInfoService {

	@Autowired
	ActivityCardInfoMapper activityCardInfoMapper;
	
	@Override
	public int insert(ActivityCardInfo record) throws SQLException {
		return activityCardInfoMapper.insert(record);
	}

	@Override
	public int update(ActivityCardInfo record) throws SQLException {
		return activityCardInfoMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return activityCardInfoMapper.deleteById(id);
	}

	@Override
	public ActivityCardInfo getById(Integer id) throws SQLException {
		return activityCardInfoMapper.getById(id);
	}

	@Override
	public List<ActivityCardInfo> getListByMap(Map<String, Object> condition) throws SQLException {
		return activityCardInfoMapper.getListByMap(condition);
	}
	
	@Override
	public List<ActivityCardInfo> getListByPo(ActivityCardInfo record) throws SQLException {
		return activityCardInfoMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return activityCardInfoMapper.count(condition);
	}
	
}

