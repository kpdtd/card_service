package com.anl.user.event.payCallBack;


import com.anl.user.account.dto.ChangeAccountDto;
import com.anl.user.account.logic.UserAccountLogic;
import com.anl.user.constant.AccountConstants;
import com.anl.user.constant.OrderState;
import com.anl.user.constant.OrderType;
import com.anl.user.dto.ActionResult;
import com.anl.user.logic.UserLogic;
import com.anl.user.logic.UserLoginLogicImpl;
import com.anl.user.persistence.po.UserAccountChangeHistory;
import com.anl.user.persistence.po.UserChargeRecord;
import com.anl.user.service.UserChargeRecordService;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 为账号充值的支付,对该用户的主账户进行加款操作,并修改订单状态,对大于0的账户调用开卡逻辑
 */
@Component
public class PayForReChargeListener {

    @Autowired
    UserAccountLogic userAccountLogic;
    @Autowired
    UserChargeRecordService userChargeRecordService;
    @Autowired
    UserLoginLogicImpl userLoginLogic;
    @Autowired
    UserLogic userLogic;
    public static final org.slf4j.Logger logger = LogFactory.getInstance().getLogger();

    @Order(100)
    @EventListener
    public void onApplicationEvent(PayCallBackEvent event) {
        logger.info("进入账号充值支付成功后的事件处理");
        try {
            UserChargeRecord userChargeRecord = (UserChargeRecord) event.getSource();
            if (OrderType.FLOW_RECHARGE == userChargeRecord.getOrderType()) {
                ChangeAccountDto changeAccountDto = new ChangeAccountDto(userChargeRecord.getUserId(), userChargeRecord.getMoney(), 0, AccountConstants.ACCOUNT_RECHARGE);
                ActionResult actionResult = userAccountLogic.changeAccount(changeAccountDto);
                if (actionResult.getCode() == ActionResult.success().getCode()) {
                    //修改订单状态
                    userChargeRecord.setState(OrderState.FLOW_RECHARGE_SUCCESS);
                    userChargeRecordService.update(userChargeRecord);
                    //检查是否需要对卡进行停开机操作
                    UserAccountChangeHistory userAccountChangeHistory = (UserAccountChangeHistory) actionResult.getData();
                    if ((userAccountChangeHistory.getPaChangeAfter() + userAccountChangeHistory.getSaChangeAfter()) > 0) {

                        if (userLogic.userOpenCard(userChargeRecord.getUserId(), "充值开卡")) {
                            logger.debug("账号充值支付成功后调用开卡逻辑,开卡成功返回");
                        } else {
                            logger.error("账号充值支付成功后调用开卡逻辑异常");
                        }
                    }else {
                        logger.debug("账号充值支付成功后账户余额仍然小于等于0,不用调开卡逻辑");
                    }
                } else {
                    logger.error("账号充值支付成功后修改账户信息错误,msg=" + actionResult.getCodemsg());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("账号充值支付成功后的事件处理异常:" + e.getMessage());
        }
    }
}
