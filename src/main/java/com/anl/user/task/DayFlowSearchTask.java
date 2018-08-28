package com.anl.user.task;

import com.anl.user.persistence.po.AutoTaskDefinition;
import com.anl.user.service.AutoTaskDefinitionService;
import com.anl.user.util.DateUtil;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangyiqiang on 2018/8/24.
 */
@Component
@EnableScheduling
public class DayFlowSearchTask implements SchedulingConfigurer {

    @Autowired
    DayFlowSearchThread dayFlowSearchThread;
    @Autowired
    AutoTaskDefinitionService autoTaskDefinitionService;
    //时间表达式  每2秒执行一次
    private String cron = "0/2 * * * * ?";
    private int taskId=1;
    public void setCron(String cron) {
        this.cron = cron;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        try {
            AutoTaskDefinition autoTaskDefinition=autoTaskDefinitionService.getById(taskId);
            if(autoTaskDefinition==null){
                LogFactory.getInstance().getLogger().error("流量使用日查询任务无法启动,没有找到相关任务配置");
                return;
            }
            setCron(autoTaskDefinition.getTaskTimeCron());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        taskRegistrar.addTriggerTask(new taskThread(taskId), new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger cronTrigger = new CronTrigger(cron);
                Date nextExecDate = cronTrigger.nextExecutionTime(triggerContext);
                return nextExecDate;
            }
        });
    }

    class taskThread implements Runnable {
        private int taskId;
        public taskThread(int taskId){
            taskId=taskId;
        }
        @Override
        public void run() {
            System.out.println("流量使用日查询任务执行"+ DateUtil.getCurDateTime());
            dayFlowSearchThread.excute(taskId);
        }
    }
}
