package com.anl.user.wxpublic.logic;

import com.anl.user.constant.WxPublicConstant;
import com.anl.user.util.HttpClient;
import com.anl.user.util.JsonHelper;
import com.anl.user.util.LogFactory;
import com.anl.user.wxpublic.vo.WxUserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yangyiqiang on 2018/7/17.
 * 获取用户列表,一次拉取调用最多拉取10000个关注者的OpenID
 */
@Component
public class WxUserInfoLogic {
    @Autowired
    WxGetAccessTokenLogic wxGetAccessTokenLogic;
    @Autowired
    WxPublicConstant wxPublicConstant;

    public WxUserInfoVo getWxUserInfo(String next)throws  Exception{
        WxUserInfoVo result=null;
        try {
            String token = wxGetAccessTokenLogic.getWxAccessToken();
            String url = wxPublicConstant.getWxUrl()+"/cgi-bin/user/get?access_token=" + token;
            if (StringUtils.isNotBlank(next)) {
                url = url + "&next_openid=" + next;
            }
            String json = HttpClient.getRequest(url);
            LogFactory.getInstance().getLogger().info("获取用户列表,返回json=" + json);
            if (StringUtils.isNotBlank(json)) {
                result= JsonHelper.toObject(json,WxUserInfoVo.class);
            }
        }catch (Exception e){
            e.printStackTrace();
            LogFactory.getInstance().getLogger().error("获取用户列表失败"+e.getMessage());
        }
        return result;
    }
}
