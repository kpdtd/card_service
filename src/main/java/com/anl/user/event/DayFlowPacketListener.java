package com.anl.user.event;


import com.anl.user.dto.UserChargingEventData;
import com.anl.user.persistence.po.Card;
import com.anl.user.persistence.po.User;
import com.anl.user.persistence.po.UserFlowPacket;
import com.anl.user.persistence.po.UserPlan;
import com.anl.user.persistence.vo.UserFlowPacketPlan;
import com.anl.user.service.UserFlowPacketService;
import com.anl.user.service.UserFlowUsedDayService;
import com.anl.user.util.DateUtil;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 扣减购买的流量包,然后再触发日租宝扣减
 */
@Component
public class DayFlowPacketListener {
    @Autowired
    UserFlowPacketService userFlowPacketService;
    @Autowired
    UserFlowUsedDayService userFlowUsedDayService;
    public static final org.slf4j.Logger logger = LogFactory.getInstance().getLogger();

    @Order(200)
    @EventListener
    public void onApplicationEvent(UserChargingEvent event) {
        logger.info("进入第二步流量加油包扣减");
        UserChargingEventData userChargingEventData = (UserChargingEventData) event.getSource();
        User user = userChargingEventData.getUser();
        int flow = userChargingEventData.getDayFlow();
        if (flow <= 0) {
            return;
        }
        //获取到用户的有效期的流量包
        Date date = new Date();
        if (DateUtil.isEarlyMonth()) {
            date = DateUtil.getLastDayOfBeforeMonth();
        }
        try {
            List<UserFlowPacketPlan> userFlowPacketPlanList = userFlowPacketService.getMonthPkgByUserId(user.getId(), date);
            for(UserFlowPacketPlan userFlowPacketPlan : userFlowPacketPlanList){
                int balance = userFlowPacketPlan.getBalance();
                logger.info("加油包ID={},名称为[{}], 剩余可用流量为 {} M, 开始扣减昨日用量", userFlowPacketPlan.getPacketId(), userFlowPacketPlan.getPacketName(), balance);
                UserFlowPacket userFlowPacket = userFlowPacketService.getById(userFlowPacketPlan.getId());
                if(balance > 0){
                    flow = balance - Math.abs(flow);
                    if(flow >= 0){
                        userFlowPacket.setBalance(flow);
                        userFlowPacket.setUpdateTime(new Date());
                        userFlowPacketService.update(userFlowPacket);
                        logger.info("加油包ID={},名称为[{}], 扣减后剩余流量为 {} M", userFlowPacketPlan.getPacketId(), userFlowPacketPlan.getPacketName(), flow);
                        flow = 0; // *******如果扣减后用量大于等于0则不进行余额扣减
                        break;
                    }
                    flow = Math.abs(flow);
                    userFlowPacket.setBalance(0);
                    logger.info("加油包ID={},名称为[{}], 扣减后流量已用完....", userFlowPacketPlan.getPacketId(), userFlowPacketPlan.getPacketName());
                    userFlowPacket.setUpdateTime(new Date());
                    userFlowPacketService.update(userFlowPacket);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("第二步流量加油包扣减完成,扣减后的昨日用量为{}",flow);
        userChargingEventData.setDayFlow(flow);
    }

}
