
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.ChannelMapper;
import com.anl.user.persistence.po.Channel;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	ChannelMapper channelMapper;
	
	@Override
	public int insert(Channel record) throws SQLException {
		return channelMapper.insert(record);
	}

	@Override
	public int update(Channel record) throws SQLException {
		return channelMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return channelMapper.deleteById(id);
	}

	@Override
	public Channel getById(Integer id) throws SQLException {
		return channelMapper.getById(id);
	}

	@Override
	public List<Channel> getListByMap(Map<String, Object> condition) throws SQLException {
		return channelMapper.getListByMap(condition);
	}
	
	@Override
	public List<Channel> getListByPo(Channel record) throws SQLException {
		return channelMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return channelMapper.count(condition);
	}
	
}

