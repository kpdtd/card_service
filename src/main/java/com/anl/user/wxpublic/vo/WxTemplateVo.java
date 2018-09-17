package com.anl.user.wxpublic.vo;

import java.util.Map;

/**
 * Created by yangyiqiang on 2018/7/16.
 * 微信公众号模板消息vo
 */
public class WxTemplateVo {

    private String touser;
    private String template_id;
    private String url;
    private String miniprogram;
    private String appid;
    private String pagepath;
    private Map<String,WxSubTemplateVo> data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(String miniprogram) {
        this.miniprogram = miniprogram;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public Map<String, WxSubTemplateVo> getData() {
        return data;
    }

    public void setData(Map<String, WxSubTemplateVo> data) {
        this.data = data;
    }
}
