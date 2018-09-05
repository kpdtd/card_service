package com.anl.user.controller;

import com.anl.user.constant.ActivityCardType;
import com.anl.user.dto.ActionResult;
import com.anl.user.persistence.po.ActivityCardInfo;
import com.anl.user.service.ActivityCardInfoService;
import com.anl.user.util.EnCoderUtils;
import com.anl.user.util.JsonHelper;
import com.anl.user.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by yangyiqiang on 2018/9/3.
 * 公众号页面
 */
@Controller
@RequestMapping(value = "/anl")
public class WxPageController extends BaseController {

    @Autowired
    ActivityCardInfoService activityCardInfoService;

    /**
     * 购买流量卡的入口页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/buyCard")
    public String buyCard(HttpServletRequest request) {
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
        try{
            if(StringUtils.isNotBlank(body)){
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonHelper.toJson(ActionResult.fail());
    }
}
