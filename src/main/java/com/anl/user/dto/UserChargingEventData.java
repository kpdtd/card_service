package com.anl.user.dto;


import com.anl.user.persistence.po.Card;
import com.anl.user.persistence.po.User;
import com.anl.user.persistence.po.UserPlan;

/**
 * Created by yangyiqiang on 2018/8/17.
 * 日流量查询完成后发布事件传输的data
 */
public class UserChargingEventData {
    private User user;
    private Card card;
    private UserPlan userPlan;
    private  int monthFee=0;//需要扣除的月租(当天的),进入事件第一步进行计算
    private  int dayFlow=0;//需要扣除的流量使用量(前一天的),进入事件第一步进行计算
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public UserPlan getUserPlan() {
        return userPlan;
    }

    public void setUserPlan(UserPlan userPlan) {
        this.userPlan = userPlan;
    }

    public int getMonthFee() {
        return monthFee;
    }

    public void setMonthFee(int monthFee) {
        this.monthFee = monthFee;
    }

    public int getDayFlow() {
        return dayFlow;
    }

    public void setDayFlow(int dayFlow) {
        this.dayFlow = dayFlow;
    }
}
