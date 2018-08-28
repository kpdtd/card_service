package com.anl.user.event;


import com.anl.user.constant.AccountConstants;
import com.anl.user.constant.UserState;
import com.anl.user.dto.UserChargingEventData;
import com.anl.user.persistence.po.*;
import com.anl.user.service.PlanDefinitionService;
import com.anl.user.service.UserAccountService;
import com.anl.user.service.UserFlowUsedDayService;
import com.anl.user.service.UserPlanService;
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

    public static final org.slf4j.Logger logger = LogFactory.getInstance().getLogger();

    /**
     * 计费逻辑:
     */
    @Order(100)
    @EventListener
    public void onApplicationEvent(UserChargingEvent event) {
        UserChargingEventData userChargingEventData = (UserChargingEventData) event.getSource();
        User user = userChargingEventData.getUser();
        Card card = userChargingEventData.getCard();
        if (user.getState() == UserState.PRE_USER || user.getState() == UserState.CANCEL_USER) {
            return;
        }
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", user.getId());
        try {
            List<UserPlan> userPlans = userPlanService.getListByMap(dataMap);
            if (CollectionUtils.isEmpty(userPlans)) {
                logger.error("月功能费扣减错误,无法获取到用户套餐:userId=" + user.getId());
                return;
            }
            UserPlan userPlan = userPlans.get(0);
            userChargingEventData.setUserPlan(userPlan);

            //进行扣减操作
            balanceSub(user, getKouFee(card, userPlan));
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("月功能费扣减异常:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("月功能费扣减异常:" + e.getMessage());
        }
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
                logger.info("扣除用户月功能费 {} 分", monthBasicMoney);
                return monthBasicMoney;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
    private void balanceSub(User user, int money) throws Exception {
        UserAccountChangeHistory userAccountChangeHistory = new UserAccountChangeHistory();
        userAccountChangeHistory.setUserId(user.getId());
        userAccountChangeHistory.setSource(AccountConstants.ACCOUNT_MONTH_SUB);
        int balance = userAccountService.updateBalance(user, userAccountChangeHistory, money);
        if (balance < 0) {
            logger.info("该用户扣减后余额不足, 调用停卡任务进行停卡...");
        }
    }
}
