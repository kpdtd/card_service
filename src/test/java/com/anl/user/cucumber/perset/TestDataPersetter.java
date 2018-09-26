package com.anl.user.cucumber.perset;

import com.anl.user.constant.ActivityCardType;
import com.anl.user.constant.PayType;
import com.anl.user.cucumber.MockClient;
import com.anl.user.dto.LogicResult;
import com.anl.user.logic.UserChargeRecordLogicImpl;
import com.anl.user.pay.logic.PayLogicImpl;
import com.anl.user.pay.wxpay.WxpayBuilder;
import com.anl.user.pay.wxpay.WxpayUtil;
import com.anl.user.persistence.mapper.*;
import com.anl.user.persistence.po.*;
import com.anl.user.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.anl.user.pay.wxpay.WxpayHelper.paraFilter;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Component
public class TestDataPersetter extends BasePersetter {

    @Autowired
    CardMapper cardMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    MockClient mockClient;
    @Autowired
    UserAccountMapper userAccountMapper;
    @Autowired
    ChargeListMapper chargeListMapper;
    @Autowired
    UserChargeRecordMapper userChargeRecordMapper;
    @Autowired
    ActivityCardInfoMapper activityCardInfoMapper;
    @Autowired
    PayLogicImpl payLogic;
    @Autowired
    UserChargeRecordLogicImpl userChargeRecordLogic;
    @Autowired
    PlanDefinitionMapper planDefinitionMapper;
    @Autowired
    AutoTaskDefinitionMapper autoTaskDefinitionMapper;


    //卡设置
    public Card setCard(String iccid) throws Exception {
        Card card = new Card();
        card.setIccid(iccid);
        List<Card> cards = cardMapper.getListByPo(card);
        if (CollectionUtils.isEmpty(cards)) {
            card.setSupplierId(19);
            card.setMsisdn("1440034217223");
            card.setImsi("460042342107224");
            card.setCardState(3);
            card.setGprsState(1);
            card.setOperator(1);
            card.setCreateTime(new Date());
            card.setUpdateTime(new Date());
            cardMapper.insert(card);
        } else {
            card = cards.get(0);
        }
        return card;
    }

    //设置用户
    public User setUser(String phone, String name, Card card) throws Exception {
        User user = new User();
        if (StringUtils.isNotBlank(phone)) {
            user.setPhone(phone);
        }
        user.setUsername(name);
        if (card != null) {
            user.setCardId(card.getId());
            user.setIccid(card.getIccid());
            user.setIndentity(card.getIccid());
        }
        List<User> users = userMapper.getListByPo(user);
        if (CollectionUtils.isEmpty(users)) {
            user.setState(1);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userMapper.insert(user);
        } else {
            user = users.get(0);
        }
        return user;
    }

    //修改用户信息
    public User updateUser(User user) throws Exception {
        userMapper.update(user);
        return user;
    }

    //获取账户信息
    public UserAccount getUserAccount(Integer primaryAccount, Integer subAccount) throws Exception {
        UserAccount userAccount = null;
        User user = mockClient.getUser();

        userAccount = new UserAccount();
        userAccount.setUserId(user.getId());
        List<UserAccount> userAccounts = userAccountMapper.getListByPo(userAccount);
        //如果不存在账户,现在就创建一个
        if (CollectionUtils.isEmpty(userAccounts)) {
            userAccount.setCreateTime(new Date());
            userAccount.setUpdateTime(new Date());
            userAccount.setPrimaryAccount(0);
            userAccount.setSubAccount(0);
            userAccountMapper.insert(userAccount);
        } else {
            userAccount = userAccounts.get(0);
            if (primaryAccount != null) {
                userAccount.setPrimaryAccount(primaryAccount);
            }
            if (subAccount != null) {
                userAccount.setSubAccount(subAccount);
            }
            userAccountMapper.update(userAccount);
        }
        return userAccount;
    }

