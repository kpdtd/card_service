package com.anl.user.event.charge;


import com.anl.user.dto.UserChargingEventData;
import com.anl.user.logic.OpenOrCloseCardLogicImpl;
import com.anl.user.persistence.po.Card;
import com.anl.user.persistence.po.ErrorRetryRecord;
import com.anl.user.service.*;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Date;

/**
 * 进入用户扣费事件,最后执行,主要是对欠费用户进行停机操作
 */
@Component
public class UserChargingLastListener {
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
    UserFlowUsedDayService userFlowUsedDayService;
    @Autowired
    OpenOrCloseCardLogicImpl openOrCloseCardLogic;
    @Autowired
    ErrorRetryRecordService errorRetryRecordService;
    @Order(5000)
    @EventListener
    public void onApplicationEvent(UserChargingEvent event) {
        UserChargingEventData userChargingEventData = (UserChargingEventData) event.getSource();
        int fee = userChargingEventData.getMonthFee();
        if (fee <= 0) {
            Card card = userChargingEventData.getCard();
            boolean b=false;
            //执行停机操作
            try {
                b= openOrCloseCardLogic.closeCard(card, "欠费停机");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(!b){
                //停机失败了,写日志表
                ErrorRetryRecord errorRetryRecord=new ErrorRetryRecord();
                errorRetryRecord.setCardId(card.getId());
                errorRetryRecord.setCardState(card.getCardState());
                errorRetryRecord.setCreateTime(new Date());
                errorRetryRecord.setErrorPoint(4);
                errorRetryRecord.setErrorType(1);
                errorRetryRecord.setIccid(card.getIccid());
                errorRetryRecord.setResult(2);
                try {
                    errorRetryRecordService.insert(errorRetryRecord);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
