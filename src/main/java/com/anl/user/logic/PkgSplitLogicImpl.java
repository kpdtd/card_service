package com.anl.user.logic;

import com.anl.user.persistence.po.FlowPacketDefinition;
import com.anl.user.persistence.po.UserFlowPacket;
import com.anl.user.service.FlowPacketDefinitionService;
import com.anl.user.service.UserFlowPacketService;
import com.anl.user.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PkgSplitLogicImpl implements PkgSplitLogic {

    @Autowired
    FlowPacketDefinitionService flowPacketDefinitionService;
    @Autowired
    UserFlowPacketService userFlowPacketService;

    @Override
    public void pkgSplit(FlowPacketDefinition flowPacketDefinition, int userId,String source) throws Exception {
        UserFlowPacket userFlowPacket = new UserFlowPacket();
        userFlowPacket.setUserId(userId);
        userFlowPacket.setCreateTime(new Date());
        userFlowPacket.setBalance(flowPacketDefinition.getFlowNumber());
        userFlowPacket.setState(1);//使用中
        if (flowPacketDefinition.getStartTime() == 1) {
            //立即生效
            userFlowPacket.setStartTime(new Date());
            String n = DateUtil.dateToString(DateUtil.afterNDaysDate(new Date(), flowPacketDefinition.getValidDays()), DateUtil.DATE_FORMAT_COMPACT);
            String last = DateUtil.dateToString(DateUtil.getLastDayOfMonth(), DateUtil.DATE_FORMAT_COMPACT);
            //如果n天之后的日期大于本月最后一天,那么endTime为本月最后一天,否则为n天之后的日期
            if (DateUtil.isBefore(n, last, DateUtil.DATE_FORMAT_COMPACT)) {
                userFlowPacket.setEndTime(DateUtil.afterNDaysDate(new Date(), flowPacketDefinition.getValidDays()));
            } else {
                //月底
                userFlowPacket.setEndTime(DateUtil.getLastDayOfMonth());
            }
        } else {
            //下月生效
            userFlowPacket.setStartTime(DateUtil.getFirstDayOfMonth(DateUtil.afterNMonthDate(1)));
            if (flowPacketDefinition.getValidDays() != 31) {
                userFlowPacket.setEndTime(DateUtil.afterNDaysDate(new Date(), flowPacketDefinition.getValidDays()));
            } else {
                //下月底
                userFlowPacket.setEndTime(DateUtil.getLastDayOfMonth(DateUtil.afterNMonthDate(1)));
            }
        }
        userFlowPacket.setCreateTime(new Date());
        userFlowPacket.setUpdateTime(new Date());
        userFlowPacket.setFlow(flowPacketDefinition.getFlowNumber());
        userFlowPacket.setPacketId(flowPacketDefinition.getId());
        userFlowPacket.setPacketName(flowPacketDefinition.getName());
        userFlowPacket.setSource(source);
        userFlowPacketService.insert(userFlowPacket);
    }
}