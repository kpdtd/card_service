package com.anl.user.controller;

import com.anl.user.account.logic.ValidationCodeLogic;
import com.anl.user.constant.ActivityCardType;
import com.anl.user.constant.SystemErrorCode;
import com.anl.user.constant.UserState;
import com.anl.user.constant.WxPublicPageErrorState;
import com.anl.user.dto.ActionResult;
import com.anl.user.dto.LogicResult;
import com.anl.user.logic.UserLoginLogic;
import com.anl.user.persistence.po.*;
import com.anl.user.persistence.vo.UserFlowUsedDayVo;
import com.anl.user.service.*;
import com.anl.user.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by yangyiqiang on 2018/9/3.
 * 公众号页面
 */
@Controller
@RequestMapping(value = "/anl")
public class WxPageController extends BaseController {

    @Autowired
    ActivityCardInfoService activityCardInfoService;
    @Autowired
    ValidationCodeLogic validationCodeLogic;
    @Autowired
    UserService userService;
    @Autowired
    UserLoginLogic userLoginLogic;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    UserFlowUsedDayService userFlowUsedDayService;
    @Autowired
    ChargeListService chargeListService;
    @Autowired
    DataDictionaryService dataDictionaryService;
    @Autowired
    UserChargeRecordService userChargeRecordService;
    @Autowired
    CardService cardService;

