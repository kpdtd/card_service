package com.anl.user.logic;

import com.anl.user.persistence.po.Card;
import com.anl.user.persistence.po.InterfaceList;
import com.anl.user.persistence.po.Supplier;
import com.anl.user.persistence.po.SupplierInterfaceItem;
import com.anl.user.service.*;
import com.anl.user.util.LogFactory;
import com.sup.pack.constant.CardState;
import com.sup.pack.constant.ProcessAction;
import com.sup.pack.dto.SupplierReqData;
import com.sup.pack.logic.CardStateUpdateLogicImpl;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyiqiang on 2018/8/22.
 */
@Service
public class OpenOrCloseCardLogicImpl implements OpenOrCloseCardLogic {

    @Autowired
    CardService cardService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    InterfaceListService interfaceListService;
    @Autowired
    SupplierInterfaceItemService supplierInterfaceItemService;
    @Autowired
    CardStateUpdateLogicImpl cardStateUpdateLogic;
    @Autowired
    CardStateHistroyService cardStateHistroyService;

    Logger logger = LogFactory.getInstance().getLogger();

    @Override
    public boolean openCard(Card card, String reason) throws Exception {
        Integer originalState = card.getCardState();
        boolean result = toSup(ProcessAction.CARD_OPEN, card);
        if (result) {
            card.setCardState(CardState.CARD_USING);
            cardStateHistroyService.insertHistoryByIotCard(card, originalState, reason);
        }
        return result;
    }

    @Override
    public boolean closeCard(Card card, String reason) throws Exception {
        Integer originalState = card.getCardState();
        boolean result = toSup(ProcessAction.CARD_CLOSE, card);
        if (result) {
            card.setCardState(CardState.CELL_PHONE_DOWN);
            cardStateHistroyService.insertHistoryByIotCard(card, originalState, reason);
        }
        return result;
    }

    boolean toSup(String action, Card card) throws Exception {
        boolean result = false;
        Supplier supplier = supplierService.getById(card.getSupplierId());
        if (supplier == null) {
            logger.error("流量卡开关卡操作错误,没有对应的上游信息,SupplierId=" + card.getSupplierId());
            return false;
        }
        InterfaceList interfaceList = new InterfaceList();
        interfaceList.setTag(action);
        List<InterfaceList> interfaceListList = interfaceListService.getListByPo(interfaceList);
        if (CollectionUtils.isEmpty(interfaceListList)) {
            logger.error("流量卡开关卡操作错误,没有定义对应的接口TAG=" + action);
            return false;
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("supplierId", card.getSupplierId());
        dataMap.put("interfaceId", interfaceListList.get(0).getId());
        List<SupplierInterfaceItem> supplierInterfaceItems = supplierInterfaceItemService.getListByMap(dataMap);
        if (CollectionUtils.isEmpty(supplierInterfaceItems)) {
            logger.error("流量卡开关卡操作错误,该上游没有对应的接口实现,SupplierId=" + card.getSupplierId());
            return false;
        }
        SupplierInterfaceItem item = supplierInterfaceItems.get(0);
        SupplierReqData supplierReqData = new SupplierReqData();
        supplierReqData.setImsi(card.getImsi());
        supplierReqData.setIccid(card.getIccid());
        supplierReqData.setMsisdn(card.getMsisdn());
        supplierReqData.setAction(action);
        supplierReqData.setInterfaceInfo(item.getInterfaceInfo());
        supplierReqData.setCardId(card.getId());
        supplierReqData.setCardState(card.getCardState());
        supplierReqData.setClassName(item.getClassName());
        supplierReqData.setEcCode(supplier.getEcCode());
        supplierReqData.setEcExtensionInfo(supplier.getEcExtensionInfo());
        supplierReqData.setInvokeToken(supplier.getInvokeToken());
        supplierReqData.setSignKey(supplier.getSignKey());
        supplierReqData.setUrl(item.getUrl());
        result = cardStateUpdateLogic.deal(supplierReqData);
        return result;
    }
}
