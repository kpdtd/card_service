package com.anl.user.pay.wxpay;


public final class WxpayConfig {
	/**
	 * 商家可以考虑读取配置文件
	 */
	//微信公众号APPID
	public static final String WX_PUBLIC_APP_ID="wx42b39c1baf0f4c20";

	//公众号商户号
	public static final String WX_PUBLIC_MCHID = "1500048162";//预支付请求的商户号
	//微信公众号退款证书路径
	public static final String WX_PUBLIC_WXREFUNDS = "/usr/local/tomcat/webapps/flow_iot_service/WEB-INF/wx_pub_cert/apiclient_cert.p12";

	//商户号对应的密钥
	public static final String PARTNER_KEY = "kjlFJDU0RF55S29KjIeLFSJmYZlp521o";
	//预支付接口
	public static final String PREPAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";  
	//查询订单接口
	public static final String QUERY_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	//签名算法常量值
	public static final String SIGN_METHOD = "sha1";
	//异步通知地址
//	public static final String NOTIFY_URL = (Const.IS_TEST?"http://noweb.com":"http://service.yoyo.cloudmobi.cn/shark-miai-service")+"/third/party/wx/notify"; 
	public static final String NOTIFY_URL = "api/wxPayCallback";
	public static final String REFUND_NOTIFY_URL = "api/wxRefundCallback";
	//交易类型
	//JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
	public static final String TRADE_All_TPRE = "JSAPI";

	//财付通商户号
	public static final String PARTNER = "1496051692";//app请求的商户号

	//暂定包值
	public static final String PACKAGE = "Sign=WXPay"; 
	//返回状态成功
	public static final String SUCCESS = "SUCCESS"; 
	//返回状态失败
	public static final String FAIL = "FAIL"; 
	
}
