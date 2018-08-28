package com.anl.user.service;

import com.anl.user.persistence.po.UserFlowPacket;
import com.anl.user.persistence.vo.UserFlowPacketPlan;

import java.util.Date;
import java.util.List;

/**
 * 类名: UserFlowPacketService
 * 创建日期: 
 * 功能描述: 
 */
public interface UserFlowPacketService extends BaseService<UserFlowPacket> {
    List<UserFlowPacketPlan> getMonthPkgByUserId(Integer userId, Date time) throws Exception;
}