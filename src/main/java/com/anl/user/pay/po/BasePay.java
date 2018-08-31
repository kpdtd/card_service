package com.anl.user.pay.po;

public class BasePay {

    public BasePay(Integer payType, String outTradeNo, String ip, Integer money) {
        this.payType = payType;
        this.outTradeNo = outTradeNo;
        this.ip = ip;
        this.money = money;
    }

    public BasePay() {
    }

    //微信公众号支付必须传的数据
    private String openId;
    //必须传的数据
    private Integer payType;
    //必须传的数据
    private String outTradeNo;
    //必须传的数据
    private String ip;
    //必须传的数据
    private Integer money;
    //非必需
    private String tradeNo;
    private Integer state;
    private String causes;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCauses() {
        return causes;
    }

    public void setCauses(String causes) {
        this.causes = causes;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
