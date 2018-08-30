package com.anl.user.event.preUser;


import com.anl.user.dto.UserChargingEventData;
import com.anl.user.persistence.po.User;
import com.anl.user.service.CardService;
import com.anl.user.util.DateUtil;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 预生成用户,超过30天,自动走销户流程
 */
@Component
public class PreUserCancelListener {
    @Autowired
    CardService cardService;

    public static final org.slf4j.Logger logger = LogFactory.getInstance().getLogger();

    @Order(1000)
    @EventListener
    public void onApplicationEvent(PreUserFlowCheckEvent event) {
        logger.info("预生成用户,超过30天,自动走销户流程开始");
        UserChargingEventData userChargingEventData = (UserChargingEventData) event.getSource();
        User user = userChargingEventData.getUser();
        Date createTime = user.getCreateTime();
        Date afterDate = DateUtil.afterNDaysDate(createTime, 30);
        boolean b=DateUtil.isBefore(DateUtil.dateToString(afterDate, DateUtil.DATE_FORMAT_COMPACT), DateUtil.dateToString(new Date(), DateUtil.DATE_FORMAT_COMPACT), DateUtil.DATE_FORMAT_COMPACT);
        if(b){
           //超过30天了

        }
        logger.info("预生成用户,超过30天,自动走销户流程完成");
    }
}
