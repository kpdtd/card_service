package com.anl.user.constant;

public class RefundRecordType {//1-发送第三方发送成功  2-发送第三方支付失败   3-退款成功  4-退款失败',
    public static final int SEND_SUCCESS  = 1; // 发送第三方支付成功
    public static final int SEND_FAIL = 2; // 发送第三方支付失败
    public static final int REFUND_RECORD_SUCCESS = 3; // 退款成功
    public static final int REFUND_RECORD_FAIL = 4; // 退款失败
}
