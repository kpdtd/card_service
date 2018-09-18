package com.anl.user.wxpublic.logic;

import com.anl.user.constant.WxPublicConstant;
import com.anl.user.util.HttpClient;
import com.anl.user.util.JsonHelper;
import com.anl.user.util.LogFactory;
import com.anl.user.wxpublic.vo.WxTemplateVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yangyiqiang on 2018/7/16.
 * 微信公众号模板消息
 */
@Component
public class WxTemplateMsgLogic {
    @Autowired
    WxGetAccessTokenLogic wxGetAccessTokenLogic;
    @Autowired
    WxPublicConstant wxPublicConstant;
    /**
     * 发送模板消息
     * http请求方式: POST
     https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
     */
    public String sendWxTemplateMsg(WxTemplateVo wxTemplateVo)throws  Exception{
        String url=wxPublicConstant.getWxUrl()+"/cgi-bin/message/template/send?access_token="+wxGetAccessTokenLogic.getWxAccessToken();
        String sendJson= JsonHelper.toJson(wxTemplateVo);
        String returnJson= HttpClient.postRequest(url,sendJson );
        LogFactory.getInstance().getLogger().debug("发送模板消息"+sendJson+"|微信公众号返回信息:"+returnJson);
        if(StringUtils.isNotBlank(returnJson)){
            return returnJson;
        }
        return null;
    }


}
