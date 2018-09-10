package com.anl.user.event.payCallBack;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class PayCallBackEvent extends ApplicationEvent{

    private static final long serialVersionUID = 1L;

    public PayCallBackEvent(Object source) {
        super(source);
    }
}
