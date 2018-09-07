package com.anl.user.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 请添加类描述
 * </p>
 * 修改记录:
 * (从这里添加，没有则删除此项)
 * 作者 yangyiqiang
 * 日期 2016 2016年11月4日
 */

public enum SystemErrorCode {

    success(0, "成功"),
    error(1, "系统异常"),

    //用户
    userTokenError(1001, "用户token验证失败"),
    updateMobileMobileExist(1002, "该手机号已经被注册"),
    sendValidationCode_error2(1003, "短信验证码下发失败,该用户已停用"),
    sendValidationCode_error(1003, "短信验证码下发失败"),
    validationCode_error(1004, "手机号错误或验证码超过有效期"),
    userMobileOrCode_error(1005, "登录失败,手机号或验证码错误"),
    userMobileNull(1006, "登录失败,手机号不能为空"),
    getValidationCode_error(1007, "产生验证码异常"),
    userState_error(1008, "登录失败,该用户已停用"),
    userNotExist(1009, "该用户不存在"),
    userNotHas(1010, "该用户不存在"),
    updateOpenIdExist(1011, "该用户openid被注册"),
    userOpenidNull(1012, "openid不能为空"),
    checkPhoneNum(1013, "此手机号已经注册"),
    getValidationCodeIsNULL(1014, "验证码不能为空"),
    cardIsNotExist(1020, "卡号不存在"),

    operators_error(2001,"无法获取到运营商信息"),

    // 产品相关错误信息
    goods_not_exists(3001,"该产品不存在"),

    //支付相关
    payError(5001,"支付失败"),

    balanceError(5004,"支付余额不足"),

    needChargeError(5005,"请先充值"),

    // 宏旗流量卡接口
    request_param_error(7001,"请求参数错误"),

    order_exists(7002,"该订单号已存在"),

    iccid_not_null(7003,"Iccid不能为空"),

    user_code_error(7004,"userCode错误"),

    partner_config_error(7005,"配置信息错误"),

    //图片上传
    picPath_error(8050,"没有配置图片保存根目录"),

    pic_user_logout(8051,"没有登录用户信息，无法进行图片上传操作"),

    plans_cancle_error(9001,"套餐取消失败,当前套餐为赠送套餐时不能取消下月套餐");

    private int code;
    private String desc;

    private static final Map<Integer, String> err_desc = new HashMap<Integer, String>();

    static {
        for (SystemErrorCode refer : SystemErrorCode.values()) {
            err_desc.put(refer.getCode(), refer.getDesc());
        }
    }

    private SystemErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(int code) {
        return err_desc.get(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
