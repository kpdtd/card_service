package com.anl.user;

import com.anl.user.task.DayFlowSearchTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by yangyiqiang on 2018/8/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml", "classpath:mybatis-config.xml"})
public class BaseTaskTest {
    @Autowired
    DayFlowSearchTask dayFlowSearchTask;
    @Test
    public void DayFlowSearchTaskTest(){
        try {
            Thread.sleep(100);
            //dayFlowSearchTask.setCron("0/10 * * * * ?");
            Thread.sleep(80000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
