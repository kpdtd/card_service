package com.anl.user.controller;

import com.anl.user.util.LogFactory;
import org.slf4j.Logger;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
}
