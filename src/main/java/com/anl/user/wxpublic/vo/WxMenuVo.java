package com.anl.user.wxpublic.vo;

import java.util.List;

/**
 * Created by yangyiqiang on 2018/7/18.
 */
public class WxMenuVo {
    public Integer buttonType;//菜单类型,1一级菜单,2,二级菜单
    public String type;//菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
    public String name;//菜单标题，不超过16个字节，子菜单不超过60个字节
    public String key;//菜单KEY值，用于消息接口推送，不超过128字节
    public String url;//网页跳转
    public List<WxMenuVo> subButton;

    public List<WxMenuVo> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<WxMenuVo> subButton) {
        this.subButton = subButton;
    }

    public Integer getButtonType() {
        return buttonType;
    }

    public void setButtonType(Integer buttonType) {
        this.buttonType = buttonType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
