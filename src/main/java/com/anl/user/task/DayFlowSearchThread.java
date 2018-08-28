package com.anl.user.task;

import com.anl.user.dto.UserChargingEventData;
import com.anl.user.event.UserChargingEvent;
import com.anl.user.logic.DayFlowSearchLogicImpl;
import com.anl.user.persistence.po.Card;
import com.anl.user.persistence.po.User;
import com.anl.user.service.CardService;
import com.anl.user.service.UserService;
import com.anl.user.util.SeqIdGenerator;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by yangyiqiang on 2018/8/24.
 */
@Component
public class DayFlowSearchThread extends BaseThread{
    @Autowired
    UserService userService;
    @Autowired
    CardService cardService;
    @Autowired
    DayFlowSearchLogicImpl dayFlowSearchLogic;
    @Autowired
    ApplicationContext applicationContext;

    public void excute(int taskId) {
        ExecutorService pool = Executors.newFixedThreadPool(200);//日流量查询
        try {
            //全量查询,对状态为3和4的用户计费
            Map<String, Object> dataMap = new HashMap<>();
            int count = userService.count(dataMap);
            int num=0;
            CountDownLatch nThread = new CountDownLatch(count); // 计数器
            int pageTotal = 10000; // 每次取得量
            int page = count / pageTotal + (count % pageTotal > 0 ? 1 : 0);
            Date startTime = new Date();
            long start=System.currentTimeMillis();
            for (int i = 0; i < page; i++) {
                dataMap.put("startPage", i * pageTotal);
                dataMap.put("pageSize", pageTotal);
                List<User> userList = userService.getListByMap(dataMap);
                for (User user : userList) {
                    Future<Boolean> bool = pool.submit(() -> {
                        MDC.put("seqID", user.getIccid() + "-" + SeqIdGenerator.generate());// 日志序列
                        Card card = cardService.getById(user.getCardId());
                        //返回值小于0,查询失败,大于=0为查询到的流量
                        int execResult = dayFlowSearchLogic.dayFlowTermSearch(card, true);
                        // 开始下一步计费处理
                        UserChargingEventData data = new UserChargingEventData();
                        data.setUser(user);
                        data.setCard(card);
                        applicationContext.publishEvent(new UserChargingEvent(data));
                        return execResult >= 0 ? true : false;
                    });
                    countDown(num, nThread, bool);
                }
            }
            nThread.await(20, TimeUnit.SECONDS);
            logger.info("日流量查询任务完成");
            waitTaskFinish(taskId, startTime,start, num, count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
