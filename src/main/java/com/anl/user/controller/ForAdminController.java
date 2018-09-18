package com.anl.user.controller;

import com.anl.user.logic.OpenOrCloseCardLogicImpl;
import com.anl.user.persistence.po.Card;
import com.anl.user.util.JsonHelper;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 提供给admin管理平台使用,停开机,激活等操作
 */
@Controller
@RequestMapping(value = "/anl")
public class ForAdminController extends BaseController {

    @Autowired
    OpenOrCloseCardLogicImpl openOrCloseCardLogic;

    //开机
    @RequestMapping(value = "/openCard")
    @ResponseBody
    public String openCard(@RequestBody String body, HttpServletRequest request) {
        String result = "error";
        try {
            Card card = JsonHelper.toObject(body, Card.class);
            if (openOrCloseCardLogic.openCard(card, "admin操作开机")) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogFactory.getInstance().getLogger().error("admin操作开机异常"+e.getMessage());
        }
        return result;
    }
    //关机
    @RequestMapping(value = "/closeCard")
    @ResponseBody
    public String closeCard(@RequestBody String body, HttpServletRequest request) {
        String result = "error";
        try {
            Card card = JsonHelper.toObject(body, Card.class);
            if (openOrCloseCardLogic.closeCard(card, "admin操作关机")) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogFactory.getInstance().getLogger().error("admin操作关机异常"+e.getMessage());
        }
        return result;
    }
}
