package com.anl.user.controller;

import com.anl.user.constant.OrderState;
import com.anl.user.event.payCallBack.PayCallBackEvent;
import com.anl.user.logic.UserChargeRecordLogic;
import com.anl.user.pay.wxpay.WxpayConfig;
import com.anl.user.pay.wxpay.WxpayHelper;
import com.anl.user.pay.wxpay.WxpayUtil;
import com.anl.user.persistence.po.UserChargeRecord;
import com.anl.user.service.UserChargeRecordService;
import com.anl.user.util.LogFactory;
import com.anl.user.util.SeqIdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping(value = "/anl")
public class WxPayCallBackController extends BaseController {
    @Autowired
    UserChargeRecordService userChargeRecordService;
    @Autowired
    UserChargeRecordLogic userChargeRecordLogic;
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 微信回传验证逻辑流程
     * 1、验证return_code,如果是success，则继续执行;
     * 2、验证签名，签名正确则继续执行;
     * 3、验证result_code 如果success，则表示付款成功，继续执行;
     * 4、根据微信回传的订单号，在订单表中查出相应的订单消息，如果订单查出且状态正常，则可表示订单付款成功;
     * 5、将设置支付成功事件;
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/wxPayCallBack", method = RequestMethod.POST)
    @ResponseBody
    public String activtyWxPayCallBack(@RequestBody String body) {
        MDC.put("seqID", SeqIdGenerator.generate());// 日志序列
        LogFactory.getInstance().getLogger().debug("activtyWxPayCallBack接收到微信的异步支付结果通知信息data=" + body);
        LogFactory.getInstance().getPayCallbackLogger().info("activtyWxPayCallBack接收到微信的异步支付结果通知信息data=" + body);
        Map<String, String> resultMap = new HashMap<>();
        if (StringUtils.isNotBlank(body)) {
            try {
                Map<String, String> map = WxpayUtil.parseToMap(body);
                // 返回代码，判断是否返回的成功
                if (!WxpayConfig.SUCCESS.equals(map.get("return_code"))) {
                    // 返回结果失败
                    LogFactory.getInstance().getLogger().error("activtyWxPayCallBack接收到微信的异步支付返回结果失败");
                    LogFactory.getInstance().getPayCallbackLogger().error("activtyWxPayCallBack接收到微信的异步支付返回结果失败");
                    resultMap.put("return_code", WxpayConfig.FAIL);
                    resultMap.put("return_msg", "参数格式校验错误");
                    return WxpayUtil.parseToXml(resultMap);
                }
                // 验证签名
                if (!WxpayHelper.verify(map)) {
                    // 签名失败
                    LogFactory.getInstance().getLogger().error("activtyWxPayCallBack接收到微信的异步支付签名失败");
                    LogFactory.getInstance().getPayCallbackLogger().error("activtyWxPayCallBack接收到微信的异步支付签名失败");
                    resultMap.put("return_code", WxpayConfig.FAIL);
                    resultMap.put("return_msg", "签名失败");
                    return WxpayUtil.parseToXml(resultMap);
                }
                String outTradeNo = map.get("out_trade_no");
                LogFactory.getInstance().getLogger().info("activtyWxPayCallBack支付成功的订单号为:{}", outTradeNo);
                // 业务结果失败
                if (!WxpayConfig.SUCCESS.equals(map.get("result_code"))) {
                    LogFactory.getInstance().getLogger().error("activtyWxPayCallBack接收到微信的异步支付业务结果失败,订单号为:{}", outTradeNo);
                    userChargeRecordLogic.updateUserChargeRecordState(outTradeNo, OrderState.PAYMENT_FAIL);
                    resultMap.put("return_code", WxpayConfig.SUCCESS); // 只要接收到微信返回的状态码，无论失败，成功，都是success，表示接收到状态码
                    return WxpayUtil.parseToXml(resultMap);
                }
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("outTradeNo", outTradeNo);
                List<UserChargeRecord> userOrderList = userChargeRecordService.getListByMap(dataMap);
                Optional<UserChargeRecord> optionalOrder = userOrderList.stream().findFirst();
                if (!optionalOrder.isPresent()) {
                    resultMap.put("return_code", WxpayConfig.FAIL);
                    resultMap.put("return_msg", "订单号不存在");
                    LogFactory.getInstance().getLogger().info("activtyWxPayCallBack接收到微信的异步支付结果通知错误，信息订单号不存在，订单号为:{}", outTradeNo);
                }


                UserChargeRecord userChargeRecord = optionalOrder.get();
                if (userChargeRecord.getState() == OrderState.PENDING_PAYMENT || userChargeRecord.getState() == OrderState.CANCEL) {
                    // 设置支付类型，1为支付宝，2为微信，
                    map.put("payType", userChargeRecord.getPayType() + "");
                    // 成功，返回
                    resultMap.put("return_code", WxpayConfig.SUCCESS);

                    //更改订单状态和支付状态,支付成功
                    userChargeRecordLogic.updateUserChargeRecordState(outTradeNo, OrderState.PAYMENT_SUCCESS);
                    LogFactory.getInstance().getLogger().debug("activtyWxPayCallBack微信支付结果回传成功，修改订单状态为支付成功，订单号为:{}", outTradeNo);
                    // 订单回调成功，调用订单完成监听
                    applicationContext.publishEvent(new PayCallBackEvent(map));
                    LogFactory.getInstance().getLogger().debug("activtyWxPayCallBack微信支付结果回传成功，调用监听执行完成，订单号为:{}", outTradeNo);
                }

            } catch (Exception e) {
                e.printStackTrace();
                LogFactory.getInstance().getLogger().error(body + e.getMessage());
                LogFactory.getInstance().getErrorLogger().error(body + e.getMessage());
            }
        } else {
            // 返回结果为空
            LogFactory.getInstance().getPayCallbackLogger().error("参数格式校验错误");
            resultMap.put("return_code", WxpayConfig.FAIL);
            resultMap.put("return_msg", "参数格式校验错误");
        }
        return WxpayUtil.parseToXml(resultMap);
    }
}
