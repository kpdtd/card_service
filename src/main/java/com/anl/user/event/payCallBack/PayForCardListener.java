package com.anl.user.event.payCallBack;


import com.anl.user.constant.ActivityCardType;
import com.anl.user.constant.OrderState;
import com.anl.user.constant.OrderType;
import com.anl.user.constant.UserState;
import com.anl.user.persistence.po.ActivityCardInfo;
import com.anl.user.persistence.po.User;
import com.anl.user.persistence.po.UserChargeRecord;
import com.anl.user.service.ActivityCardInfoService;
import com.anl.user.service.UserChargeRecordService;
import com.anl.user.service.UserService;
import com.anl.user.util.LogFactory;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购买流量卡支付,修改购买信息的状态,修改订单状态,增加预生成用户信息
 */
@Component
public class PayForCardListener {

    @Autowired
    ActivityCardInfoService activityCardInfoService;
    @Autowired
    UserChargeRecordService userChargeRecordService;
    @Autowired
    UserService userService;
    public static final org.slf4j.Logger logger = LogFactory.getInstance().getLogger();

    @Order(500)
    @EventListener
    public void onApplicationEvent(PayCallBackEvent event) {
        logger.info("进入购买流量卡支付成功后的事件处理");
        try {
            UserChargeRecord userChargeRecord = (UserChargeRecord) event.getSource();
            if (OrderType.BUY_FLOW_CARD == userChargeRecord.getOrderType()) {
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("expressMsgId", userChargeRecord.getId());
                List<ActivityCardInfo> activityCardInfos = activityCardInfoService.getListByMap(dataMap);
                if (CollectionUtils.isNotEmpty(activityCardInfos)) {
                    ActivityCardInfo activityCardInfo = activityCardInfos.get(0);
                    //没有支付状态或者支付状态为未支付
                    if (activityCardInfo.getPayState() == null || activityCardInfo.getPayState() == ActivityCardType.ONLINE_NOPAY) {
                        activityCardInfo.setPayState(ActivityCardType.ONLINE_PAY);
                        activityCardInfo.setUpdateTime(new Date());
                        activityCardInfoService.update(activityCardInfo);
                        //生成预生成用户
                        User user=new User();
                        user.setPhone(activityCardInfo.getMobile());
                        user.setState(UserState.PRE_USER);
                        user.setWxOpenid(userChargeRecord.getOpenId());
                        user.setCreateTime(new Date());
                        user.setUpdateTime(new Date());
                        userService.insert(user);
                        //修改订单状态
                        userChargeRecord.setState(OrderState.FLOW_RECHARGE_SUCCESS);
                        userChargeRecord.setUserId(user.getId());
                        userChargeRecordService.update(userChargeRecord);

                    } else {
                        logger.debug("购买流量卡支付成功后的事件处理,该订单对应的卡购买信息支付状态不正确,payState=" + activityCardInfo.getPayState());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("购买流量卡支付成功后的事件处理异常:" + e.getMessage());
        }
    }
}
