package com.anl.user;

import com.anl.user.constant.WxPublicConstant;
import com.anl.user.wxpublic.logic.WxGetAccessTokenLogic;
import com.anl.user.wxpublic.logic.WxMenuLogic;
import com.anl.user.wxpublic.vo.WxMenuVo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyiqiang on 2018/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml", "classpath:mybatis-config.xml"})
public class WxMenuLogicTest {
    @Autowired
    WxGetAccessTokenLogic wxGetAccessTokenLogic;
    @Autowired
    WxPublicConstant wxPublicConstant;
    @Autowired
    WxMenuLogic wxMenuLogic;
    private List<WxMenuVo> menuVoList;

    @Before
    public void setUp() {
        menuVoList=new ArrayList<>();
        //1
        WxMenuVo w1 = new WxMenuVo();//1级
        w1.setName("开通");
        w1.setKey("kaitong");
        List<WxMenuVo> sub1 = new ArrayList<>();
        WxMenuVo sub1_1 = new WxMenuVo();
        sub1_1.setName("开通激活");
        sub1_1.setType("view");
        sub1_1.setUrl("http://baidu.com");
        WxMenuVo sub1_2 = new WxMenuVo();
        sub1_2.setName("申请流量卡");
        sub1_2.setType("view");
        sub1_2.setUrl("http://baidu.com");
        WxMenuVo sub1_3 = new WxMenuVo();
        sub1_3.setName("开通帮助");
        sub1_3.setType("view");
        sub1_3.setUrl("http://baidu.com");
        sub1.add(sub1_1);
        sub1.add(sub1_2);
        sub1.add(sub1_3);
        w1.setSubButton(sub1);

        //2
        WxMenuVo w2 = new WxMenuVo();//1级
        w2.setName("充值");
        w2.setKey("chongzhi");
        List<WxMenuVo> sub2 = new ArrayList<>();
        WxMenuVo sub2_1 = new WxMenuVo();
        sub2_1.setName("充值");
        sub2_1.setType("view");
        sub2_1.setUrl("http://baidu.com");

        WxMenuVo sub2_2 = new WxMenuVo();
        sub2_2.setName("送50活动介绍");
        sub2_2.setType("view");
        sub2_2.setUrl("http://baidu.com");
        sub2.add(sub2_1);
        sub2.add(sub2_2);
        w2.setSubButton(sub2);

        //3
        WxMenuVo w3 = new WxMenuVo();//1级
        w3.setName("服务");
        w3.setKey("fuwu");

        List<WxMenuVo> sub3 = new ArrayList<>();
        WxMenuVo sub3_1 = new WxMenuVo();
        sub3_1.setName("余额查询");
        sub3_1.setType("view");
        sub3_1.setUrl("http://baidu.com");
        sub3.add(sub3_1);
        w3.setSubButton(sub3);
        menuVoList.add(w1);
        menuVoList.add(w2);
        menuVoList.add(w3);
    }

    @After
    public void tearDown() {
        try {
            wxMenuLogic.delMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createTest() throws Exception {
       boolean r= wxMenuLogic.createMenu(menuVoList);
        Assert.assertTrue(r);
    }

    @Test
    public void delTest() throws Exception {
        boolean r= wxMenuLogic.delMenu();
        Assert.assertTrue(r);
    }
}
