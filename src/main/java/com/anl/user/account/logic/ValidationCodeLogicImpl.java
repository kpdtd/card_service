package com.anl.user.account.logic;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.anl.user.constant.Constant;
import com.anl.user.constant.SystemErrorCode;
import com.anl.user.dto.LogicResult;
import com.anl.user.persistence.po.DataDictionary;
import com.anl.user.persistence.po.ValidationCode;
import com.anl.user.service.DataDictionaryService;
import com.anl.user.service.ValidationCodeService;
import com.anl.user.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 请添加类描述 获取验证码接口，调用第三方,验证码10分钟失效，每天每个手机号最大下发5次
 * </p>
 * 修改记录: (从这里添加，没有则删除此项) 作者 yangyiqiang 日期 2016 2016年11月19日
 */
@Service
public class ValidationCodeLogicImpl implements ValidationCodeLogic {
    private static final Logger logger = LogFactory.getInstance().getLogger();
    private static final Logger smsLogger = LogFactory.getInstance().getSmsSendLogger();

    @Autowired
    ValidationCodeService validationCodeService;
    @Autowired
    Configuration configuration;
    @Autowired
    DataDictionaryService dataDictionaryService;

    @Override
    public LogicResult getValidationCode(String mobile, int maxTimes) throws Exception {
        if (StringUtils.isNotBlank(mobile)) {
            String date = DateUtil.getCurDateTime(DateUtil.DATE_FORMAT_COMPACT);
            if (maxTimes == 0) {
                maxTimes = Integer.parseInt(dataDictionaryService.getDicByKey(Constant.SMS_SEND_MAX).getValue());
            }
            // 判断当天是否已经下发了maxTimes条了，超过maxTimes条就不下发了
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("userPhone", mobile);
            dataMap.put("startTime", DateUtil.getTodayStartTime());
            int count = validationCodeService.count(dataMap);
            if (count >= maxTimes) {
                logger.info("mobile:" + mobile + ",今天已经下发" + maxTimes + "次短信验证码，不再下发");
                return LogicResult.fail("今天已经下发" + maxTimes + "次短信验证码，请明天再来");
            }
            // 调用短信下发接口
            String code = randomCode();
            if (StringUtils.isBlank(code)) {
                return LogicResult.fail(SystemErrorCode.getValidationCode_error.getCode());
            }
            DataDictionary sign = dataDictionaryService.getDicByKey(Constant.SMS_SIGN_NAME);// 签名
            DataDictionary t_code = dataDictionaryService.getDicByKey(Constant.SMS_TEMPLATE_CODE);// 模板
            String smsSendSign = "【创思卡】";
            String smsCode = "SMS_103610166";
            if (sign != null && StringUtils.isNotBlank(sign.getValue())) {
                smsSendSign = sign.getValue();
            }

            if (t_code != null && StringUtils.isNotBlank(t_code.getValue())) {
                smsCode = t_code.getValue();
            }
            logger.info("短信验证码签名:{},短信标识:{}", smsSendSign, smsCode);
            Map<String, String> content = new HashMap<>();
            content.put("code", code);
            SendSmsResponse ssr = AliyunSmsUtil.sendSms(mobile, JsonHelper.toJson(content), smsSendSign, smsCode);
            smsLogger.info("mobile=" + mobile + "|request=" + ssr.getCode() + "|response=" + ssr.getMessage());
            // String smsCode="0";
            // 记录下发流水
            ValidationCode phValidationCode = new ValidationCode();
            phValidationCode.setCreateTime(new Date());
            phValidationCode.setSendCode(smsCode);
            phValidationCode.setUserPhone(mobile);
            phValidationCode.setValidationCode(code);
            validationCodeService.insert(phValidationCode);// 流水表
            if (ssr != null && ssr.getCode().equals("OK")) {
                return LogicResult.success(code);
            } else {
                return LogicResult.fail(SystemErrorCode.sendValidationCode_error.getDesc());
            }
        } else {
            logger.error("获取验证码手机号不能为空");
        }
        return LogicResult.fail(SystemErrorCode.sendValidationCode_error.getDesc());
    }

    /**
     * 验证验证码,根据手机号获取到对应的验证码,返回正确或者错误
     *
     * @param code
     * @param mobile
     * @return
     * @throws Exception
     */
    @Override
    public LogicResult checkValidationCode(String code, String mobile) throws Exception {
        if (StringUtils.isNotBlank(mobile) || StringUtils.isNotBlank(code)) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("userPhone", mobile);
            dataMap.put("validationCode", code);
            List<ValidationCode> validationCodeList = validationCodeService.getListByMap(dataMap);
            if (CollectionUtils.isEmpty(validationCodeList)) {
                // 取不到验证码
                logger.error("手机号或验证码错误");
                return LogicResult.fail(SystemErrorCode.userMobileOrCode_error.getCode());
            }
            ValidationCode validationCode = validationCodeList.get(0);
            //是否超过有效期(2分钟内验证有效)
            Date n = DateUtil.afterNSecondsDate(validationCode.getCreateTime(), 120);
            Date now = new Date();
            if (DateUtil.isBefore(DateUtil.dateToString(n, DateUtil.DATE_FORMAT_FULL), DateUtil.dateToString(now, DateUtil.DATE_FORMAT_FULL), DateUtil.DATE_FORMAT_FULL)) {
                //失效了
                logger.error("验证码过期了" + validationCode.getValidationCode());
                return LogicResult.fail(SystemErrorCode.validationCode_error.getCode());
            }
            return LogicResult.success(validationCode);
        } else {
            return LogicResult.fail(SystemErrorCode.userMobileOrCode_error.getCode());
        }
    }

    private String randomCode() {
        String result = "";
        for (int i = 0; i < 4; i++) {
            // 调用Math.random()方法
            int num = (int) (Math.random() * 10) + 0;
            result = result + num;
        }
        return result;
    }
}
