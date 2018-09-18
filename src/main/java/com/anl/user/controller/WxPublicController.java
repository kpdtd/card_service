package com.anl.user.controller;

import com.anl.user.constant.OrderState;
import com.anl.user.constant.OrderType;
import com.anl.user.constant.PayType;
import com.anl.user.constant.WxPublicConstant;
import com.anl.user.dto.ActionResult;
import com.anl.user.logic.UserChargeRecordLogic;
import com.anl.user.persistence.po.UserChargeRecord;
import com.anl.user.service.UserChargeRecordService;
import com.anl.user.util.*;
import com.anl.user.wxpublic.logic.WxMsgLogic;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by yangyiqiang on 2018/9/3.
 */
@Controller
@RequestMapping(value = "/anl")
public class WxPublicController extends BaseController {

    @Autowired
    UserChargeRecordService userChargeRecordService;
    @Autowired
    UserChargeRecordLogic userChargeRecordLogic;
    @Autowired
    WxPublicConstant wxPublicConstant;
    @Autowired
    WxMsgLogic wxMsgLogic;

    /**
     * 公众号页面入口
     * 1充值toChargePage
     * 2激活 toActive
     * 3查询getCardFlowInfo
     * 4加油包购买
     * 5购买充值卡入口buyCard
     * 6登录toLogin
     */
    //参考文档:https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
    @RequestMapping(value = "getWXOpenId")
    public void getWXOpenId(HttpServletRequest request, HttpServletResponse response, String state) {
        String appid = wxPublicConstant.getAppId();
        String redirect_uri = wxPublicConstant.getRedirectUri();
        LogFactory.getInstance().getLogger().error("授权登录重定向地址URI=" + redirect_uri);
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_base&state=" + state + "#wechat_redirect";
        try {
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 微信重定向地址,获取到openid
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "toWXOpenId")
    public String toWXOpenId(HttpServletRequest request, HttpServletResponse response) {
        String openid = "";
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        LogFactory.getInstance().getLogger().debug("请求微信公众号获取code=" + code + ",state=" + state);
        //根据code获取到accessToken
        String appid = wxPublicConstant.getAppId();
        String appsecret = wxPublicConstant.getAppsecret();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
        try {
            String json = HttpClient.getRequest(url);
            LogFactory.getInstance().getLogger().debug("获取openid返回:" + json);
            openid = JsonHelper.toMap(json).get("openid").toString();
        } catch (Exception e) {
            e.printStackTrace();
            LogFactory.getInstance().getLogger().error("获取openid异常" + e.getMessage());
        }
        return "redirect:" + state + "?openid=" + openid;
    }

    /**
     * 公众号购买卡时付费调用
     * <p>
     * requestParam:
     * 1、goodsId |必须 |String |商品ID 对应列表返回的数据ID
     * 2、giftId |可空 |String |赠送的商品ID 对应赠送信息得数据ID
     * 3、money |必须 |Integer |支付金额 单位：分，按产品列表
     * 4、cardId |必须 ｜  Integer | 卡ID
     * <p>
     *
     * @param body
     * @return
     */
    @RequestMapping(value = "/wxPublicCreateOrder", method = RequestMethod.POST)
    @ResponseBody
    public String wxPublicCreateOrder(HttpServletRequest request, @RequestBody String body) {
        LogFactory.getInstance().getLogger().debug("接收到生成订单信息data=" + body);
        // 获取登录状态
        if (StringUtils.isNotBlank(body)) {
            try {
                // 获取接口数据，存入map中
                Map<Object, Object> map = JsonHelper.toMap(body);
                Integer payType = PayType.WX_JSAPI_PAY;
                Object goodsIdObj = map.get("goodsId");
                Object moneyObj = map.get("money");
                Object userIdObj = map.get("userId");
                int goodsId = Integer.parseInt(goodsIdObj == null ? "" : goodsIdObj.toString());
                int money = Integer.parseInt(moneyObj == null ? "" : moneyObj.toString());
                int userId = Integer.parseInt(userIdObj == null ? "0" : userIdObj.toString());
                //判断这个订单是否付过款
                Map<String, Object> model = new HashMap<>();
                model.put("goodsId", goodsId);//为t_activity_card_info的ID
                model.put("orderType", OrderType.BUY_FLOW_CARD);
                model.put("state", OrderState.FLOW_RECHARGE_SUCCESS);
                Optional<UserChargeRecord> orderOptional = userChargeRecordService.getListByMap(model).stream().findFirst();
                if (orderOptional.isPresent()) {
                    return JsonHelper.toJson(ActionResult.fail(1, "生成订单异常"));
                }
                String outTradeNo = SeqIdGenerator.getRandom32();
                String ip = IpUtil.getIpAddr(request);
                // 创建正常的套餐订单对象
                UserChargeRecord userChargeRecord = new UserChargeRecord(payType, outTradeNo, ip, money);
                userChargeRecord.setChargeListId(goodsId);
                userChargeRecord.setUserId(userId);
                userChargeRecord.setIccid("0");
                userChargeRecord.setOrderType(OrderType.BUY_FLOW_CARD);
                userChargeRecord.setOpenId(getOpenIdFromCookie(request));
                ActionResult actionResult = userChargeRecordLogic.creatOrder(userChargeRecord);
                return JsonHelper.toJson(actionResult);
            } catch (Exception e) {
                e.printStackTrace();
                LogFactory.getInstance().getLogger().error("生成订单异常");
                Map<String, Object> dataMap = new HashMap<String, Object>();
                dataMap.put("payData", "");
                dataMap.put("outTradeNo", "");
                return JsonHelper.toJson(ActionResult.fail(1, "生成订单异常", dataMap));
            }
        } else {
            LogFactory.getInstance().getLogger().debug("参数不完整并返回error");
        }
        return JsonHelper.toJson(ActionResult.fail(1, "服务器访问失败"));
    }

    @RequestMapping(value = "/toCancelOrder", method = RequestMethod.GET)
    @ResponseBody
    public String toCancelOrder(HttpServletRequest request, String outTradeNo) {
        try {
            Map<String, Object> model = new HashMap<>();
            model.put("outTradeNo", outTradeNo);
            Optional<UserChargeRecord> orderOptional = userChargeRecordService.getListByMap(model).stream().findFirst();
            if (orderOptional.isPresent()) {
                UserChargeRecord userChargeRecord = orderOptional.get();
                userChargeRecord.setState(OrderState.CANCEL);
                userChargeRecordService.update(userChargeRecord);
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    //接收微信公众号消息
    @RequestMapping(value = "ck")
    public void checkSignature(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
        MDC.put("seqID", SeqIdGenerator.generate());// 日志序列
        String signature = request.getParameter("signature");//	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String timestamp = request.getParameter("timestamp");//	时间戳
        String nonce = request.getParameter("nonce");//	随机数
        String echostr = request.getParameter("echostr");//随机字符串
        LogFactory.getInstance().getLogger().debug("接收到微信公众号消息请求--------------" + body);
        if (StringUtils.isNotBlank(body)) {
            //解析消息,进行消息分发
            this.writerToClient("succss", response);
            try {
                this.writerToClient(wxMsgLogic.readMeg(body), response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.writerToClient(echostr == null ? "" : echostr, response);
    }

}
