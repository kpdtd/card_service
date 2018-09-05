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
import com.anl.user.persistence.po.ChargeList;
import com.anl.user.persistence.po.UserChargeRecord;
import com.anl.user.service.ChargeListService;
import com.anl.user.service.UserChargeRecordService;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
        LogFactory.getInstance().getLogger().debug("写入订单表:" + userChargeRecord.getId());
        // 写入订单表
        ActionResult actionResult = ActionResult.success(dataMap);
        return actionResult;
    }
}
