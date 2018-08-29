package com.anl.user.persistence.mapper;

import com.anl.user.persistence.mapper.BaseMapper;
import com.anl.user.persistence.po.UserFlowUsedDay;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 类名: UserFlowUsedDay
 * 创建日期: 
 * 功能描述: 
 */
public interface UserFlowUsedDayMapper extends BaseMapper<UserFlowUsedDay> {
    Integer getUsedFlowByRecordTime(@Param("cardId") Integer cardId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
    Integer countFlowByCardId(@Param("cardId") Integer cardId);
}