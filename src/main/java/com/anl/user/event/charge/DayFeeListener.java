package com.anl.user.event.charge;


import com.anl.user.constant.AccountConstants;
import com.anl.user.dto.UserChargingEventData;
import com.anl.user.persistence.po.*;
import com.anl.user.service.PlanDefinitionService;
import com.anl.user.service.UserAccountService;
import com.anl.user.service.UserFlowPacketService;
import com.anl.user.service.UserFlowUsedDayService;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 日租宝扣减,加油包扣完了不够,就触发了这个
 */
@Component
public class DayFeeListener {
    @Autowired
    UserFlowPacketService userFlowPacketService;
    @Autowired
    UserFlowUsedDayService userFlowUsedDayService;
    @Autowired
    PlanDefinitionService planDefinitionService;
    @Autowired
    UserAccountService userAccountService;

    public static final org.slf4j.Logger logger = LogFactory.getInstance().getLogger();

    @Order(300)
    @EventListener
    public void onApplicationEvent(UserChargingEvent event) {
        logger.info("进入第三步流量日租宝扣减");
        UserChargingEventData userChargingEventData = (UserChargingEventData) event.getSource();
        User user = userChargingEventData.getUser();
        Card card = userChargingEventData.getCard();
        int flow=userChargingEventData.getDayFlow();
        if(flow<=0){
            logger.debug("当月日使用量为{}M,流量日租宝不需要扣减",flow);
            return;
        }
        UserPlan userPlan=userChargingEventData.getUserPlan();
        try {
            PlanDefinition planDefinition=planDefinitionService.getById(userPlan.getPlanId());
            int flowUnit = planDefinition.getFlowUnit();
            int flowUnitPrice = planDefinition.getFlowUnitPrice();
            logger.info("用户订购的计价单位是 {}M/{}分", flowUnit, flowUnitPrice);
            if(flowUnit <= 0 || flowUnitPrice <= 0){
                logger.info("该卡单位计价是0, 可能是VIP客户, 不进行账户扣减操作");
                return ;
            }
            int nUnit = flow / flowUnit + (flow % flowUnit > 0 ? 1 : 0);
            int money = nUnit * flowUnitPrice;
            logger.info("扣减金额为 {} 分", money);
            UserAccountChangeHistory userAccountChangeHistory = new UserAccountChangeHistory();
            userAccountChangeHistory.setSource(AccountConstants.ACCOUNT_DAY_SUB);
            userAccountService.updateBalance(user, userAccountChangeHistory, money);
            userChargingEventData.setDayFlow(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("第三步流量日租宝扣减完成");
    }


}
