package com.anl.user.cucumber;

import com.anl.user.persistence.po.Card;
import com.anl.user.persistence.po.ChargeList;
import com.anl.user.persistence.po.User;
import com.anl.user.persistence.po.UserChargeRecord;
import com.anl.user.util.HttpClient;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MockClient {
    private  String iccid;
    private Card card;
    private User user;
    private UserChargeRecord userChargeRecord;//订单
    private ChargeList chargeList;//充值列表
    private  String payXml;//模拟支付回传的数据
    private  Integer taskId;

    String BaseUri = "http://127.0.0.1:8081/card_server/";

    public String wxPayCallBack(String xml) throws IOException {
        String url = BaseUri + "anl/wxPayCallBack";
        return HttpClient.postRequest(url,xml);
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserChargeRecord getUserChargeRecord() {
        return userChargeRecord;
    }

    public void setUserChargeRecord(UserChargeRecord userChargeRecord) {
        this.userChargeRecord = userChargeRecord;
    }

    public String getBaseUri() {
        return BaseUri;
    }

    public void setBaseUri(String baseUri) {
        BaseUri = baseUri;
    }

    public ChargeList getChargeList() {
        return chargeList;
    }

    public void setChargeList(ChargeList chargeList) {
        this.chargeList = chargeList;
    }

    public String getPayXml() {
        return payXml;
    }

    public void setPayXml(String payXml) {
        this.payXml = payXml;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
