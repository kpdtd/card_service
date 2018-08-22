package com.anl.user;

import com.anl.user.logic.OpenOrCloseCardLogicImpl;
import com.anl.user.persistence.po.Card;
import com.anl.user.service.CardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yangyiqiang on 2018/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml", "classpath:mybatis-config.xml"})
public class OpenOrCloseCardLogicImplTest {

    @Autowired
    CardService cardService;
    @Autowired
    OpenOrCloseCardLogicImpl openOrCloseCardLogicImpl;
    Card card;

    @Before
    public void setUp() throws Exception {
        card = cardService.getById(84);
    }
    @Test
    public void dealTest() throws Exception {
        openOrCloseCardLogicImpl.openCard(card,"测试开卡");
    }
}
