package com.anl.user.event;


import com.anl.user.dto.UserChargingEventData;
import com.anl.user.persistence.po.PlanDefinition;
import com.anl.user.persistence.po.User;
import com.anl.user.persistence.po.UserPlan;
import com.anl.user.persistence.po.UserPlanChangeHistory;
import com.anl.user.service.*;
import com.anl.user.util.DateUtil;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 用户套餐更改,确保最后执行,
 */
@Component
public class UserPlanChangeListener {
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

    /**
     * 用户套餐更改,每月1日做套餐变更
     */
    @Order(1000)
    @EventListener
    public void onApplicationEvent(UserChargingEvent event) {
        //判断时间是不是每月1日,如果不是,直接返回
        if (DateUtil.isEarlyMonth()) {
            UserChargingEventData userChargingEventData = (UserChargingEventData) event.getSource();
            try {
                User user = userChargingEventData.getUser();
                UserPlan userPlan = userChargingEventData.getUserPlan();
                if (userPlan == null) {
                    userPlan = new UserPlan();
                    userPlan.setUserId(user.getId());
                    List<UserPlan> userPlans = userPlanService.getListByPo(userPlan);
                    userPlan = userPlans.get(0);
                }
                updatePlans(user, userPlan);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updatePlans(User user, UserPlan userPlan) throws Exception {
        UserPlanChangeHistory userPlanChangeHistory = new UserPlanChangeHistory();
        userPlanChangeHistory.setUserId(user.getId());
        userPlanChangeHistory.setOldPlanId(userPlan.getPlanId());
        userPlanChangeHistory.setNewPlanId(userPlan.getNewPlanId());
        userPlanChangeHistory.setStartTime(userPlan.getStartTime());
        userPlanChangeHistory.setEndTime(userPlan.getEndTime());
        userPlanChangeHistory.setCreateTime(new Date());
        userPlanChangeHistory.setUpdateTime(new Date());
        PlanDefinition planDefinition = planDefinitionService.getById(userPlan.getId());
        userPlanChangeHistory.setPlanName(planDefinition.getName());
        userPlan.setPlanId(userPlan.getNewPlanId());
        userPlan.setNewPlanId(0);
        userPlan.setUpdateTime(new Date());
        userPlanService.update(userPlan);
        userPlanChangeHistoryService.insert(userPlanChangeHistory);
    }
}
