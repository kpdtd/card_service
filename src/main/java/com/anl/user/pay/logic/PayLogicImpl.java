package com.anl.user.pay.logic;

import com.anl.user.constant.OrderState;
import com.anl.user.constant.PayType;
import com.anl.user.dto.LogicResult;
import com.anl.user.pay.po.BasePay;
import com.anl.user.pay.wxpay.WxpayBuilder;
import com.anl.user.pay.wxpay.WxpayConfig;
import com.anl.user.pay.wxpay.WxpayHelper;
import com.anl.user.pay.wxpay.WxpayUtil;
import com.anl.user.persistence.po.DataDictionary;
import com.anl.user.service.DataDictionaryService;
import com.anl.user.util.JsonHelper;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PayLogicImpl implements PayLogic {


    @Autowired
    DataDictionaryService dataDictionaryService;

    /**
     * 支付总入口
     * @param userOrder
     * @return
     * @throws Exception
     */
    @Override
    public LogicResult entrancePayLogic(BasePay userOrder) throws Exception {
            return wxPayLogic(userOrder);
    }
    /**
     * 微信订单逻辑
     * 1、封装业务代码
     * 2、通过WxpayBuilder.buildRequestPara方法出去空值和添加签名
     * 3、将Map转换成xml
     * 4、调用WxpayUtil.postXmlClient(URL, xmlParams)将转换的xmlParams数据发送到指定URL中，并获取返回值
     * 5、将返回值转换成Map，进行判断return_code、验证签名、判断result_code，判断都通过则获取prepay_id
     * 6、将客户端需要的数据封装好并返回
     *
     * @param userOrder
     * @throws Exception
     */
    private LogicResult wxPayLogic(BasePay userOrder) throws Exception {
        // goodsPriceSetting(chargeRecord, userOrder);
        // 微信封装业务代码
        Map<String, String> xmlData;
        //判断支付方式
        if (userOrder.getPayType() == PayType.WX_JSAPI_PAY) {
            //公众号支付
            xmlData = getPackageParameters(userOrder, WxpayConfig.TRADE_All_TPRE);
        } else {
//            return null;
            return LogicResult.fail(null);
        }

        // 添加签名
        Map<String, String> result = WxpayBuilder.buildRequestPara(xmlData);
        // Map<String, String> result = WxpayHelper.paraFilter(xmlData);
        String xmlParams = WxpayUtil.parseToXml(result);
        LogFactory.getInstance().getLogger().debug("微信支付请求报文:" + xmlParams);
        String xmlResult = WxpayUtil.postXmlClient(WxpayConfig.PREPAY_URL, xmlParams);
        Map<String, String> map = WxpayUtil.parseToMap(xmlResult);
        LogFactory.getInstance().getLogger().debug("微信支付返回报文:" + JsonHelper.toJson(map));

        // return_code返回失败
        if (!WxpayConfig.SUCCESS.equals(map.get("return_code"))) {
            // return_code返回失败
            String return_msg = map.get("return_msg");
            LogFactory.getInstance().getLogger().error("微信支付返回失败：返回消息为：" + return_msg);
            return LogicResult.fail(return_msg);
        }

        // 除去签名和空值
        Map<String, String> paraFilter = WxpayHelper.paraFilter(map);
        // 获得签名
        String mysign = WxpayBuilder.buildRequestMysign(paraFilter);

        // 签名验证失败
        if (!mysign.equals(map.get("sign"))) {
            LogFactory.getInstance().getLogger().error("微信支付验证签名失败");
            return LogicResult.fail("签名失败");
        }
        // result_code返回失败
        if (!WxpayConfig.SUCCESS.equals(map.get("result_code"))) {
            String err_code = map.get("err_code");
            String err_code_des = map.get("err_code_des");
            userOrder.setCauses(err_code + err_code_des);
            LogFactory.getInstance().getLogger().error("微信支付结果失败:失败码为" + err_code + ",失败原因是" + err_code_des);
            userOrder.setState(OrderState.PAYMENT_FAIL);

        } else {
            //返回成功
            String prepay_id = map.get("prepay_id");
            // 将微信生成的订单id写到这里
            userOrder.setOpenId(prepay_id);
        }
        // 微信支付参数封装
        Map<String, String> mapBack = new HashMap<>();
        if (userOrder.getPayType() == PayType.WX_JSAPI_PAY) {
            mapBack.put("appid", WxpayConfig.WX_PUBLIC_APP_ID);
        }
        mapBack.put("partnerid", WxpayConfig.PARTNER);
        mapBack.put("prepayid", userOrder.getOpenId());
        mapBack.put("package", WxpayConfig.PACKAGE);
        mapBack.put("noncestr", WxpayUtil.getNonceStr());
        mapBack.put("timestamp", WxpayUtil.getTimeStamp());
        // return WxpayBuilder.buildRequestPara(mapBack);
        return LogicResult.success(WxpayBuilder.buildRequestPara(mapBack));
    }

    /**
     * 转换数据包
     * @param userOrder
     * @param tradeQrType
     * @return
     */
    private Map<String, String> getPackageParameters(BasePay userOrder, String tradeQrType) {
        Map<String, String> xmlData = new HashMap<String, String>();

        if ("JSAPI".equals(tradeQrType)) {
            xmlData.put("mch_id", WxpayConfig.WX_PUBLIC_MCHID);
            xmlData.put("appid", WxpayConfig.WX_PUBLIC_APP_ID);
            xmlData.put("openid", userOrder.getOpenId());

        }
        xmlData.put("nonce_str", WxpayUtil.getNonceStr());
        xmlData.put("out_trade_no", userOrder.getOutTradeNo());
        xmlData.put("total_fee", userOrder.getMoney().toString());
        xmlData.put("spbill_create_ip", userOrder.getIp());
        // 从数据字典里面去主机地址
        DataDictionary dataDictionary = dataDictionaryService.getDicByKey("PAY_CALLBACK");
        xmlData.put("notify_url", dataDictionary.getValue() + WxpayConfig.NOTIFY_URL);
        xmlData.put("trade_type", tradeQrType);
        xmlData.put("body", dataDictionaryService.getDicByKey("WXPAY_BODY").getValue());
        return xmlData;
    }

}