    //是否存在该金额的充值列表
    public ChargeList setChargeList(Integer money) throws Exception {

        ChargeList chargeList = new ChargeList();
        chargeList.setMoney(money * 100);//转化成分
        List<ChargeList> chargeLists = chargeListMapper.getListByPo(chargeList);
        if (CollectionUtils.isEmpty(chargeLists)) {
            chargeList.setUpdateTime(new Date());
            chargeList.setCreateTime(new Date());
            chargeList.setTitle("10元");
            chargeListMapper.insert(chargeList);
        } else {
            chargeList = chargeLists.get(0);
        }
        return chargeList;
    }

    //增加流量卡信息
    public ActivityCardInfo setActivityCardInfo(String userName, int payState) throws Exception {
        ActivityCardInfo activityCardInfo = new ActivityCardInfo();
        activityCardInfo.setName(userName);
        List<ActivityCardInfo> activityCardInfos = activityCardInfoMapper.getListByPo(activityCardInfo);
        if (CollectionUtils.isEmpty(activityCardInfos)) {
            activityCardInfo.setCreateTime(new Date());
            activityCardInfo.setUpdateTime(new Date());
            activityCardInfo.setCardState(1);
            activityCardInfo.setAddress("test地址");
            activityCardInfo.setValidDays(0);
            activityCardInfo.setPrice(900);
            activityCardInfo.setSort(5);
            activityCardInfo.setUrlPageId(19);
            activityCardInfo.setState(1);
            activityCardInfo.setMobile("13811155196");
            activityCardInfo.setPayState(ActivityCardType.ONLINE_NOPAY);
            activityCardInfoMapper.insert(activityCardInfo);
        } else {
            activityCardInfo = activityCardInfos.get(0);
            if (payState > 0) {
                activityCardInfo.setPayState(payState);
                activityCardInfoMapper.update(activityCardInfo);
            }
        }
        return activityCardInfo;
    }

    //生成订单
    public UserChargeRecord setUserChargeRecord(String outTradeNo, String money, String orderType) throws Exception {
        // MockitoAnnotations.initMocks(this);
        UserChargeRecord userChargeRecord = new UserChargeRecord(PayType.WX_JSAPI_PAY, outTradeNo, "192.168.1.1", Integer.parseInt(money) * 100);
        userChargeRecord.setOutTradeNo(outTradeNo);
        List<UserChargeRecord> userChargeRecords = userChargeRecordMapper.getListByPo(userChargeRecord);
        if (CollectionUtils.isNotEmpty(userChargeRecords)) {
            UserChargeRecord order = userChargeRecords.get(0);
            if (order.getId() != null && order.getId() > 0) {
                userChargeRecordMapper.deleteById(order.getId());
            }
        }
        //参考wxPublicActivityCardInfoCreateOrder
        userChargeRecord.setOrderType(Integer.parseInt(orderType));
        if ("1".equals(orderType)) {
            //为流量卡充值
            userChargeRecord.setChargeListId(mockClient.getChargeList().getId());
            userChargeRecord.setUserId(mockClient.getUser().getId());
            userChargeRecord.setIccid(mockClient.getCard().getIccid());
        } else {
            //购买流量卡支付
            ActivityCardInfo activityCardInfo = setActivityCardInfo(mockClient.getUser().getUsername(), ActivityCardType.ONLINE_NOPAY);
            userChargeRecord.setChargeListId(activityCardInfo.getId());
            userChargeRecord.setUserId(0);
            userChargeRecord.setIccid("0");
        }
        userChargeRecord.setOpenId("oH-4a0-CJ64ZLyXOw2Wazv7SLBBw");
        payLogic = mock(PayLogicImpl.class);
        userChargeRecordLogic.setPayLogic(payLogic);
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", DateUtil.getCurDateTime());
        map.put("prepayid", "test");
        when(payLogic.entrancePayLogic(any())).thenReturn(LogicResult.success(map));
        userChargeRecordLogic.creatOrder(userChargeRecord);

        return userChargeRecord;
    }

