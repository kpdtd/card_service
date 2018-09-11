package com.anl.user.logic;

import com.anl.user.constant.LogicResultCode;
import com.anl.user.constant.OrderState;
import com.anl.user.constant.OrderType;
import com.anl.user.constant.SystemErrorCode;
import com.anl.user.dto.ActionResult;
import com.anl.user.dto.LogicResult;
import com.anl.user.pay.logic.PayLogic;
import com.anl.user.pay.wxpay.WxpayBuilder;
import com.anl.user.pay.wxpay.WxpayConfig;
import com.anl.user.persistence.po.ActivityCardInfo;
import com.anl.user.persistence.po.ChargeList;
import com.anl.user.persistence.po.UserChargeRecord;
import com.anl.user.service.ActivityCardInfoService;
import com.anl.user.service.ChargeListService;
import com.anl.user.service.UserChargeRecordService;
import com.anl.user.util.LogFactory;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyiqiang on 2018/9/4.
 */
@Service
public class UserChargeRecordLogicImpl implements UserChargeRecordLogic {
    @Autowired
    ChargeListService chargeListService;
    @Autowired
    PayLogic payLogic;
    @Autowired
    UserChargeRecordService userChargeRecordService;
    @Autowired
    ActivityCardInfoService activityCardInfoService;

    /**
     * 创建支付订单,购买卡或者为流量卡充值
     *
     * @param userChargeRecord
     * @return
     * @throws Exception
     */
    @Override
    public ActionResult creatOrder(UserChargeRecord userChargeRecord) throws Exception {

        //如果是充值订单类型,需要获取价格对象
        ChargeList chargeList = null;
        if (userChargeRecord.getOrderType() != null && OrderType.FLOW_RECHARGE == userChargeRecord.getOrderType()) {
            chargeList = chargeListService.getById(userChargeRecord.getChargeListId());
            if (chargeList == null) {
                return ActionResult.fail();
            }
        }
        LogicResult logicResult = payLogic.entrancePayLogic(userChargeRecord);
        Map<String, Object> dataMap = new HashMap<>();
        if (logicResult.getCode() == LogicResultCode.ERROR) {
            return ActionResult.fail(SystemErrorCode.payError.getCode());
        } else {
            Map<String, String> map = (HashMap) logicResult.getData();
            Map<String, String> mapTmp = new HashMap<>();
            mapTmp.put("appId", WxpayConfig.WX_PUBLIC_APP_ID);
            mapTmp.put("timeStamp", map.get("timestamp"));
            mapTmp.put("nonceStr", userChargeRecord.getOutTradeNo());
            mapTmp.put("package", "prepay_id=" + map.get("prepayid"));
            mapTmp.put("signType", "MD5");
            //除去数组中的空值和签名参数
            dataMap = (HashMap) WxpayBuilder.wxPubBuildRequestPara(mapTmp);
        }
        userChargeRecord.setState(OrderState.PENDING_PAYMENT);//待支付
        userChargeRecordService.insert(userChargeRecord);
        //如果是购买流量卡的支付订单,回写订单号到t_activity_card_info
        if (OrderType.BUY_FLOW_CARD == userChargeRecord.getOrderType()) {
            ActivityCardInfo activityCardInfo = activityCardInfoService.getById(userChargeRecord.getChargeListId());
            activityCardInfo.setExpressMsgId(userChargeRecord.getId());
            activityCardInfoService.update(activityCardInfo);
        }
        LogFactory.getInstance().getLogger().debug("写入订单表:" + userChargeRecord.getId());
        // 写入订单表
        ActionResult actionResult = ActionResult.success(dataMap);
        return actionResult;
    }

    //更新订单状态
    @Override
    public UserChargeRecord updateUserChargeRecordState(String outTradeNo, int state) throws Exception {
        Map<String, Object> model = new HashMap<>();
        // 获取到订单号，根据订单号获取到对应的订单
        model.put("outTradeNo", outTradeNo);
        List<UserChargeRecord> chargeRecords = userChargeRecordService.getListByMap(model);
        UserChargeRecord userChargeRecord = null;
        if (CollectionUtils.isNotEmpty(chargeRecords)) {
            userChargeRecord = chargeRecords.get(0);
            if (userChargeRecord.getState() > state) {
                userChargeRecord.setState(state);
            }
            userChargeRecord.setUpdateTime(new Date());
        }
        return userChargeRecord;
    }
}
