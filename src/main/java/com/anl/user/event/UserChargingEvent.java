package com.anl.user.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author lianzf
 * @Description:
 * 当用户日流量查询处理完成之后自动触发
 * @date 18/4/2上午11:29
 *
 * @Order(100)//事件执行顺序，数字越小，越早执行
 * 1.UserChargingFirstListener准备数据
 * 2.MonthFeeListener扣月租
 * 3.DayFlowPacketListener//扣流量包
 * 4.DayFeeListener扣日租宝
 *
 */
@Component
public class UserChargingEvent extends ApplicationEvent{

    private static final long serialVersionUID = 1L;

    public UserChargingEvent(Object source) {
        super(source);
    }
}
