package com.anl.user.wxpublic.vo;

/**
 * Created by yangyiqiang on 2018/7/18.
 * 微信用户列表
 */
public class WxUserInfoVo {
    private Integer total;
    private Integer count;
    private Object data;//openid的数组
    private String next_openid;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }
}
