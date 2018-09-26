package com.anl.user.cucumber;

import com.anl.user.constant.ActivityCardType;
import com.anl.user.cucumber.perset.TestDataPersetter;
import com.anl.user.persistence.po.*;
import com.anl.user.task.DayFlowSearchThread;
import com.anl.user.util.DateUtil;
import com.sup.pack.dto.FlowDailyData;
import com.sup.pack.logic.CardFlowDayUsedLogicImpl;
import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import org.junit.Assert;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by yangyiqiang on 2018/6/25.
 */
public class ClientSteps extends BaseSteps {
    @Autowired
    MockClient mockClient;
    @Autowired
    TestDataPersetter testDataPersetter;
    @Autowired
    CardFlowDayUsedLogicImpl cardFlowDayUsedLogic;
    @Autowired
    DayFlowSearchThread dayFlowSearchThread;

    @假如("^用户\"(.*?)\"存在一张iccid为\"(.*?)\"的大象卡$")
    public void 用户_存在一张iccid为_的大象卡(String userName, String iccid) throws Throwable {
        mockClient.setIccid(iccid);
        Card card = testDataPersetter.setCard(iccid);
        mockClient.setCard(card);
        //生成预用户
        User user = testDataPersetter.setUser(null, userName, card);
        mockClient.setUser(user);
    }

    @假如("^使用手机号\"(.*?)\"进行了实名认证$")
    public void 使用手机号_进行了实名认证(String phone) throws Throwable {
        //将user修改为试用期用户
        User user = mockClient.getUser();
        user.setPhone(phone);
        user.setState(2);
        user.setUpdateTime(new Date());
        testDataPersetter.updateUser(user);
        mockClient.setUser(user);
    }

    @假如("^该卡对应的账户信息主账户金额为\"(.*?)\"$")
    public void 该卡对应的账户信息主账户金额为(String arg1) throws Throwable {
        UserAccount userAccount = testDataPersetter.getUserAccount(Integer.parseInt(arg1),null);
    }

    @假如("^服务器已经配置了金额为\"(.*?)\"元的充值列表$")
    public void 服务器已经配置了金额为_元的充值列表(String arg1) throws Throwable {
        ChargeList chargeList = testDataPersetter.setChargeList(Integer.parseInt(arg1));
        mockClient.setChargeList(chargeList);
    }

    @假如("^生成订单号为\"(.*?)\"的订单,支付金额为\"(.*?)\"元,订单类型为\"(.*?)\"$")
    public void 生成订单号为_的订单_支付金额为_元_订单类型为(String arg1, String arg2, String arg3) throws Throwable {
        UserChargeRecord userChargeRecord = testDataPersetter.setUserChargeRecord(arg1, arg2, arg3);
        mockClient.setUserChargeRecord(userChargeRecord);
    }

    @假如("^服务器成功接收到微信公众号支付回传信息$")
    public void 服务器成功接收到微信公众号支付回传信息() throws Throwable {

    }

    @假如("^服务器接收到订单为\"(.*?)\"的支付报文$")
    public void 服务器接收到订单为_的支付报文(String arg1) throws Throwable {
        mockClient.setPayXml(testDataPersetter.getPayXml(arg1));
    }

    @当("^访问服务器\"(.*?)\"回传接口时$")
    public void 访问服务器_回传接口时(String arg1) throws Throwable {

        mockClient.wxPayCallBack(mockClient.getPayXml());

    }

    @那么("^订单\"(.*?)\"的状态应该为\"(.*?)\"$")
    public void 订单_的状态应该为(String arg1, String arg2) throws Throwable {
        UserChargeRecord userOrder = testDataPersetter.getUserOrder(arg1);
        Assert.assertEquals(userOrder.getState() + "", arg2);
    }

    @那么("^该卡对应的用户账户主账户金额应该为\"(.*?)\"分$")
    public void 该卡对应的用户账户主账户金额应该为_分(String arg1) throws Throwable {
        UserAccount userAccount = testDataPersetter.getUserAccount(null, null);
        Assert.assertEquals(userAccount.getPrimaryAccount() + "", (Integer.parseInt(arg1)) + "");
    }

