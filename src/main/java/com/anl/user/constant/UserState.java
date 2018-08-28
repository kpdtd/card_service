package com.anl.user.constant;

/**
 * Created by yangyiqiang on 2018/8/28.
 */
public class UserState {
    // 预生成用户,流量卡快递期间到用户绑定激活之前的一种状态
    public static final int PRE_USER = 1;
    //试用期用户,用户完成绑卡认证,但是没有充值,账户有赠送
    public static final int PRO_USER = 2;
    //正式充值用户
    public static final int CHARGE_USER = 3;
    //停机用户
    public static final int OFF_USER = 4;
    //注销的用户
    public static final int CANCEL_USER = 5;
}
