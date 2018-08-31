package com.anl.user.service;

import com.anl.user.persistence.po.DataDictionary;
import com.anl.user.persistence.vo.SelectGroup;

import java.util.List;
import java.util.Map;

/**
 * 类名: DataDictionaryService
 * 创建日期: 
 * 功能描述: 
 */
public interface DataDictionaryService extends BaseService<DataDictionary> {
    List<SelectGroup> getValueListByKey(String string);

    DataDictionary getDicByKey(String key);
    public Map<String, String> getDicValueByKey(String key);
}