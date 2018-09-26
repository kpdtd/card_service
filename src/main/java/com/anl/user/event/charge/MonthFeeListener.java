package com.anl.user.event.charge;


import com.anl.user.constant.AccountConstants;
import com.anl.user.constant.UserState;
import com.anl.user.dto.UserChargingEventData;
import com.anl.user.persistence.po.Card;
import com.anl.user.persistence.po.User;
import com.anl.user.persistence.po.UserAccountChangeHistory;
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
 * 月功能费扣减,确保最先执行,针对试用期用户,正常充值用户和欠费停机用户,不对预生成用户扣款和销户用户
 */
@Component
public class MonthFeeListener {
    @Autowired
    UserPlanService userPlanService;
    @Autowired
    UserFlowUsedDayService userFlowUsedDayService;
    @Autowired
    PlanDefinitionService planDefinitionService;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    UserAccountChangeHistoryService userAccountChangeHistoryService;

    public static final org.slf4j.Logger logger = LogFactory.getInstance().getLogger();

    /**
     * 计费逻辑:
     */
    @Order(100)
    @EventListener
    public void onApplicationEvent(UserChargingEvent event) {
        logger.info("进入第一步月租扣减");
        UserChargingEventData userChargingEventData = (UserChargingEventData) event.getSource();
        User user = userChargingEventData.getUser();
        Card card = userChargingEventData.getCard();
        if (user.getState() == UserState.PRE_USER || user.getState() == UserState.CANCEL_USER) {
            return;
        }
        try {
            //如果当天存在扣月租的记录,直接返回
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("userId", user.getId());
            dataMap.put("source", AccountConstants.ACCOUNT_MONTH_SUB);//月功能费
            dataMap.put("startTime", DateUtil.getTodayStartTime());
            dataMap.put("endTime", new Date());
            List<UserAccountChangeHistory> userAccountChangeHistories = userAccountChangeHistoryService.getListByMap(dataMap);
            if (CollectionUtils.isNotEmpty(userAccountChangeHistories)) {
                logger.error("月功能费扣减错误,已经扣减过月租了,本次不做扣减操作,日租宝也不做扣减");
                userChargingEventData.setDayFlow(0);
                return;
            }
            dataMap = new HashMap<>();
            dataMap.put("userId", user.getId());
            List<UserPlan> userPlans = userPlanService.getListByMap(dataMap);
            if (CollectionUtils.isEmpty(userPlans)) {
                logger.error("月功能费扣减错误,无法获取到用户套餐:userId=" + user.getId());
                return;
            }
            UserPlan userPlan = userPlans.get(0);
            userChargingEventData.setUserPlan(userPlan);

            //进行扣减操作
            int b = balanceSub(user, userChargingEventData.getMonthFee());
            logger.info("月功能费扣减完成,扣减金额为{},扣减完成后账户余额为{}", userChargingEventData.getMonthFee(), b);
            userChargingEventData.setMonthFee(b);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("月功能费扣减异常:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("月功能费扣减异常:" + e.getMessage());
        }
    }

    /**
     * 余额扣减
     * 余额扣减优先级
     * 主账户 -> 子账户
     *
     * @param user
     * @param money
     * @throws Exception
     */
    private int balanceSub(User user, int money) throws Exception {
        UserAccountChangeHistory userAccountChangeHistory = new UserAccountChangeHistory();
        userAccountChangeHistory.setUserId(user.getId());
        userAccountChangeHistory.setSource(AccountConstants.ACCOUNT_MONTH_SUB);
        int balance = userAccountService.updateBalance(user, userAccountChangeHistory, money);
        return balance;
    }
}
