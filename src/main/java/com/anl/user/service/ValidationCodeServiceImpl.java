
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.ValidationCodeMapper;
import com.anl.user.persistence.po.ValidationCode;

@Service
public class ValidationCodeServiceImpl implements ValidationCodeService {

	@Autowired
	ValidationCodeMapper validationCodeMapper;
	
	@Override
	public int insert(ValidationCode record) throws SQLException {
		return validationCodeMapper.insert(record);
	}

	@Override
	public int update(ValidationCode record) throws SQLException {
		return validationCodeMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return validationCodeMapper.deleteById(id);
	}

	@Override
	public ValidationCode getById(Integer id) throws SQLException {
		return validationCodeMapper.getById(id);
	}

	@Override
	public List<ValidationCode> getListByMap(Map<String, Object> condition) throws SQLException {
		return validationCodeMapper.getListByMap(condition);
	}
	
	@Override
	public List<ValidationCode> getListByPo(ValidationCode record) throws SQLException {
		return validationCodeMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return validationCodeMapper.count(condition);
	}
	
}

