package com.anl.user.pay.logic;


import com.anl.user.account.logic.UserAccountLogic;
import com.anl.user.constant.OrderState;
import com.anl.user.constant.PayType;
import com.anl.user.constant.RefundRecordType;
import com.anl.user.pay.wxpay.WxpayBuilder;
import com.anl.user.pay.wxpay.WxpayConfig;
import com.anl.user.pay.wxpay.WxpayUtil;
import com.anl.user.persistence.po.DataDictionary;
import com.anl.user.persistence.po.UserChargeRecord;
import com.anl.user.persistence.po.UserRefundRecord;
import com.anl.user.service.DataDictionaryService;
import com.anl.user.service.UserChargeRecordService;
import com.anl.user.service.UserRefundRecordService;
import com.anl.user.util.JsonHelper;
import com.anl.user.util.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.util.*;

@Service
public class UserRefundRecordLogicImpl implements UserRefundRecordLogic {

    @Autowired
    DataDictionaryService dataDictionaryService;
    @Autowired
    UserRefundRecordService userRefundRecordService;
    @Autowired
    UserChargeRecordService userChargeRecordService;
    @Autowired
    UserAccountLogic userAccountLogic;
    /**
     * 总退款入口
     * @param outTradeNo
     * @param money
     * @return
     * @throws Exception
     */
    @Override
    public Boolean Refunds(String outTradeNo, Integer money) throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("outTradeNo", outTradeNo);
        List<UserChargeRecord> userChargeRecords = userChargeRecordService.getListByMap(dataMap);
        Optional<UserChargeRecord> optionalOrder = userChargeRecords.stream().findFirst();
        if (!optionalOrder.isPresent()) {
            LogFactory.getInstance().getLogger().debug("找不到订单，订单号为:{}",outTradeNo);
            return false;
        }
        UserChargeRecord order = optionalOrder.get();
        if(order.getMoney()<money){
            LogFactory.getInstance().getLogger().info("退款金额有误，订单号为:{}",outTradeNo);
            return false;
        }
        UserRefundRecord record = new UserRefundRecord();
        record.setGoodsId(order.getChargeListId());
        record.setPayType(order.getPayType().toString());
        record.setMoney(money);
        record.setOutTradeNo(order.getOutTradeNo());
        record.setTradeNo( WxpayUtil.getNonceStr());
        record.setPayer(order.getPayer());
        record.setCreateTime(new Date());
        record.setUserId(order.getUserId());
        if (order.getPayType() == PayType.WX_JSAPI_PAY) {
            LogFactory.getInstance().getLogger().debug("订单号为：" + outTradeNo + "进行微信退款");
            // 微信支付退款
            if (wxRefunds(order, money,record)) {
                userRefundSuccessUpdateRecord(order, record);
            } else {
                userRefundFailUpdateRecord(order, record);
                return false;
            }
        }
        return true;
    }

    /**
     * 退款失败之后的记录更新
     * @param order 需要更新的订单
     * @param record 退款记录
     * @throws java.sql.SQLException
     */
    private void userRefundFailUpdateRecord(UserChargeRecord order, UserRefundRecord record) throws java.sql.SQLException {
        record.setState(RefundRecordType.REFUND_RECORD_FAIL);
        record.setUpdateTime(new Date());
        userRefundRecordService.insert(record);
        order.setState(8);
        userChargeRecordService.update(order);
    }

    /**
     * 退款成功之后的记录更新
     * @param order 需要更新的订单
     * @param record 退款记录
     * @throws java.sql.SQLException
     */
    private void userRefundSuccessUpdateRecord(UserChargeRecord order, UserRefundRecord record) throws java.sql.SQLException {
        record.setState(RefundRecordType.SEND_SUCCESS);
        record.setUpdateTime(new Date());
        userRefundRecordService.insert(record);
        order.setState(OrderState.REFUNDS);
        userChargeRecordService.update(order);
    }


    /**
     * 微信退款
     *
     * @param order
     * @return
     * @throws Exception
     */
    public Boolean wxRefunds(UserChargeRecord order, Integer money,UserRefundRecord record) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = null;
        try {
            if (order.getPayType() == PayType.WX_JSAPI_PAY) {
                //微信公众号支付退款证书路径
                instream = new FileInputStream(new File(WxpayConfig.WX_PUBLIC_WXREFUNDS));
                //暂时修改路径
//                instream = new FileInputStream(new File("/webapp/WEB-INF/wx_pub_cert/apiclient_cert.p12"));
                keyStore.load(instream, WxpayConfig.WX_PUBLIC_MCHID.toCharArray());
            }
        } finally {
            instream.close();
        }

        SSLContext sslcontext = null;
        if (order.getPayType() == PayType.WX_JSAPI_PAY) {
            sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, WxpayConfig.WX_PUBLIC_MCHID.toCharArray())
                    .build();
        }

        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
            Map<String, String> map = new HashMap<>();
            if (order.getPayType() == PayType.WX_JSAPI_PAY) {
                map.put("appid", WxpayConfig.WX_PUBLIC_APP_ID);
                map.put("mch_id", WxpayConfig.WX_PUBLIC_MCHID);
            }

            map.put("nonce_str", WxpayUtil.getNonceStr());
            map.put("out_trade_no", order.getOutTradeNo());
            map.put("out_refund_no", record.getTradeNo());
            map.put("total_fee", order.getMoney().toString());
            map.put("refund_fee", money + "");
            DataDictionary dataDictionary = dataDictionaryService.getDicByKey("PAY_CALLBACK");
            map.put("notify_url", dataDictionary.getValue() + WxpayConfig.REFUND_NOTIFY_URL);
            // 少写签名
            Map<String, String> result = WxpayBuilder.buildRequestPara(map);
            String xmlParams = WxpayUtil.parseToXml(result);
            LogFactory.getInstance().getLogger().debug("微信退款请求报文:" + xmlParams);
            StringEntity stringEntity = new StringEntity(xmlParams, "utf-8");
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text = "";
                    String temp;
                    while ((temp = bufferedReader.readLine()) != null) {
                        text = text + temp;
                    }
                    Map<String, String> data = WxpayUtil.parseToMap(text);
                    LogFactory.getInstance().getLogger().debug("微信退款接收报文:" + JsonHelper.toJson(data));
                    if (WxpayConfig.SUCCESS.equals(data.get("return_code")) && WxpayConfig.SUCCESS.equals(data.get("result_code"))) {
                        return true;
                    }
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return false;
    }


}
