package com.anl.user.pay.wxpay;

import org.apache.commons.lang3.StringUtils;

import java.util.*;


/** 
 * @Description: TODO
 * @author mouzhanpeng
 * @date 2016年8月11日 下午5:17:31
 * @version 
 * @since 1.8.0
 */
public class WxpayHelper {
	 /** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("key")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /** 
     * 把数组所有元素，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要参与字符拼接的参数组
     * @param sorts   是否需要排序 true 或者 false
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }
    
    /**
     * 验签响应签名
     * @param paras
     * @return
     */
    public static boolean verify(Map<String,String> paras){
    	String sign = paras.get("sign");
    	if(StringUtils.isBlank(sign)) return false;
    	return sign.equals(WxpayBuilder.buildRequestMysign(paraFilter(paras)));
    }
    
    /**
     * 响应成功
     * @param loggerAgent - 日志代理
     * @return String
     */
    /*public static String yes(ILoggerAgent loggerAgent){
    	Map<String,String> return_wx = new HashMap<>();
    	return_wx.put("return_code", WxpayConfig.SUCCESS);
    	loggerAgent.payParamsLogger(null , "WX-NOTIFY", false, return_wx);
    	return WxpayUtil.parseToXml(return_wx);
    }*/
    
    /**
     * 响应失败
     * @param loggerAgent - 日志代理
     * @param errDesc - 错误信息
     * @return String
     */
    /*public static String no(ILoggerAgent loggerAgent , String errDesc){
    	Map<String,String> return_wx = new HashMap<>();
    	return_wx.put("return_code", WxpayConfig.FAIL);
		return_wx.put("return_msg", errDesc);
		loggerAgent.payParamsLogger(null , "WX-NOTIFY", false, return_wx);
    	return WxpayUtil.parseToXml(return_wx);
    }*/
    
}