    @假如("^用户\"(.*?)\"在公众号申请了一张大象卡$")
    public void 用户_在公众号申请了一张大象卡(String arg1) throws Throwable {
        //testDataPersetter.setUser("",arg1,null);
        User user = new User();
        user.setUsername(arg1);
        mockClient.setUser(user);
    }

    @假如("^填写了地址电话等订单信息$")
    public void 填写了地址电话等订单信息() throws Throwable {
        testDataPersetter.setActivityCardInfo(mockClient.getUser().getUsername(), ActivityCardType.ONLINE_NOPAY);
    }

    @那么("^订单\"(.*?)\"的userId应该不为空$")
    public void 订单_的userId应该不为空(String arg1) throws Throwable {
        UserChargeRecord userOrder = testDataPersetter.getUserOrder(arg1);
        Assert.assertNotNull(userOrder.getUserId());
    }

    @那么("^该订单对应的t_activity_card_info的状态应该为\"(.*?)\"$")
    public void 该订单对应的t_activity_card_info的状态应该为(String arg1) throws Throwable {
        ActivityCardInfo activityCardInfo = testDataPersetter.setActivityCardInfo(mockClient.getUser().getUsername(), 0);
        Assert.assertEquals(arg1, activityCardInfo.getState() + "");
    }

    @那么("^该订单对应的t_activity_card_info的支付状态应该为\"(.*?)\"$")
    public void 该订单对应的t_activity_card_info的支付状态应该为(String arg1) throws Throwable {
        ActivityCardInfo activityCardInfo = testDataPersetter.setActivityCardInfo(mockClient.getUser().getUsername(), 0);
        Assert.assertEquals(arg1, activityCardInfo.getPayState() + "");
    }

    @那么("^存在对应的状态为\"(.*?)\"的用户信息$")
    public void 存在对应的状态为_的用户信息(String arg1) throws Throwable {
        User user = testDataPersetter.setUser("", mockClient.getUser().getUsername(), null);
        Assert.assertEquals(arg1, user.getState() + "");
    }

    @假如("^该卡存在当月的套餐信息为月租\"(.*?)\"元,日租宝为\"(.*?)\"元\"(.*?)\"M$")
    public void 该卡存在当月的套餐信息为月租_元_日租宝为_元_M(String arg1, String arg2, String arg3) throws Throwable {
        testDataPersetter.getPlanDefinition(Integer.parseInt(arg1), Integer.parseInt(arg2), Integer.parseInt(arg3));
    }

    @假如("^存在任务ID为\"(.*?)\",状态为\"(.*?)\"的任务定义$")
    public void 存在任务id为_状态为_的任务定义(String arg1, String arg2) throws Throwable {
       AutoTaskDefinition autoTaskDefinition= testDataPersetter.getAutoTaskDefinition(Integer.parseInt(arg1), Integer.parseInt(arg2));
        mockClient.setTaskId(autoTaskDefinition.getId());
    }

    @假如("^服务器执行日流量查询任务调用成功$")
    public void 服务器执行日流量查询任务调用成功() throws Throwable {

    }

    @假如("^该卡的日流量使用量为\"(.*?)\"M$")
    public void 该卡的日流量使用量为_M(String arg1) throws Throwable {
        MockitoAnnotations.initMocks(this);
        cardFlowDayUsedLogic = mock(CardFlowDayUsedLogicImpl.class);
        FlowDailyData flowDailyData = new FlowDailyData();
        flowDailyData.setCardId(mockClient.getCard().getId());
        flowDailyData.setDay(DateUtil.dateToString(DateUtil.afterNDaysDate(new Date(), -1), "yyyyMMdd"));
        flowDailyData.setMonth(DateUtil.getCurDateTime("yyyyMM"));
        flowDailyData.setDayUsedFlow(Integer.parseInt(arg1));
        flowDailyData.setMonthUsedFlow(Integer.parseInt(arg1));
        when(cardFlowDayUsedLogic.deal(any())).thenReturn(flowDailyData);
        dayFlowSearchThread.excute(mockClient.getTaskId());
    }
    @假如("^该用户的状态为\"(.*?)\"$")
    public void 该用户的状态为(String arg1) throws Throwable {
        User user=mockClient.getUser();
        user.setState(Integer.parseInt(arg1));
        testDataPersetter.updateUser(user);
        mockClient.setUser(user);
    }
}
