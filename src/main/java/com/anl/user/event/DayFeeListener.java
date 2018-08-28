package com.anl.user.event;


import com.anl.user.constant.UserState;
import com.anl.user.dto.UserChargingEventData;
import com.anl.user.persistence.po.Card;
import com.anl.user.persistence.po.User;
import com.anl.user.persistence.vo.UserFlowPacketPlan;
import com.anl.user.service.UserFlowPacketService;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 日租宝扣减,先扣减购买的流量包,然后再触发日租宝扣减
 */
@Component
public class DayFeeListener {
    @Autowired
    UserFlowPacketService userFlowPacketService;
    public static final org.slf4j.Logger logger = LogFactory.getInstance().getLogger();

    @Order(500)
    @EventListener
    public void onApplicationEvent(UserChargingEvent event) {
        UserChargingEventData userChargingEventData = (UserChargingEventData) event.getSource();
        User user = userChargingEventData.getUser();
        Card card = userChargingEventData.getCard();
        //预生成用户和注销用户不在扣费之列
        if (user.getState() == UserState.PRE_USER || user.getState() == UserState.CANCEL_USER) {
            return;
        }
    }

    public void kouFee(User user) {
        //获取到用户的有效期的流量包
        try {
            List<UserFlowPacketPlan> userFlowPacketPlen = userFlowPacketService.getMonthPkgByUserId(user.getId(), new Date());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
