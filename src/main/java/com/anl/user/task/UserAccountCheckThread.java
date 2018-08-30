package com.anl.user.task;

import com.anl.user.dto.UserChargingEventData;
import com.anl.user.event.account.UserAccountCheckEvent;
import com.anl.user.persistence.po.User;
import com.anl.user.persistence.po.UserAccount;
import com.anl.user.service.UserAccountService;
import com.anl.user.service.UserService;
import com.anl.user.util.SeqIdGenerator;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yangyiqiang on 2018/8/24.
 * 用户账户资金监控
 */
@Component
public class UserAccountCheckThread extends BaseThread {
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    UserService userService;

    @Autowired
    ApplicationContext applicationContext;

    public void excute(int taskId) {
        ExecutorService pool = Executors.newFixedThreadPool(200);
        int count = 0;
        int num = 0;
        Date startTime = new Date();
        long start = System.currentTimeMillis();
        try {
            CountDownLatch nThread = new CountDownLatch(count); // 计数器
            List<UserAccount> userAccounts = userAccountService.getListByMoney(3);
            if (CollectionUtils.isNotEmpty(userAccounts)) {
                count = userAccounts.size();
                for (UserAccount userAccount : userAccounts) {
                    Future<Boolean> bool = pool.submit(() -> {
                        MDC.put("seqID", userAccount.getUserId() + "-" + SeqIdGenerator.generate());// 日志序列
                        User user = userService.getById(userAccount.getUserId());
                        UserChargingEventData data = new UserChargingEventData();
                        data.setUser(user);
                        applicationContext.publishEvent(new UserAccountCheckEvent(data));
                        return true;
                    });
                    countDown(num, nThread, bool);
                }
                nThread.await(20, TimeUnit.SECONDS);
                logger.info("用户账户资金监控");
                waitTaskFinish(taskId, startTime, start, num, count);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
