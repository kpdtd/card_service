package com.anl.user.constant;

import com.anl.user.persistence.po.DataDictionary;
import com.anl.user.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 微信公众号相关常量
 */
@Component
public class WxPublicConstant {
    //微信公众号统一URL
    static final String WX_URL="https://api.weixin.qq.com";

    //大象卡公众号
    static final String DX_APPID = "wx42b39c1baf0f4c20";
    static final String DX_APPSECRET = "9a2b4047e8172fd62ae0874db81b0cc0";
    public static final String DX_WX_PUBLIC_TOKEN = "3bX2ct92QOwDj7scVvDJyHCcXZfkqlas";
    //流量黑卡公众号

    //测试公众号
    static final String T_APPID = "wx89867c472e2ee6ae";
    static final String T_APPSECRET = "6f41992ded939b56240af56b4de113aa";

    static final String WXPUBLIC_APP = "WXPUBLIC_APP";//1正式环境,2是测试环境

    @Autowired
    DataDictionaryService dataDictionaryService;

    public String getAppId() {
        DataDictionary dataDictionary = dataDictionaryService.getDicByKey(WXPUBLIC_APP);
        if (dataDictionary != null && dataDictionary.getValue().equals("2")) {
            return T_APPID;
        } else {
            return DX_APPID;
        }
    }

    public String getAppsecret() {
        DataDictionary dataDictionary = dataDictionaryService.getDicByKey(WXPUBLIC_APP);
        if (dataDictionary != null && dataDictionary.getValue().equals("2")) {
            return T_APPSECRET;
        } else {
            return DX_APPSECRET;
        }
    }

    public String getWxUrl(){
        return  WX_URL;
    }

    public String getRedirectUri(){
        DataDictionary dataDictionary = dataDictionaryService.getDicByKey(WXPUBLIC_APP);
        if (dataDictionary != null && dataDictionary.getValue().equals("2")) {
            return "http%3A%2F%2Ftestiot.mindmobi.com%2Fapi%2FtoWXOpenId";//测试
        } else {
            return "http%3A%2F%2Fiot.mindmobi.com%2Fapi%2FtoWXOpenId";//正式
        }
    }
}
