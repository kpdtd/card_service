package com.anl.user.event.payCallBack;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * 支付成功回调之后调用
 * 1目前分购买流量卡和充值两种,根据订单类型进行不同的业务处理
 */
@Component
public class PayCallBackEvent extends ApplicationEvent{

    private static final long serialVersionUID = 1L;

    public PayCallBackEvent(Object source) {
        super(source);
    }
}
