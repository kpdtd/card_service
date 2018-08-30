package com.anl.user.event.preUser;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 *预生成用户监控
 * @Order(100)//事件执行顺序，数字越小，越早执行
 * 1.
 *
 */
@Component
public class PreUserFlowCheckEvent extends ApplicationEvent{

    private static final long serialVersionUID = 1L;

    public PreUserFlowCheckEvent(Object source) {
        super(source);
    }
}
