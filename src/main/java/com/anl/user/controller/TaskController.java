package com.anl.user.controller;

import com.anl.user.persistence.po.AutoTaskDefinition;
import com.anl.user.service.AutoTaskDefinitionService;
import com.anl.user.task.DayFlowSearchThread;
import com.anl.user.task.PreUserFlowCheckThread;
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
    PreUserFlowCheckThread preUserFlowCheckThread;
    @Autowired
    AutoTaskDefinitionService autoTaskDefinitionService;
    ReentrantLock lock = new ReentrantLock();

    ReentrantLock preUserLock = new ReentrantLock();

    //日流量查询任务触发
    @RequestMapping(value = "/dayFlowSearchTask")
    @ResponseBody
    public String dayFlowSearchTask(@RequestBody String body, HttpServletRequest request) {
        String result = "success";
        if (lock.isLocked()) {
            return "error";
        }
        Integer taskId = 0;
        if (StringUtils.isNotBlank(body)) {
            taskId = JsonHelper.toMap(body).get("taskId") == null ? 0 : Integer.parseInt(JsonHelper.toMap(body).get("taskId").toString());
        }
        if (taskId > 0) {
            lock.lock();
            try {
                if (updateTaskState(taskId)) {
                    final Integer t = taskId;
                    new Thread(() -> dayFlowSearchThread.excute(t)).start();
                } else {
                    result = "error";
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        return result;
    }

    //预生成用户流量使用监控
    @RequestMapping(value = "/preUserCheckTask")
    @ResponseBody
    public String preUserCheckTask(@RequestBody String body, HttpServletRequest request) {
        String result = "success";
        if (preUserLock.isLocked()) {
            return "error";
        }
        Integer taskId = 0;
        if (StringUtils.isNotBlank(body)) {
            taskId = JsonHelper.toMap(body).get("taskId") == null ? 0 : Integer.parseInt(JsonHelper.toMap(body).get("taskId").toString());
        }
        if (taskId > 0) {
            preUserLock.lock();
            try {
                if (updateTaskState(taskId)) {
                    final Integer t = taskId;
                    new Thread(() -> preUserFlowCheckThread.excute(t)).start();
                } else {
                    result = "error";
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                preUserLock.unlock();
            }
        }
        return result;
    }

    private boolean updateTaskState(int taskId) {
        try {
            AutoTaskDefinition autoTaskDefinition = autoTaskDefinitionService.getById(taskId);
            if (autoTaskDefinition.getExecuteState() == 2) {
                return false;
            }
            if (autoTaskDefinition.getExecuteState() == 1) {
                autoTaskDefinition.setExecuteState(2);
                autoTaskDefinitionService.update(autoTaskDefinition);//修改成正在执行
            }
            final Integer t = taskId;
            new Thread(() -> dayFlowSearchThread.excute(t)).start();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
