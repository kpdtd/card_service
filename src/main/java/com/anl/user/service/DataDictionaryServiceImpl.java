
package com.anl.user.service;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.anl.user.persistence.vo.SelectGroup;
import org.apache.commons.lang3.StringUtils;
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

	@Override
	public List<SelectGroup> getValueListByKey(String key) {
		DataDictionary dictionary = dataDictionaryMapper.getValueByKey(key);
		String value = dictionary.getValue();
		if (StringUtils.isNotBlank(value)) {
			List<SelectGroup> groupList = Stream.of(value.split(";")).map(i -> {
				SelectGroup group = new SelectGroup();
				group.setId(i.split("=")[0]);
				group.setName(i.split("=")[1]);
				return group;
			}).collect(Collectors.toList());
			return groupList;
		}
		return new ArrayList<>();
	}

	@Override
	public DataDictionary getDicByKey(String key) {
		return dataDictionaryMapper.getValueByKey(key);
	}

	@Override
	public Map<String, String> getDicValueByKey(String key) {
		DataDictionary dictionary = new DataDictionary();
		dictionary.setName(key);
		Optional<DataDictionary> optional = dataDictionaryMapper.getListByPo(dictionary).stream().findFirst();
		if (optional.isPresent()) {
			return Stream.of(optional.get().getValue().split(";"))
					.collect(Collectors.toMap(k -> k.split("=")[0], v -> v.split("=")[1]));
		}
		return new HashMap<>();
	}
}

