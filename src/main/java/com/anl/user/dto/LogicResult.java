package com.anl.user.dto;


import com.anl.user.constant.LogicResultCode;

import java.io.Serializable;

/**
 * Created by yangyiqiang on 2018/4/1.
 * 定义组建logic返回的数据类型.
 */
public class LogicResult implements Serializable {
    private static final long serialVersionUID = 1L;
    //返回码
    private int code;
    //返回的数据
    private Object data;

    public static LogicResult getResult(int code, Object data) {
        LogicResult logicResult = new LogicResult();
        logicResult.setCode(code);
        logicResult.setData(data);
        return logicResult;
    }

    public static LogicResult success(Object data) {
        return getResult(LogicResultCode.SUCCESS, data);
    }

    public static LogicResult fail(Object data) {
        return getResult(LogicResultCode.ERROR, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