    /**
     * 购买流量卡的入口页面 111111111
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/buyCard")
    public String buyCard(HttpServletRequest request, HttpServletResponse response) {
        String openid = request.getParameter("openid");
        wxPutCookie(response, openid);
        String ranNum = RandomUtil.generateString(16);
        request.setAttribute("ranNum", "pay_" + ranNum);
        request.setAttribute("activityFlag", 1);//分类标识,统计用,不同的购买入口该值不一样.
        request.setAttribute("urlSuffix", "buyCard");
        return "/view/WXPUB/activity/activity5-payfirst";
    }

    /**
     * 跳转到支付页面
     *
     * @param request
     * @param activityCardId
     * @return
     */
    @RequestMapping(value = "/toPayPage")
    public String toPayPage(HttpServletRequest request, Integer activityCardId) {
        try {
            ActivityCardInfo activityCardInfo = activityCardInfoService.getById(activityCardId);
            String resolve = EnCoderUtils.resolveToEmojiFromByte(activityCardInfo.getAddress());
            activityCardInfo.setAddress(resolve);
            request.setAttribute("activityCardInfo", activityCardInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/view/WXPUB/activity/activity5-pay";
    }

    @RequestMapping(value = "/createActivetyCardInfo")
    @ResponseBody
    public String createActivetyCardInfo(HttpServletRequest request, @RequestBody String body) {
        try {
            if (StringUtils.isNotBlank(body)) {
                Map<Object, Object> map = JsonHelper.toMap(body);
                Object moneyObj = map.get("money");
                Object usernameObj = map.get("username");
                Object addressObj = map.get("address2");
                Object mobileObj = map.get("mobile");
                Object buyNumObj = map.get("buyNum");//暂时不用
                Object liuyanObj = map.get("liuyan");
                Object randomNumObj = map.get("randomNum");
                Object activityFlagObj = map.get("activityFlag");
                Object urlSuffixObj = map.get("urlSuffix");
                Object flag = map.get("flag");
                ActivityCardInfo activityCardInfo = new ActivityCardInfo();
                if (moneyObj != null) {
                    activityCardInfo.setPrice(Integer.parseInt(moneyObj.toString()));
                }
                if (usernameObj != null) {
                    activityCardInfo.setName(usernameObj.toString());
                }
                if (addressObj != null) {
                    String emoji = EnCoderUtils.resolveToByteFromEmoji(addressObj.toString());
                    activityCardInfo.setAddress(emoji);
                }
                if (mobileObj != null) {
                    activityCardInfo.setMobile(mobileObj.toString());
                }
                if (activityFlagObj != null) {
                    activityCardInfo.setSort(Integer.parseInt(activityFlagObj.toString()));
                }
                if (liuyanObj != null) {
                    String str = liuyanObj.toString();
                    String emoji = EnCoderUtils.resolveToByteFromEmoji(str);
                    activityCardInfo.setInfo(emoji);
                }
                activityCardInfo.setCreateTime(new Date());
                activityCardInfo.setUpdateTime(new Date());
                activityCardInfo.setCardState(1);//发货的卡默认为开机状态
                activityCardInfo.setState(ActivityCardType.NO_SEND_OUT);
                activityCardInfo.setPayState(ActivityCardType.ONLINE_NOPAY);
                activityCardInfoService.insert(activityCardInfo);
                return JsonHelper.toJson(ActionResult.success(activityCardInfo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonHelper.toJson(ActionResult.fail());
    }

    //-------查询账户topage22222222-----
    @RequestMapping(value = "/getCardFlowInfo")
    public String getCardFlowInfo(HttpServletRequest request, HttpServletResponse response) {
        String openid = request.getParameter("openid");
        if (StringUtils.isBlank(openid)) {
            return "view/WXPUB/login";
        }
        wxPutCookie(response, openid);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("wxOpenid", openid);
        try {
            List<User> users = userService.getListByMap(dataMap);
            if (CollectionUtils.isNotEmpty(users)) {
                User user = users.get(0);
                //查账户
                UserAccount userAccount = userAccountService.getUserAccountByUserId(user.getId());
                request.setAttribute("userAccount", userAccount);
                request.setAttribute("user", user);
                //查询昨天使用流量
                Date date = DateUtil.afterNDaysDate(DateUtil.getTodayStartTime(), -1);
                int userFlowUsedDay = userFlowUsedDayService.getUsedFlowByRecordTime(user.getCardId(), date, new Date());
                request.setAttribute("yesterdayFlowUsed", userFlowUsedDay);
                request.setAttribute("time", DateUtil.dateToString(new Date(), DateUtil.DATE_FORMAT_SHORT));
                request.setAttribute("countTime", DateUtil.dateToString(date, DateUtil.DATE_FORMAT_SHORT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "view/WXPUB/cardFlow";
    }

    //账户页面跳转到流量使用详情页面
    @RequestMapping(value = "goFlowDetail")
    public String goFlowDetail(HttpServletRequest request, String userPhone) {
        if (StringUtils.isBlank(userPhone)) {
            return "view/WXPUB/login";
        }
        try {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("phone", userPhone);
            List<User> users = userService.getListByMap(dataMap);
            if (CollectionUtils.isNotEmpty(users)) {
                User user = users.get(0);
                List<UserFlowUsedDayVo> usedDayList = new ArrayList<>();
                dataMap = new HashMap<>();
                dataMap.put("cardId", user.getCardId());
                Date befor7date = DateUtil.afterNDaysDate(DateUtil.getTodayStartTime(), -7);
                dataMap.put("startTime", befor7date);
                dataMap.put("endTime", new Date());
                userFlowUsedDayService.getListByMap(dataMap).stream().forEach(userFlowUsedDay -> {
                    String date = DateUtil.dateToString(userFlowUsedDay.getRecordTime(), DateUtil.DATE_FORMAT_SHORT);
                    UserFlowUsedDayVo userFlowUsedDayVo = new UserFlowUsedDayVo();
                    userFlowUsedDayVo.setRecordTimeStr(date);
                    //页面要求保留两位小数，此处直接除以10，忽略最后一位，页面上直接除以100显示就可以了
                    if (userFlowUsedDay.getFlow() >= 1024) {
                        double v = (double) userFlowUsedDay.getFlow() / 1024;
                        DecimalFormat df = new DecimalFormat("#.00");
//                    userFlowUsedDayVo.setFlow(df.format(v)+"");
                        userFlowUsedDayVo.setFlowStr(df.format(v) + "");
                        userFlowUsedDayVo.setFlowUnit("G");
                    } else {
                        userFlowUsedDayVo.setFlow(userFlowUsedDay.getFlow());
                        userFlowUsedDayVo.setFlowUnit("M");
                    }
                    userFlowUsedDayVo.setId(userFlowUsedDay.getId());
                    usedDayList.add(userFlowUsedDayVo);
                });
                usedDayList.sort((uf1, uf2) -> uf1.getId() < uf2.getId() ? 1 : -1);
                request.setAttribute("usedDayList", usedDayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "view/WXPUB/flowDetail";
    }

    //-----登录333333333333333333----
    //登录页面,手机号 验证码
    @RequestMapping(value = "toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        String openid = request.getParameter("openid");
        if (StringUtils.isNotBlank(openid)) {
            LogFactory.getInstance().getLogger().debug("从cookie获取到openid信息" + openid);
        } else {
            LogFactory.getInstance().getLogger().debug("从cookie没有openid信息");
        }
        return "view/WXPUB/login";
    }

    //充值页面入口444444444444444
    @RequestMapping(value = "toChargePage")
    public String toRechargeCall(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String openid = request.getParameter("openid");
        if (StringUtils.isBlank(openid)) {
            return "redirect:getWXOpenId?state=toChargePage";
        }
        wxPutCookie(response, openid);
        //判断openid是否存在用户信息
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("wxOpenid", openid);
        User user = null;
        try {
            List<User> users = userService.getListByMap(dataMap);
            if (CollectionUtils.isNotEmpty(users)) {
                user = users.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogFactory.getInstance().getLogger().error("充值页面获取用户信息异常,openid=" + openid);
        }
        if (user == null) {
            //跳转到登录页面
            return "redirect:getWXOpenId?state=toLogin";
        }
        //获取充值价格列表
        Map<String, Object> data = new HashMap<>();
        List<ChargeList> chargeLists = chargeListService.getListByMap(data);
        request.setAttribute("chargeLists", chargeLists);
        //是否配置了默认选定的价格项
        DataDictionary defaultChargeDic = dataDictionaryService.getDicByKey("DEFAULT_CHARGE");
        if (defaultChargeDic != null && StringUtils.isNotBlank(defaultChargeDic.getValue())) {
            ChargeList defaultCharge = chargeListService.getById(Integer.parseInt(defaultChargeDic.getValue()));
            request.setAttribute("defaultCharge", defaultCharge);
        }
        request.setAttribute("userId", user.getId());//用户ID
        return "view/WXPUB/rechargeCall";
    }

    //充值页面支付完成之后的页面
    @RequestMapping(value = "getRechargeCallFinishPage")
    public String getRechargeCallFinishPage(String outTradeNo, HttpServletRequest request) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("outTradeNo", outTradeNo);
            Optional<UserChargeRecord> orderOptional = userChargeRecordService.getListByMap(data).stream().findFirst();
            if (orderOptional.isPresent()) {
                request.setAttribute("userOrder", orderOptional.get());
                String time = DateUtil.dateToString(orderOptional.get().getCreateTime(), DateUtil.DATE_FORMAT_FULL);
                request.setAttribute("time", time);
                //设置标志位
                request.setAttribute("flag", 1);
                User user = userService.getById(orderOptional.get().getUserId());
                request.setAttribute("phone", user.getPhone());
            }
            return "view/WXPUB/rechargeCall_finish";
        } catch (Exception e) {
            e.printStackTrace();
            //设置标志位
            request.setAttribute("flag", 0);
            return "view/WXPUB/rechargeCallFinish";
        }
    }

    //----获取验证码---
     /*
     * 获取验证码 ,参数为空，返回成功即可 (修改手机号码发送验证码短信，暂时不做区分）
     */
    @RequestMapping(value = "/getValidationCode", method = RequestMethod.POST)
    public
    @ResponseBody
    String getValidationCode(@RequestBody String body) {
        if (StringUtils.isNotBlank(body)) {
            try {
                String mobile = JsonHelper.toMap(body).get("mobile").toString();
                LogicResult logicResult = validationCodeLogic.getValidationCode(mobile, 0);
                if (logicResult.getCode() == 1) {
                    //成功了
                    return JsonHelper.toJson(ActionResult.success());
                } else {
                    return JsonHelper.toJson(ActionResult.fail(SystemErrorCode.sendValidationCode_error.getCode(), logicResult.getData().toString()));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JsonHelper.toJson(ActionResult.fail(SystemErrorCode.sendValidationCode_error.getCode()));
    }

    @RequestMapping(value = "userLogin")
    @ResponseBody
    public String userLogin(String validationCode, String mobile, HttpServletRequest request, HttpServletResponse response) {
        try {
            String openid = getOpenIdFromCookie(request);
            LogFactory.getInstance().getLogger().debug("cookie中的openid为{}", openid);
            if (StringUtils.isNotBlank(openid)) {
                ActionResult actionResult = userLoginLogic.userLogin(validationCode, mobile, openid);
                Cookie cookie1 = new Cookie("loginUser", mobile);
                response.addCookie(cookie1);
                return JsonHelper.toJson(actionResult);
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonHelper.toJson(ActionResult.fail());
        }
    }

    //激活接口,手机号和卡号进行绑定
    @RequestMapping(value = "toActive")
    public String toActive(HttpServletRequest request, HttpServletResponse response) {
        String openid = request.getParameter("openid");
        if (StringUtils.isBlank(openid)) {
            return "redirect:getWXOpenId?state=toLogin";
        }
        wxPutCookie(response, openid);
        request.setAttribute("error", 0);
        return "view/WXPUB/activateCardStep";
    }


    //激活的时候使用,验证输入的卡号对不对
    @RequestMapping(value = "verifIccid")
    @ResponseBody
    public String verifIccid(String iccid) {
        Map<String, Object> data = new HashMap<>();
        if (StringUtils.isNotBlank(iccid)) {
            data.put("iccid", iccid);
            try {
                List<Card> cardList = cardService.getListByMap(data);
                Optional<Card> first = cardList.stream().findFirst();
                if (first.isPresent()) {
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("cardId", first.get().getId());
                    List<User> users = userService.getListByMap(dataMap);
                    Optional<User> user = users.stream().findFirst();
                    if (user.isPresent()) {
                        if (UserState.PRE_USER == user.get().getState()) {
                            return JsonHelper.toJson(ActionResult.success(first.get().getId()));
                        }
                        return WxPublicPageErrorState.CARD_HAS_ACTIVE + "";
                    } else {
                        //没有预生成用户,无法注册
                        return WxPublicPageErrorState.CARD_NOT_FIND + "";
                    }

                } else {
                    return WxPublicPageErrorState.CARD_NOT_FIND + "";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return WxPublicPageErrorState.SERVICE_ERROR + "";
            }
        }
        return WxPublicPageErrorState.CARD_INPUT_EMPTY + "";
    }

    /**
     * 激活进入下一步绑定页面
     *
     * @param cardId
     * @param iccid
     * @param request
     * @return
     */
    @RequestMapping(value = "getCardByIccid")
    public String getCardByIccid(String cardId, String iccid, HttpServletRequest request) {
        request.setAttribute("cardId", cardId);
        request.setAttribute("iccid", iccid);
        return "view/WXPUB/banding";
    }

    /**
     * 激活绑定最终的操作
     *
     * @param request
     * @param cardId
     * @param validationCode
     * @param mobile
     * @return
     */
    @RequestMapping(value = "userBanding")
    @ResponseBody
    public String userBanding(HttpServletRequest request, Integer cardId, String validationCode, String mobile) {
        try {
            Optional<Cookie> cookie = Arrays.stream(request.getCookies()).filter(i -> i.getName().equals("openid")).findFirst();
            if (cookie.isPresent()) {
                String openid = cookie.get().getValue();
                ActionResult actionResult = userLoginLogic.userBanding(validationCode, mobile, cardId, openid);
                return JsonHelper.toJson(actionResult);
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonHelper.toJson(ActionResult.fail());
        }
    }

    /**
     * 绑卡激活成功之后跳转的绑卡成功页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "getAcCardSuccess")
    public String getAcCardSuccess(HttpServletRequest request) {
        try {
            String openid = getOpenIdFromCookie(request);
            if (StringUtils.isNotBlank(openid)) {
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("wxOpenid", openid);
                Optional<User> user = userService.getListByMap(dataMap).stream().findFirst();
                if (user.isPresent()) {
                    request.setAttribute("userId", user.get().getId());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "view/WXPUB/activateCardSuccess";
    }
}
