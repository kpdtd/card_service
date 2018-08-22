
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.SysMessageMapper;
import com.anl.user.persistence.po.SysMessage;

@Service
public class SysMessageServiceImpl implements SysMessageService {

	@Autowired
	SysMessageMapper sysMessageMapper;
	
	@Override
	public int insert(SysMessage record) throws SQLException {
		return sysMessageMapper.insert(record);
	}

	@Override
	public int update(SysMessage record) throws SQLException {
		return sysMessageMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return sysMessageMapper.deleteById(id);
	}

	@Override
	public SysMessage getById(Integer id) throws SQLException {
		return sysMessageMapper.getById(id);
	}

	@Override
	public List<SysMessage> getListByMap(Map<String, Object> condition) throws SQLException {
		return sysMessageMapper.getListByMap(condition);
	}
	
	@Override
	public List<SysMessage> getListByPo(SysMessage record) throws SQLException {
		return sysMessageMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return sysMessageMapper.count(condition);
	}
	
}

