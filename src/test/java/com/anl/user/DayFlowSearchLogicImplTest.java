package com.anl.user;

import com.anl.user.logic.DayFlowSearchLogicImpl;
import com.anl.user.persistence.po.Card;
import com.anl.user.service.CardService;
import com.anl.user.task.DayFlowSearchThread;
import com.anl.user.util.LogFactory;
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
public class DayFlowSearchLogicImplTest {

    @Autowired
    CardService cardService;
    @Autowired
    DayFlowSearchLogicImpl dayFlowSearchLogic;
    @Autowired
    DayFlowSearchThread dayFlowSearchThread;
    Card card;

    @Before
    public void setUp() throws Exception {
        card = cardService.getById(89);
    }

    @Test
    public void dealTest() throws Exception {
//       int a= dayFlowSearchLogic.dayFlowTermSearch(card,false);
//        LogFactory.getInstance().getLogger().debug("当日流量:"+a);
        dayFlowSearchThread.excute(1);
    }
}
