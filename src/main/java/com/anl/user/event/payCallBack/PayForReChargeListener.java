package com.anl.user.event.payCallBack;


import com.anl.user.account.dto.ChangeAccountDto;
import com.anl.user.account.logic.UserAccountLogic;
import com.anl.user.constant.AccountConstants;
import com.anl.user.constant.OrderState;
import com.anl.user.constant.OrderType;
import com.anl.user.persistence.po.UserAccount;
import com.anl.user.persistence.po.UserChargeRecord;
import com.anl.user.service.UserChargeRecordService;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 为账号充值的支付,对该用户的主账户进行加款操作,并修改订单状态
 */
@Component
public class PayForReChargeListener {

    @Autowired
    UserAccountLogic userAccountLogic;
    @Autowired
    UserChargeRecordService userChargeRecordService;
    public static final org.slf4j.Logger logger = LogFactory.getInstance().getLogger();

    @Order(100)
    @EventListener
    public void onApplicationEvent(PayCallBackEvent event) {
        logger.info("进入账号充值支付成功后的事件处理");
        try {
            UserChargeRecord userChargeRecord = (UserChargeRecord) event.getSource();
            if (OrderType.FLOW_RECHARGE == userChargeRecord.getOrderType()) {
                ChangeAccountDto changeAccountDto=new ChangeAccountDto(userChargeRecord.getUserId(),userChargeRecord.getMoney(),0, AccountConstants.ACCOUNT_RECHARGE);
                userAccountLogic.changeAccount(changeAccountDto);
                //修改订单状态
                userChargeRecord.setState(OrderState.FLOW_RECHARGE_SUCCESS);
                userChargeRecordService.update(userChargeRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("账号充值支付成功后的事件处理异常:" + e.getMessage());
        }
    }
}
