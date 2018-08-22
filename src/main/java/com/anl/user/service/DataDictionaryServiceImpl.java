
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.DataDictionaryMapper;
import com.anl.user.persistence.po.DataDictionary;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

	@Autowired
	DataDictionaryMapper dataDictionaryMapper;
	
	@Override
	public int insert(DataDictionary record) throws SQLException {
		return dataDictionaryMapper.insert(record);
	}

	@Override
	public int update(DataDictionary record) throws SQLException {
		return dataDictionaryMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return dataDictionaryMapper.deleteById(id);
	}

	@Override
	public DataDictionary getById(Integer id) throws SQLException {
		return dataDictionaryMapper.getById(id);
	}

	@Override
	public List<DataDictionary> getListByMap(Map<String, Object> condition) throws SQLException {
		return dataDictionaryMapper.getListByMap(condition);
	}
	
	@Override
	public List<DataDictionary> getListByPo(DataDictionary record) throws SQLException {
		return dataDictionaryMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return dataDictionaryMapper.count(condition);
	}
	
}

