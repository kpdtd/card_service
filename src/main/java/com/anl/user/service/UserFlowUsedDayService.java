package com.anl.user.service;

import com.anl.user.persistence.po.UserFlowUsedDay;

import java.util.Date;

/**
 * 类名: UserFlowUsedDayService
 * 创建日期: 
 * 功能描述: 
 */
public interface UserFlowUsedDayService extends BaseService<UserFlowUsedDay> {
    Integer getBeforeUsedFlow(Integer cardId, Date fristDayOfMonth, Date currentDay);
    Integer countFlowByCardId(Integer cardId) throws Exception;
}