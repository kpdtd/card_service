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
}