    //封装回传的支付报文
    public String getPayXml(String outTradeNo) throws Exception {
        UserChargeRecord userOrder = mockClient.getUserChargeRecord();
        String xml = "<xml><appid><![CDATA[wx42b39c1baf0f4c20]]><" +
                "/appid>" +
                "<bank_type><![CDATA[CMB_DEBIT]]></bank_type>" +
                "<cash_fee><![CDATA[5000]]></cash_fee>" +
                "<fee_type><![CDATA[CNY]]></fee_type>" +
                "<is_subscribe><![CDATA[Y]]></is_subscribe>" +
                "<mch_id><![CDATA[1500048162]]></mch_id>" +
                "<nonce_str><![CDATA[9bb6dee73b8b0ca97466ccb24fff3139]]></nonce_str>" +
                "<openid><![CDATA[ofRaj0_YpejzgLF-_K4ZqPI9zdt0]]></openid>" +
                "<out_trade_no><![CDATA[" + outTradeNo + "]]></out_trade_no>" +
                "<result_code><![CDATA[SUCCESS]]></result_code>" +
                "<return_code><![CDATA[SUCCESS]]></return_code>" +
                "<sign><![CDATA[4C54FC913AFF3C135B26805595891898]]></sign>" +
                "<time_end><![CDATA[20180814081621]]></time_end>" +
                "<total_fee>" + userOrder.getMoney() + "</total_fee>" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>" +
                "<transaction_id><![CDATA[4200000150201808148906010542]]></transaction_id>" +
                "</xml>";
        Map<String, String> map = WxpayUtil.parseToMap(xml);
        String sign = WxpayBuilder.buildRequestMysign(paraFilter(map));
        //替换掉xml中sign的部分
        xml = xml.replace("4C54FC913AFF3C135B26805595891898", sign);
        return xml;
    }

    //获取订单信息
    public UserChargeRecord getUserOrder(String outTradeNo) throws Exception {
        UserChargeRecord userOrder = new UserChargeRecord();
        userOrder.setOutTradeNo(outTradeNo);
        List<UserChargeRecord> userOrders = userChargeRecordMapper.getListByPo(userOrder);
        if (CollectionUtils.isNotEmpty(userOrders)) {
            userOrder = userOrders.get(0);
            return userOrder;
        }
        return null;
    }

    //设置套餐信息
    public PlanDefinition getPlanDefinition(int money, int unitMoney, int unit) throws Exception {
        PlanDefinition planDefinition = new PlanDefinition();
        planDefinition.setMonthlyPlanPrice(money * 100);
        planDefinition.setFlowUnit(unit);
        planDefinition.setFlowUnitPrice(unitMoney * 100);
        List<PlanDefinition> planDefinitions = planDefinitionMapper.getListByPo(planDefinition);
        if (CollectionUtils.isNotEmpty(planDefinitions)) {
            planDefinition = planDefinitions.get(0);
        } else {
            planDefinition.setCode("e10");
            planDefinition.setName("e10");
            planDefinition.setCreateTime(new Date());
            planDefinition.setUpdateTime(new Date());
            planDefinition.setState(1);
            planDefinition.setEffectiveTime(1);
            planDefinition.setDisplaySort(100);
            planDefinitionMapper.insert(planDefinition);
        }
        return planDefinition;
    }
    //任务

    public AutoTaskDefinition getAutoTaskDefinition(int taskId, int state) throws Exception {
        AutoTaskDefinition autoTaskDefinition = autoTaskDefinitionMapper.getById(taskId);
        if (autoTaskDefinition == null) {
            autoTaskDefinition = new AutoTaskDefinition();
            autoTaskDefinition.setExecuteState(state);
            autoTaskDefinition.setId(taskId);
            autoTaskDefinitionMapper.insert(autoTaskDefinition);
        }
        if (state > 0 && autoTaskDefinition.getExecuteState() != state) {
            autoTaskDefinition.setExecuteState(state);
            autoTaskDefinitionMapper.update(autoTaskDefinition);
        }
        return autoTaskDefinition;
    }
}
