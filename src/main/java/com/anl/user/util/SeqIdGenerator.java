package com.anl.user.util;

import java.util.Date;
import java.util.Random;

public class SeqIdGenerator {
    public static String generate(){
        StringBuffer seqId = new StringBuffer();
        seqId.append(System.currentTimeMillis());
        int num = new Random().nextInt(9999);
        seqId.append(num);
        return seqId.toString();
    }

	public static String getRandom32() {//
		StringBuffer tigerTid = new StringBuffer("G");
		tigerTid.append(DateUtil.getCurDateTime(DateUtil.DATE_FORMAT_FULL_MSEL));//yyyyMMddHHmmssSSSS 带毫秒数  18位
		tigerTid.append(RandomUtil.generateString(13));//产生5位随机数,必须*10000后在int，去掉小数掉后面位数
		return tigerTid.toString();
	}

	public static String createHibeiOrderId(String prefix){
		StringBuffer seqId = new StringBuffer(prefix);
		seqId.append(DateUtil.dateToString(new Date(),DateUtil.DATE_FORMAT_COMPACTFULL));
		seqId.append(getFixLenthString(8));
		return seqId.toString();
	}

	public static String createCardInfoIdentity(){
		StringBuffer seqId = new StringBuffer();
		seqId.append(DateUtil.getTimeStamp1());
		int num = new Random().nextInt(99999999);
		seqId.append(num);
		return seqId.toString();
	}

	public static String createJsydReqSeq(){
		StringBuffer seqId = new StringBuffer();
		seqId.append(DateUtil.getTimeStamp1());
		int num = new Random().nextInt(999);
		seqId.append(num);
		return seqId.toString();
	}

    /*
     * 返回长度为[strLength]的随机数
     */
    public static String getFixLenthString(int strLength) {
        Random rm = new Random();
        // 获得随机数
//        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);//Math.pow(a,b) :对a做b次幂计算  如  10,2  = 100 这里如果参数过大不能用。会溢出
        double pross = (rm.nextDouble());
        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);
        // 返回固定的长度的随机数
        return fixLenthString.substring(2, strLength + 2);
    }

	public static void main(String[] args) {

	}
}
