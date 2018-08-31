package com.anl.user.account.logic;

import com.anl.user.account.dto.ChangeAccountDto;
import com.anl.user.constant.ActivityType;
import com.anl.user.logic.PkgSplitLogic;
import com.anl.user.persistence.po.FlowPacketDefinition;
import com.anl.user.persistence.po.GoodsActivity;
import com.anl.user.service.FlowPacketDefinitionService;
import com.anl.user.service.GoodsActivityService;
import com.anl.user.service.UserFlowPacketService;
import com.anl.user.util.JsonHelper;
import com.anl.user.util.LogFactory;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyiqiang on 2018/4/1.
 * 用户活动基础逻辑
 */
@Service
public class UserActivityLogicImpl implements UserActivityLogic {

    @Autowired
    GoodsActivityService goodsActivityService;
    @Autowired
    FlowPacketDefinitionService flowPacketDefinitionService;
    @Autowired
    UserAccountLogic userAccountLogic;
    @Autowired
    UserFlowPacketService userFlowPacketService;
    @Autowired
    PkgSplitLogic pkgSplitLogic;

    /**
     * ext_json={"type":1,"value":100} type=1话费,2流量,value单位为分或者赠送的流量包的ID
     *
     * @param execLogic
     * @return
     * @throws Exception
     */
    @Override
    public int userActivity(int userId, String execLogic) throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("execLogic", execLogic);
        List<GoodsActivity> goodsActivityList = goodsActivityService.getListByMap(dataMap);
        if (CollectionUtils.isNotEmpty(goodsActivityList)) {
            GoodsActivity goodsActivity = goodsActivityList.get(0);
            Map<String, Object> map = JsonHelper.toMap(goodsActivity.getExtJson());
            int type = map.get("type") == null ? 0 : Integer.parseInt(map.get("type").toString());
            int value = map.get("value") == null ? 0 : Integer.parseInt(map.get("value").toString());
            if (type > 0 && value > 0) {
                if (type == ActivityType.FLOW) {
                    //流量,直接赠送到t_user_flow_detail表
                    FlowPacketDefinition flowPacketDefinition = flowPacketDefinitionService.getById(value);
                    if (flowPacketDefinition != null) {
                        pkgSplitLogic.pkgSplit(flowPacketDefinition, userId, goodsActivity.getType() + "");
                    } else {
                        LogFactory.getInstance().getLogger().error("活动配置错误,无法找到对应的流量产品" + execLogic + ";" + goodsActivity.getExtJson());
                    }
                } else {
                    //话费
                    ChangeAccountDto changeAccountDto = new ChangeAccountDto(userId, 0, value);
                    userAccountLogic.changeAccount(changeAccountDto);
                    return value;
                }
            } else {
                LogFactory.getInstance().getLogger().error("活动配置错误," + execLogic + ";" + goodsActivity.getExtJson());
            }

        } else {
            LogFactory.getInstance().getLogger().debug("没有配置赠送活动,不需要进行赠送,execLogic=" + execLogic);
            return 0;
        }
        return -1;
    }
}