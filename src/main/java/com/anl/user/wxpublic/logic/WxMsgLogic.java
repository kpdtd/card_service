package com.anl.user.wxpublic.logic;

import com.csxm.flow.iot.util.LogFactory;
import com.csxm.flow.iot.util.XmlUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangyiqiang on 2018/7/18.
 * 消息接收,回复
 */
@Component
public class WxMsgLogic {
    public String readMeg(String xmlData) throws Exception {
        String xml = "";
        HashMap<String, Object> data = XmlUtil.fromXml(xmlData, HashMap.class);
        //接收到文本消息
        if (data.get("MsgType").equals("text")) {
            xml = goBackTextMsg(data);
        }
        LogFactory.getInstance().getLogger().debug("消息回复xml="+xml);
        return xml;
    }


    //信息回复,调用对应的业务逻辑,进行返回信息的封装
    public String goBackTextMsg(HashMap<String, Object> data) {
        String xml = "";
        Map<String,Object> map=new HashMap<>();
        map.put("ToUserName",cdataString(data.get("FromUserName").toString()));
        map.put("FromUserName",cdataString(data.get("ToUserName").toString()));
        map.put("CreateTime", System.currentTimeMillis()/1000);
        map.put("MsgType",cdataString("text"));
        map.put("Content",cdataString("哈哈,我成功回复了你"));
        xml=XmlUtil.getXmlFromMap(map,"xml");
        return xml.substring(xml.indexOf("<xml>"));
    }

    private String cdataString(String str){
        return "<![CDATA["+str+"]]>";
    }
}
