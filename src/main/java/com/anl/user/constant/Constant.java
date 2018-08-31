package com.anl.user.constant;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Constant {
	// header头
	public static final String REQUEST_HEADER_ENCRYPT = "header-encrypt-code";
	public static final String SMS_SEND_MAX = "SMS_SEND_MAX";// 短信验证码最大下发次数
	public static final String SMS_SIGN_NAME = "SMS_SIGN_NAME";
	public static final String SMS_TEMPLATE_CODE = "SMS_TEMPLATE_CODE";

	public static final Integer FLOW_UNIT=1024;//流量单位换算

	/**
	 * 流量统一单位转换,本身int的单位是M，大于1000，转换成G，产品要求换算单位为1000并非1024, 2018 0810 修改成1024 yyq
	 */
	public static String toStringFLow(int flow) throws Exception {
		if (flow < 1024 && flow >= 0 || flow < 0 && flow > -1024) {
			return flow + "M";
		} else {
			DecimalFormat df = new DecimalFormat(".##");
			df.setRoundingMode(RoundingMode.UP);
			double f = flow / 1024d;
			return df.format(f) + "G";
		}
	}
	public  static  void main(String[] args){
		try {
			System.out.println(toStringFLow(1025));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
