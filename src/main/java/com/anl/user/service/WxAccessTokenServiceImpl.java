
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.WxAccessTokenMapper;
import com.anl.user.persistence.po.WxAccessToken;

@Service
public class WxAccessTokenServiceImpl implements WxAccessTokenService {

	@Autowired
	WxAccessTokenMapper wxAccessTokenMapper;
	
	@Override
	public int insert(WxAccessToken record) throws SQLException {
		return wxAccessTokenMapper.insert(record);
	}

	@Override
	public int update(WxAccessToken record) throws SQLException {
		return wxAccessTokenMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return wxAccessTokenMapper.deleteById(id);
	}

	@Override
	public WxAccessToken getById(Integer id) throws SQLException {
		return wxAccessTokenMapper.getById(id);
	}

	@Override
	public List<WxAccessToken> getListByMap(Map<String, Object> condition) throws SQLException {
		return wxAccessTokenMapper.getListByMap(condition);
	}
	
	@Override
	public List<WxAccessToken> getListByPo(WxAccessToken record) throws SQLException {
		return wxAccessTokenMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return wxAccessTokenMapper.count(condition);
	}

	@Override
	public WxAccessToken getWxAccessTokenByAppId(String appId) {
		return wxAccessTokenMapper.getWxAccessTokenByAppId(appId);
	}
}

