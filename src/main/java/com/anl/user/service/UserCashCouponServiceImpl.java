
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.UserCashCouponMapper;
import com.anl.user.persistence.po.UserCashCoupon;

@Service
public class UserCashCouponServiceImpl implements UserCashCouponService {

	@Autowired
	UserCashCouponMapper userCashCouponMapper;
	
	@Override
	public int insert(UserCashCoupon record) throws SQLException {
		return userCashCouponMapper.insert(record);
	}

	@Override
	public int update(UserCashCoupon record) throws SQLException {
		return userCashCouponMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return userCashCouponMapper.deleteById(id);
	}

	@Override
	public UserCashCoupon getById(Integer id) throws SQLException {
		return userCashCouponMapper.getById(id);
	}

	@Override
	public List<UserCashCoupon> getListByMap(Map<String, Object> condition) throws SQLException {
		return userCashCouponMapper.getListByMap(condition);
	}
	
	@Override
	public List<UserCashCoupon> getListByPo(UserCashCoupon record) throws SQLException {
		return userCashCouponMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return userCashCouponMapper.count(condition);
	}
	
}

