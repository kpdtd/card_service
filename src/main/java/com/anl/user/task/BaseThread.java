package com.anl.user.task;

import com.anl.user.persistence.po.AutoTaskDefinition;
import com.anl.user.persistence.po.AutoTaskExecHistory;
import com.anl.user.service.AutoTaskDefinitionService;
import com.anl.user.service.AutoTaskExecHistoryService;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

public  class BaseThread {

    public static final org.slf4j.Logger logger = LogFactory.getInstance().getLogger();
    @Autowired
    AutoTaskExecHistoryService autoTaskExecHistoryService;
    @Autowired
    AutoTaskDefinitionService autoTaskDefinitionService;

    public void waitTaskFinish(Integer taskId, Date startTime,long start, int successNum, int count) throws Exception {

        AutoTaskExecHistory autoTaskExecHistory = new AutoTaskExecHistory();
        autoTaskExecHistory.setStartTime(startTime);
        autoTaskExecHistory.setCreateTime(new Date());
        autoTaskExecHistory.setTaskId(taskId);
        autoTaskExecHistory.setItemTotal(count);
        autoTaskExecHistory.setItemSuccess(successNum);
        autoTaskExecHistory.setReturnResult(count == successNum ? 1 : 3);
        autoTaskExecHistory.setEndTime(new Date());
        autoTaskExecHistory.setTimeConsuming((int)((System.currentTimeMillis()-start)/1000));
        autoTaskExecHistoryService.insert(autoTaskExecHistory);
        logger.info("添加任务执行历史、更新任务状态完成");
        AutoTaskDefinition autoTaskDefinition=autoTaskDefinitionService.getById(taskId);
        autoTaskDefinition.setExecuteState(1);//未执行
        autoTaskDefinitionService.update(autoTaskDefinition);
    }

    public void countDown(int successNum, CountDownLatch nThread, Future<Boolean> bool) {
        try {
            if (bool.get()) {
                successNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            nThread.countDown();
        }
    }
}
