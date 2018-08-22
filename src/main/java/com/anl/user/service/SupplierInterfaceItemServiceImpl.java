
package com.anl.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.user.persistence.mapper.SupplierInterfaceItemMapper;
import com.anl.user.persistence.po.SupplierInterfaceItem;

@Service
public class SupplierInterfaceItemServiceImpl implements SupplierInterfaceItemService {

	@Autowired
	SupplierInterfaceItemMapper supplierInterfaceItemMapper;
	
	@Override
	public int insert(SupplierInterfaceItem record) throws SQLException {
		return supplierInterfaceItemMapper.insert(record);
	}

	@Override
	public int update(SupplierInterfaceItem record) throws SQLException {
		return supplierInterfaceItemMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return supplierInterfaceItemMapper.deleteById(id);
	}

	@Override
	public SupplierInterfaceItem getById(Integer id) throws SQLException {
		return supplierInterfaceItemMapper.getById(id);
	}

	@Override
	public List<SupplierInterfaceItem> getListByMap(Map<String, Object> condition) throws SQLException {
		return supplierInterfaceItemMapper.getListByMap(condition);
	}
	
	@Override
	public List<SupplierInterfaceItem> getListByPo(SupplierInterfaceItem record) throws SQLException {
		return supplierInterfaceItemMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return supplierInterfaceItemMapper.count(condition);
	}
	
}

