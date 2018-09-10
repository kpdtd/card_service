package com.anl.user.logic;

import com.anl.user.account.logic.ValidationCodeLogic;
import com.anl.user.constant.SystemErrorCode;
import com.anl.user.constant.UserState;
import com.anl.user.dto.ActionResult;
import com.anl.user.dto.LogicResult;
import com.anl.user.persistence.po.Card;
import com.anl.user.persistence.po.User;
import com.anl.user.persistence.po.ValidationCode;
import com.anl.user.service.CardService;
import com.anl.user.service.UserService;
import com.anl.user.service.ValidationCodeService;
import com.anl.user.util.DateUtil;
import com.anl.user.util.LogFactory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserLoginLogicImpl implements UserLoginLogic {

    private static final Logger logger = LogFactory.getInstance().getLogger();

    @Autowired
    ValidationCodeService validationCodeService;
    @Autowired
    ValidationCodeLogic validationCodeLogic;

    @Autowired
    UserService userService;
    @Autowired
    CardService cardService;

    /**
     * 公众号手机号登录
     *
     * @param code
     * @param mobile
     * @param openid
     * @return
     * @throws Exception
     */
    @Override
    public ActionResult userLogin(String code, String mobile, String openid) throws Exception {
        if (StringUtils.isNotBlank(mobile)) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("userPhone", mobile);
            dataMap.put("validationCode", code);
            List<ValidationCode> validationCodeList = validationCodeService.getListByMap(dataMap);
            if (CollectionUtils.isEmpty(validationCodeList)) {
                // 取不到验证码
                logger.error("手机号或验证码错误");
                return ActionResult.fail(SystemErrorCode.validationCode_error.getCode());
            }
            LogicResult logicResult= validationCodeLogic.checkValidationCode(code,mobile);
            if(logicResult.getCode()==0){
                return ActionResult.fail((Integer) logicResult.getData());
            }
            Map<String, Object> data = new HashMap<>();
            data.put("phone", mobile);
            List<User> users = userService.getListByMap(data);
            Optional<User> authenticationOptional = users.stream().findFirst();
            if (authenticationOptional.isPresent()) {
                //更新openid
                User user = authenticationOptional.get();
                user.setWxOpenid(openid);
                //redisFactory.set(openid, authenticationOptional.get().getIccid());
                userService.update(user);
                return ActionResult.success();
            } else {
                logger.error(mobile + "手机号未绑定卡");
                return ActionResult.fail(SystemErrorCode.userNotHas.getCode());
            }

        } else {
            logger.error("手机号为空，获取验证码失败");
            return ActionResult.fail(SystemErrorCode.userMobileNull.getCode());
        }
    }

    /**
     * 公众号手机绑卡,修改user表的state为绑卡状态,并更改手机号,openid等信息
     *
     * @param code
     * @param mobile
     * @param cardId
     * @param openid
     * @return
     * @throws Exception
     */
    @Override
    public ActionResult userBanding(String code, String mobile, Integer cardId, String openid) throws Exception {
        if (StringUtils.isNotBlank(mobile)) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("userPhone", mobile);
            dataMap.put("validationCode", code);
            List<ValidationCode> validationCodeList = validationCodeService.getListByMap(dataMap);
            if (CollectionUtils.isEmpty(validationCodeList)) {
                // 取不到验证码
                logger.error("手机号或验证码错误");
                return ActionResult.fail(SystemErrorCode.validationCode_error.getCode());
            }
            LogicResult logicResult= validationCodeLogic.checkValidationCode(code,mobile);
            if(logicResult.getCode()==0){
                return ActionResult.fail((Integer) logicResult.getData());
            }
            Card card = cardService.getById(cardId);//保证card存在
            dataMap = new HashMap<>();
            dataMap.put("cardId", card.getId());
            List<User> users = userService.getListByMap(dataMap);
            User user;
            if (CollectionUtils.isEmpty(users)) {
                user = new User();
                user.setCardId(card.getId());
                user.setIccid(card.getIccid());
            } else {
                user = users.get(0);
            }
            user.setWxOpenid(openid);
            user.setState(UserState.PRO_USER);
            user.setPhone(mobile);
            user.setUsername(mobile);
            user.setPassword(code);
            user.setUpdateTime(new Date());
            userService.update(user);
            return ActionResult.success();

        } else {
            logger.error("手机号为空，获取验证码失败");
            return ActionResult.fail(SystemErrorCode.userMobileNull.getCode());
        }
    }
}
