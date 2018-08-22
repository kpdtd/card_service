
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.SetApnTutorialsMapper;
import com.anl.user.persistence.po.SetApnTutorials;

@Service
public class SetApnTutorialsServiceImpl implements SetApnTutorialsService {

	@Autowired
	SetApnTutorialsMapper setApnTutorialsMapper;
	
	@Override
	public int insert(SetApnTutorials record) throws SQLException {
		return setApnTutorialsMapper.insert(record);
	}

	@Override
	public int update(SetApnTutorials record) throws SQLException {
		return setApnTutorialsMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return setApnTutorialsMapper.deleteById(id);
	}

	@Override
	public SetApnTutorials getById(Integer id) throws SQLException {
		return setApnTutorialsMapper.getById(id);
	}

	@Override
	public List<SetApnTutorials> getListByMap(Map<String, Object> condition) throws SQLException {
		return setApnTutorialsMapper.getListByMap(condition);
	}
	
	@Override
	public List<SetApnTutorials> getListByPo(SetApnTutorials record) throws SQLException {
		return setApnTutorialsMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return setApnTutorialsMapper.count(condition);
	}
	
}

