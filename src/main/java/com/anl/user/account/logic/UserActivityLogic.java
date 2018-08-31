package com.anl.user.account.logic;

/**
 * Created by yangyiqiang on 2018/4/1.
 * 用户活动基础逻辑
 */
public interface UserActivityLogic {

    public int userActivity(int userId, String execLogic) throws Exception;
}