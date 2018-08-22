
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.SupplierMapper;
import com.anl.user.persistence.po.Supplier;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierMapper supplierMapper;
	
	@Override
	public int insert(Supplier record) throws SQLException {
		return supplierMapper.insert(record);
	}

	@Override
	public int update(Supplier record) throws SQLException {
		return supplierMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return supplierMapper.deleteById(id);
	}

	@Override
	public Supplier getById(Integer id) throws SQLException {
		return supplierMapper.getById(id);
	}

	@Override
	public List<Supplier> getListByMap(Map<String, Object> condition) throws SQLException {
		return supplierMapper.getListByMap(condition);
	}
	
	@Override
	public List<Supplier> getListByPo(Supplier record) throws SQLException {
		return supplierMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return supplierMapper.count(condition);
	}
	
}

