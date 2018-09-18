package com.anl.user;

import com.anl.user.constant.WxPublicConstant;
import com.anl.user.wxpublic.logic.WxTemplateMsgLogic;
import com.anl.user.wxpublic.vo.WxSubTemplateVo;
import com.anl.user.wxpublic.vo.WxTemplateVo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangyiqiang on 2018/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"classpath*:spring/applicationContext.xml"})
public class WxTemplateMsgLogicTest {

    @Autowired
    WxTemplateMsgLogic wxTemplateMsgLogic;
    @Autowired
    WxPublicConstant wxPublicConstant;
    private WxTemplateVo wxTemplateVo;
    private String openId;
    @Before
    public void setUp(){
        openId="oH-4a083z_5da29U9Q_ETCEhomvI";//杨益强的
        wxTemplateVo=new WxTemplateVo();
        wxTemplateVo.setAppid(wxPublicConstant.getAppId());
        wxTemplateVo.setTemplate_id("Pb8S-zc-FD2whGqzS0BNQTLPyMkKBZ2ONHYf13FeGo8");
        wxTemplateVo.setTouser(openId);
        wxTemplateVo.setUrl("http://baidu.com");
        WxSubTemplateVo wxSubTemplateVo=new WxSubTemplateVo();
        wxSubTemplateVo.setValue("欢迎你使用测试微信");
        Map<String,WxSubTemplateVo> urlMap=new HashMap<>();
        urlMap.put("url",wxSubTemplateVo);

        WxSubTemplateVo wxSubTemplateVo2=new WxSubTemplateVo();
        wxSubTemplateVo2.setValue("dianwo");
        wxSubTemplateVo2.setColor("#173177");
        urlMap.put("content",wxSubTemplateVo2);
        wxTemplateVo.setData(urlMap);

    }
    @After
    public void tearDown() {

    }
    @Test
    public void handle() throws Exception {
        wxTemplateMsgLogic.sendWxTemplateMsg(wxTemplateVo);
    }
}
