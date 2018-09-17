package com.anl.user.wxpublic.logic;

import com.anl.user.constant.WxPublicConstant;
import com.anl.user.util.HttpClient;
import com.anl.user.util.JsonHelper;
import com.anl.user.util.LogFactory;
import com.anl.user.wxpublic.vo.WxMenuVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyiqiang on 2018/7/18.
 * 微信公众号自定义菜单
 * 1、自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
 * 2、一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。
 * 3、创建自定义菜单后，菜单的刷新策略是，在用户进入公众号会话页或公众号profile页时，如果发现上一次拉取菜单的请求在5分钟以前，就会拉取一下菜单，如果菜单有更新，就会刷新客户端的菜单。测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
 */
@Component
public class WxMenuLogic {

    @Autowired
    WxPublicConstant wxPublicConstant;
    @Autowired
    WxGetAccessTokenLogic wxGetAccessTokenLogic;

    //创建菜单
    public boolean createMenu(List<WxMenuVo> menuVoList) throws Exception {
        boolean result = false;
        HashMap<String, Object> menuMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(menuVoList)) {
            menuMap = voToMenuMap(menuVoList);
            String url = wxPublicConstant.getWxUrl() + "/cgi-bin/menu/create?access_token=" + wxGetAccessTokenLogic.getWxAccessToken();
            String json = JsonHelper.toJson(menuMap);
            LogFactory.getInstance().getLogger().debug("微信公众号创建菜单请求的json=" + json);
            String returnJson= HttpClient.postRequest(url,json);
            if (returnJson.contains("ok")) {
                result = true;
            }
        }
        return result;
    }

    //删除菜单
    public boolean delMenu() throws Exception {
        boolean result = false;
        String url = wxPublicConstant.getWxUrl() + "/cgi-bin/menu/delete?access_token=" + wxGetAccessTokenLogic.getWxAccessToken();
        String json = HttpClient.getRequest(url);
        if (json.contains("ok")) {
            result = true;
        }
        return result;
    }

    //对象转换
    HashMap<String, Object> voToMenuMap(List<WxMenuVo> menuVoList) {
        HashMap<String, Object> menuMap = new HashMap<>();
        List buttonList = new ArrayList<>();//1级
        for (WxMenuVo wxMenuVo : menuVoList) {
            Map map = new HashMap<>();
            map.put("type", wxMenuVo.getType());
            map.put("name", wxMenuVo.getName());
            map.put("url", wxMenuVo.getUrl());
            map.put("key", wxMenuVo.getKey());
            //看是否存在子菜单
            if (CollectionUtils.isNotEmpty(wxMenuVo.getSubButton())) {
                List subButtonList = new ArrayList<>();//2级
                for (WxMenuVo subWxMenuVo : wxMenuVo.getSubButton()) {
                    Map subMap = new HashMap<>();
                    subMap.put("type", subWxMenuVo.getType());
                    subMap.put("name", subWxMenuVo.getName());
                    subMap.put("url", subWxMenuVo.getUrl());
                    subMap.put("key", subWxMenuVo.getKey());
                    subButtonList.add(subMap);
                }
                map.put("sub_button", subButtonList);
            }
            buttonList.add(map);
        }
        menuMap.put("button", buttonList);
        return menuMap;
    }
}
