package com.anl.user.wxpublic.logic;

import com.anl.user.constant.WxPublicConstant;
import com.anl.user.persistence.po.WxAccessToken;
import com.anl.user.service.WxAccessTokenService;
import com.anl.user.util.DateUtil;
import com.anl.user.util.HttpClient;
import com.anl.user.util.JsonHelper;
import com.anl.user.util.LogFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by yangyiqiang on 2018/7/16.
 * 获取微信公众号access_token
 */
@Component
public class WxGetAccessTokenLogic {

    @Autowired
    WxPublicConstant wxPublicConstant;
    @Autowired
    WxAccessTokenService wxAccessTokenService;
    //https请求方式: GET
    //https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET

    /**
     * 从数据库中获取根据appid获取token,
     *
     * @throws Exception
     */
    public String getWxAccessToken() throws Exception {
        String result = "";
        String appid = wxPublicConstant.getAppId();
        try {
            WxAccessToken wxAccessToken = wxAccessTokenService.getWxAccessTokenByAppId(appid);
            if (wxAccessToken != null) {
                //判断该token是否过期
                String expireTime = DateUtil.dateToString(wxAccessToken.getExpireTime(), DateUtil.DATE_FORMAT_COMPACTFULL);
                String now = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMAT_COMPACTFULL);
                if (DateUtil.isBefore(now, expireTime, DateUtil.DATE_FORMAT_COMPACTFULL)) {
                    result = wxAccessToken.getAccessToken();
                }
            }
            if (StringUtils.isBlank(result)) {
                String url = wxPublicConstant.getWxUrl() + "/cgi-bin/token?grant_type=client_credential&appid=" + wxPublicConstant.getAppId() + "&secret=" + wxPublicConstant.getAppsecret();
                String json = HttpClient.getRequest(url);
                LogFactory.getInstance().getLogger().info("请求微信获取accessToken,返回json=" + json);
                result = JsonHelper.toMap(json).get("access_token") == null ? "" : JsonHelper.toMap(json).get("access_token").toString();
                if (wxAccessToken != null && wxAccessToken.getId() > 0) {
                    wxAccessToken.setAccessToken(result);
                    wxAccessToken.setUpdateTime(new Date());
                    wxAccessToken.setExpireTime(DateUtil.afterNSecondsDate(7200));
                    wxAccessTokenService.update(wxAccessToken);
                } else {
                    wxAccessToken = new WxAccessToken();
                    wxAccessToken.setAccessToken(result);
                    wxAccessToken.setUpdateTime(new Date());
                    wxAccessToken.setExpireTime(DateUtil.afterNSecondsDate(7200));
                    wxAccessToken.setCreateTime(new Date());
                    wxAccessToken.setAppId(appid);
                    wxAccessTokenService.insert(wxAccessToken);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogFactory.getInstance().getLogger().error("从数据库获取微信公众号TOken失败,appId=" + appid + ";" + e.getMessage());
        }

        return result;
    }

}
