package com.anl.user.event.preUser;


import com.anl.user.dto.UserChargingEventData;
import com.anl.user.persistence.po.Card;
import com.anl.user.service.CardService;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Date;

/**
 * 预生成用户,产生流量进行通知,并修改卡的激活时间,修改成正使用状态
 */
@Component
public class NotifyAndUpdateCardListener {
    @Autowired
    CardService cardService;

    public static final org.slf4j.Logger logger = LogFactory.getInstance().getLogger();

    @Order(100)
    @EventListener
    public void onApplicationEvent(PreUserFlowCheckEvent event) {
        logger.info("预生成用户产生流量进行通知并修改卡状态开始");
        UserChargingEventData userChargingEventData = (UserChargingEventData) event.getSource();
        Card card = userChargingEventData.getCard();
        int flow = userChargingEventData.getDayFlow();
        if (flow > 0) {
            card.setActivationTime(new Date());
            try {
                cardService.update(card);
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("预生成用户产生了{}M流量,对iccid的卡{}进行激活修改失败", flow, card.getIccid());
            }
            logger.info("预生成用户产生了{}M流量,对iccid的卡{}进行激活并发送通知", flow, card.getIccid());
        }
        logger.info("预生成用户产生流量进行通知并修改卡状态完成");
    }
}
