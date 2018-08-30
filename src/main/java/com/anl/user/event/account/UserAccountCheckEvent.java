package com.anl.user.event.account;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 *用户账户资金监控
 * @Order(100)//事件执行顺序，数字越小，越早执行
 * 1.
 *
 */
@Component
public class UserAccountCheckEvent extends ApplicationEvent{

    private static final long serialVersionUID = 1L;

    public UserAccountCheckEvent(Object source) {
        super(source);
    }
}
