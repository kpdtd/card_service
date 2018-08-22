
package com.anl.user.service;

import com.anl.user.persistence.mapper.InterfaceListMapper;
import com.anl.user.persistence.po.InterfaceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class InterfaceListServiceImpl implements InterfaceListService {

	@Autowired
	InterfaceListMapper interfaceListMapper;
	
	@Override
	public int insert(InterfaceList record) throws SQLException {
		return interfaceListMapper.insert(record);
	}

	@Override
	public int update(InterfaceList record) throws SQLException {
		return interfaceListMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return interfaceListMapper.deleteById(id);
	}

	@Override
	public InterfaceList getById(Integer id) throws SQLException {
		return interfaceListMapper.getById(id);
	}

	@Override
	public List<InterfaceList> getListByMap(Map<String, Object> condition) throws SQLException {
		return interfaceListMapper.getListByMap(condition);
	}
	
	@Override
	public List<InterfaceList> getListByPo(InterfaceList record) throws SQLException {
		return interfaceListMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return interfaceListMapper.count(condition);
	}
	
}

