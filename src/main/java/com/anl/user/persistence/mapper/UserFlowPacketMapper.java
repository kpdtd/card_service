package com.anl.user.persistence.mapper;

import com.anl.user.persistence.po.UserFlowPacket;
import com.anl.user.persistence.vo.UserFlowPacketPlan;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 类名: UserFlowPacket
 * 创建日期: 
 * 功能描述: 
 */
public interface UserFlowPacketMapper extends BaseMapper<UserFlowPacket> {
    List<UserFlowPacketPlan> getMonthPkgByUserId(@Param("userId") Integer userId, @Param("time") Date time) throws Exception;
}