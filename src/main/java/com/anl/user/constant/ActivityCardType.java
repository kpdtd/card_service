package com.anl.user.constant;

/**
 * 购买卡订单类型
 */
public class ActivityCardType {
    //'1未发货，2快递已下单，3拒收，4已完成'
    public static final int NO_SEND_OUT = 1;
    public static final int ORDERED = 2;
    public static final int REJECT = 3;
    public static final int FINISH = 4;
    //'11在线支付未付款，12在线支付已付款，13在线支付已退款，21货到付款未付款，22货到付款已付款，23货到付款已退款'
    public static final int ONLINE_NOPAY = 11;
    public static final int ONLINE_PAY = 12;
    public static final int ONLINE_REFUNDS= 13;

    public static final int ARRIVAL_NOPAY = 21;
    public static final int ARRIVAL_PAY = 22;
    public static final int ARRIVAL_REFUNDS= 23;

}
