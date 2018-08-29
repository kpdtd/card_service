package com.anl.user.controller;

import com.anl.user.persistence.po.AutoTaskDefinition;
import com.anl.user.service.AutoTaskDefinitionService;
import com.anl.user.task.DayFlowSearchThread;
import com.anl.user.util.JsonHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yangyiqiang on 2018/8/24.
 * 任务配置参数重置
 */
@Controller
@RequestMapping(value = "/anl")
public class TaskController extends BaseController {

    @Autowired
    DayFlowSearchThread dayFlowSearchThread;
    @Autowired
    AutoTaskDefinitionService autoTaskDefinitionService;
    ReentrantLock lock = new ReentrantLock();

    //日流量查询任务执行时间点重置
    @RequestMapping(value = "/dayFlowSearchTask")
    @ResponseBody
    public String dayFlowSearchTaskCron(@RequestBody String body, HttpServletRequest request) {
        if (lock.isLocked()) {
            return "error";
        }
        Integer taskId = 0;
        if (StringUtils.isNotBlank(body)) {
            taskId = JsonHelper.toMap(body).get("taskId") == null ? 0 : Integer.parseInt(JsonHelper.toMap(body).get("cron").toString());
        }
        if (taskId > 0) {
            lock.lock();
            try {
                AutoTaskDefinition autoTaskDefinition = autoTaskDefinitionService.getById(taskId);
                if (autoTaskDefinition.getExecuteState() == 1) {
                    autoTaskDefinition.setExecuteState(2);
                    autoTaskDefinitionService.update(autoTaskDefinition);//修改成正在执行
                }
                final Integer t = taskId;
                new Thread(() -> dayFlowSearchThread.excute(t)).start();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        return "success";
    }

}
