package com.anl.user.controller;

import com.anl.user.task.DayFlowSearchTask;
import com.anl.user.util.JsonHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangyiqiang on 2018/8/24.
 * 任务配置参数重置
 */
@Controller
@RequestMapping(value = "/anl")
public class TaskCronController extends BaseController {

    @Autowired
    DayFlowSearchTask dayFlowSearchTask;

    //日流量查询任务执行时间点重置
    @RequestMapping(value = "/dayFlowSearchTaskCron")
    @ResponseBody
    public String dayFlowSearchTaskCron(@RequestBody String body, HttpServletRequest request) {
        String cron = "";
        if (StringUtils.isNotBlank(body)) {
            cron = JsonHelper.toMap(body).get("cron") == null ? "" : JsonHelper.toMap(body).get("cron").toString();
        }
        if (StringUtils.isNotBlank(cron)) {
            dayFlowSearchTask.setCron(cron);
            return "success";
        }
        return "error";
    }

}
