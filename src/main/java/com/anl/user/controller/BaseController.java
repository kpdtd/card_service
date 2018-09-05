package com.anl.user.controller;

import com.anl.user.util.LogFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 请添加类描述
 * </p>
 * 修改记录:mvc 基类 (从这里添加，没有则删除此项) 作者 yangyiqiang 日期 2016 2016年11月4日
 */
public abstract class BaseController {

    private static final Logger logger = LogFactory.getInstance().getLogger();

    /**
     * 打印错误信息
     *
     * @param result - BindingResult
     */
    protected void printError(BindingResult result) {
        List<FieldError> fes = result.getFieldErrors();
        if (!CollectionUtils.isEmpty(fes)) {
            fes.stream().forEach(i -> {
                logger.info("FieldError : " + i.getField() + " :" + i.getDefaultMessage());
            });
        }
    }

    /**
     * 错误页面
     *
     * @param msg - 内容
     * @return String
     */
    protected String errorPage(String msg, Model model) {
        model.addAttribute("msg", msg);
        return "error";
    }

    /**
     * 将数据信息写到客户端
     *
     * @param respData
     */
    protected void writerToClient(String respData, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(respData);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从cookie获取openid
     */
    protected String getOpenIdFromCookie(HttpServletRequest request) {
        String openid = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            Optional<Cookie> cookie = Arrays.stream(cookies).filter(i -> i.getName().equals("openid")).findFirst();
            if (cookie.isPresent()) {
                openid = cookie.get().getValue();
                LogFactory.getInstance().getLogger().debug("从cookie获取到openid信息" + openid);
            } else {
                LogFactory.getInstance().getLogger().debug("从cookie没有openid信息");
            }
        }
        return openid;
    }

    /**
     * openid存储在Cookie
     * @param response
     * @param openid
     */
    protected  void wxPutCookie(HttpServletResponse response, String openid) {
        if (StringUtils.isNotBlank(openid)) {
            Cookie cookie = new Cookie("openid", openid);
            cookie.setMaxAge(365 * 24 * 60 * 60 * 1000);
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            LogFactory.getInstance().getLogger().debug("没有openid信息");
        }
    }
}
