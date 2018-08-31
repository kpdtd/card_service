package com.anl.user.account.logic;


import com.anl.user.dto.LogicResult;

public interface ValidationCodeLogic {
    LogicResult getValidationCode(String mobile, int maxTimes)throws Exception;//获取验证码

    LogicResult checkValidationCode(String validationCode, String mobile)throws  Exception;//验证码验证
}
