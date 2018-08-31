package com.anl.user.constant;

/**
 * 订单状态
 * Created by hechuanzhen on 17/12/28.
 */
public class OrderState {
    public static final int PENDING_PAYMENT = 1; // 待付款，初始默认状态
    public static final int PAYMENT_SUCCESS = 2; // 用户付款成功（第三方SDK异步回调响应结果）
    public static final int PAYMENT_FAIL = 3; // 用户付款失败（第三方SDK异步回调响应结果）
    public static final int FLOW_RECHARGE_SUCCESS = 4; // 为用户充值成功，订单成功
    public static final int FLOW_RECHARGERECHARGE_FAIL = 5; // 为用户充值失败，订单失败
    public static final int CANCEL = 6; // 用户已取消
    public static final int REFUNDS = 7; // 用户退款成功
}
