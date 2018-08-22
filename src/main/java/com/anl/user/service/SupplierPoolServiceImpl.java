
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.SupplierPoolMapper;
import com.anl.user.persistence.po.SupplierPool;

@Service
public class SupplierPoolServiceImpl implements SupplierPoolService {

	@Autowired
	SupplierPoolMapper supplierPoolMapper;
	
	@Override
	public int insert(SupplierPool record) throws SQLException {
		return supplierPoolMapper.insert(record);
	}

	@Override
	public int update(SupplierPool record) throws SQLException {
		return supplierPoolMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return supplierPoolMapper.deleteById(id);
	}

	@Override
	public SupplierPool getById(Integer id) throws SQLException {
		return supplierPoolMapper.getById(id);
	}

	@Override
	public List<SupplierPool> getListByMap(Map<String, Object> condition) throws SQLException {
		return supplierPoolMapper.getListByMap(condition);
	}
	
	@Override
	public List<SupplierPool> getListByPo(SupplierPool record) throws SQLException {
		return supplierPoolMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return supplierPoolMapper.count(condition);
	}
	
}

