package com.anl.user.task;

import com.anl.user.constant.UserState;
import com.anl.user.dto.UserChargingEventData;
import com.anl.user.event.charge.UserChargingEvent;
import com.anl.user.event.preUser.PreUserFlowCheckEvent;
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
 * 预生成用户流量监控
 */
@Component
public class PreUserFlowCheckThread extends BaseThread{
    @Autowired
    UserService userService;
    @Autowired
    CardService cardService;
    @Autowired
    DayFlowSearchLogicImpl dayFlowSearchLogic;
    @Autowired
    ApplicationContext applicationContext;

    public void excute(int taskId) {
        ExecutorService pool = Executors.newFixedThreadPool(200);
        try {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("state", UserState.PRE_USER);
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
                        int execResult = dayFlowSearchLogic.dayFlowTermSearch(card, false);

                        UserChargingEventData data = new UserChargingEventData();
                        data.setUser(user);
                        data.setCard(card);
                        data.setDayFlow(execResult);//当日使用的流量
                        applicationContext.publishEvent(new PreUserFlowCheckEvent(data));
                        return execResult >= 0 ? true : false;
                    });
                    countDown(num, nThread, bool);
                }
            }
            nThread.await(20, TimeUnit.SECONDS);
            logger.info("预生成用户流量监控任务完成");
            waitTaskFinish(taskId, startTime,start, num, count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
