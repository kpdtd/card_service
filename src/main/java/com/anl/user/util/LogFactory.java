package com.anl.user.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFactory {

    private static LogFactory logFactory = new LogFactory();

    private LogFactory() {
    }

    public static LogFactory getInstance() {
        return logFactory;
    }

    /**
     * 获取默认的Logger，默认为控制台
     * 
     * @return Logger
     */
    public Logger getLogger() {
        return LoggerFactory.getLogger("stdout");
    }


    /**
     * 短信下发打印的日志
     */
    public Logger getSmsSendLogger() {
        return LoggerFactory.getLogger("sms_send");
    }
     
    /**
     * 支付回调日志
     * @return
     */
    public Logger getPayCallbackLogger() {
        return LoggerFactory.getLogger("payCallback");
    }

    public Logger getErrorLogger() {
        return LoggerFactory.getLogger("errorlog");
    }
    /**
     * 测试方法
     * 
     * @param args
     */
    public static void main(String[] args) {
    	
        LogFactory logFactory = LogFactory.getInstance();
        Logger log = null;
        // 循环用来测试对配置文件的监控是否生效
        for (int i = 1; i < 8; i++) {
            log = logFactory.getSmsSendLogger();
            log.debug("debug");
            log.info("info");
            log.warn("warn");
            log.error("error");
        }
    }

}
