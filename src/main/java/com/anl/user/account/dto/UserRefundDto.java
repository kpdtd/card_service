package com.anl.user.account.dto;

/**
 * @Auther: liangxuekai
 * @Date: 18/8/22 14:16
 * @Description:
 */
public class UserRefundDto {
    private String outTradeNo;
    private Integer money;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
