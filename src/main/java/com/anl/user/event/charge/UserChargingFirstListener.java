package com.anl.user.event.charge;


import com.anl.user.constant.UserState;
import com.anl.user.dto.UserChargingEventData;
import com.anl.user.persistence.po.Card;
import com.anl.user.persistence.po.PlanDefinition;
import com.anl.user.persistence.po.User;
import com.anl.user.persistence.po.UserPlan;
import com.anl.user.service.*;
import com.anl.user.util.DateUtil;
import com.anl.user.util.LogFactory;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 进入用户扣费事件,第一个执行,进行数据初始化工作
 */
@Component
public class UserChargingFirstListener {
    public static final org.slf4j.Logger logger = LogFactory.getInstance().getLogger();
    @Autowired
    UserPlanService userPlanService;
    @Autowired
    PlanDefinitionService planDefinitionService;
    @Autowired
    UserPlanChangeHistoryService userPlanChangeHistoryService;
    @Autowired
    DataDictionaryService dataDictionaryService;
    @Autowired
    UserService userService;
    @Autowired
    CardService cardService;
    @Autowired
    UserFlowUsedDayService userFlowUsedDayService;

    @Order(10)
    @EventListener
    public void onApplicationEvent(UserChargingEvent event) {
        UserChargingEventData userChargingEventData = (UserChargingEventData) event.getSource();
        User user = userChargingEventData.getUser();
        Card card = userChargingEventData.getCard();
        if (user.getState() == UserState.PRE_USER || user.getState() == UserState.CANCEL_USER) {
            userChargingEventData.setDayFlow(0);
            userChargingEventData.setMonthFee(0);
            LogFactory.getInstance().getLogger().debug("该用户状态不需要日流量查询计费 userState=" + user.getState());
            return;
        }

        //计算月租
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", user.getId());
        try {
            List<UserPlan> userPlans = userPlanService.getListByMap(dataMap);
            if (CollectionUtils.isEmpty(userPlans)) {
                logger.error("计算月功能费错误,无法获取到用户套餐:userId=" + user.getId());
                return;
            }
            UserPlan userPlan = userPlans.get(0);
            userChargingEventData.setUserPlan(userPlan);
            userChargingEventData.setMonthFee(getKouFee(card, userPlan));

            //激活日期修改
            if (card.getActivationTime() == null) {
                card.setActivationTime(new Date());
                cardService.update(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("计算月功能费异常:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("计算月功能费异常:" + e.getMessage());
        }
        //计算需要扣减的日流量
        //需要扣减的流量总数,获取到前一天的流量
//        int flow = userFlowUsedDayService.getUsedFlowByRecordTime(card.getId(), DateUtil.afterNDaysDate(DateUtil.getTodayStartTime(), -1), new Date());
//        userChargingEventData.setDayFlow(flow);
        logger.info("该用户日流量使用计费,需要扣减的月租为{}分,需要扣减的流量为{}M", userChargingEventData.getMonthFee(), userChargingEventData.getDayFlow());
    }

    /**
     * 产生过流量开始计费,按每月的实际天数计算,除不尽加1分扣,最后一天扣除额=当月月租减去当月已经扣除的钱数.
     *
     * @return
     */
    public int getKouFee(Card card, UserPlan userPlan) {
        try {
            Integer usedFlow = userFlowUsedDayService.countFlowByCardId(card.getId());
            if (usedFlow == null || usedFlow <= 0) {
                return 0;
            }
            int monthDay = DateUtil.getDaysOfMonth(new Date());//当月的天数
            PlanDefinition planDefinition = null;
            //当月第一天,并且存在更换的套餐,按下月生效的套餐ID扣减
            if (DateUtil.isEarlyMonth() && userPlan.getNewPlanId() > 0) {
                planDefinition = planDefinitionService.getById(userPlan.getNewPlanId());
            } else {
                //按当前套餐进行扣减
                planDefinition = planDefinitionService.getById(userPlan.getPlanId());
            }
            int monthlyPlanPrice = planDefinition.getMonthlyPlanPrice();// 月套餐价格
            if (monthlyPlanPrice > 0) {
                int monthBasicMoney = monthlyPlanPrice / monthDay + (monthlyPlanPrice % monthDay > 0 ? 1 : 0); // 月基本费用计算
                if (DateUtil.isLastDayOfMonth() && monthlyPlanPrice % monthDay > 0) {
                    /**
                     * TODO 问题: 若月功能费在120以下monthlyPlanPrice % monthDay > 0时,算出的日租可能是负数, 如何扣减，当做月底返还?
                     * 月末最后一天的扣减逻辑
                     * 最后一天日租=月套餐费-(月套餐费/月天数*(月天数-1)+月天数-1)
                     */
                    monthBasicMoney = monthlyPlanPrice - (monthBasicMoney * (monthDay - 1));
                }
                logger.info("用户月功能费 {} 分", monthBasicMoney);
                return monthBasicMoney;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
