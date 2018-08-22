package com.anl.user.logic;

import com.anl.user.persistence.po.*;
import com.anl.user.service.*;
import com.anl.user.util.DateUtil;
import com.anl.user.util.LogFactory;
import com.sup.pack.constant.ProcessAction;
import com.sup.pack.dto.FlowDailyData;
import com.sup.pack.dto.SupplierReqData;
import com.sup.pack.logic.CardFlowDayUsedLogicImpl;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyiqiang on 2018/8/10.
 * 流量卡日流量使用查询,根据任务配置的时间点从上游获取流量使用情况,并更新流量日使用表
 */
@Service
public class DayFlowSearchLogicImpl implements DayFlowSearchLogic {
    @Autowired
    CardService cardService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    SupplierInterfaceItemService supplierInterfaceItemService;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    InterfaceListService interfaceListService;
    @Autowired
    UserFlowUsedDayService userFlowUsedDayService;
    @Autowired
    UserFlowUsedMonthService userFlowUsedMonthService;
    @Autowired
    RealnameAuthenticationService realnameAuthenticationService;
    @Autowired
    CardFlowDayUsedLogicImpl cardFlowDayUsedLogic;
    Logger logger = LogFactory.getInstance().getLogger();

    /**
     * 日流量实时查询,如果fee为true,需要入库,进行扣费操作,为false仅仅查询返回当天用量
     * @param card
     * @param fee
     * @return -1为查询失败.>=0为当日使用量
     * @throws Exception
     */
    @Override
    public int dayFlowTermSearch(Card card,boolean fee) throws Exception {

        boolean isEarlyMonth = DateUtil.isEarlyMonth();
        String action = ProcessAction.FLOW_DAY_SEARCH;
        InterfaceList interfaceList = new InterfaceList();
        interfaceList.setTag(action);//日流量查询的tag
        List<InterfaceList> interfaceListList = interfaceListService.getListByPo(interfaceList);
        if (CollectionUtils.isEmpty(interfaceListList)) {
            logger.error("流量卡日流量使用查询操作错误,没有定义对应的接口TAG=" + action);
            return -1;
        }
        Supplier supplier = supplierService.getById(card.getSupplierId());
        if (supplier == null) {
            logger.error("流量卡日流量使用查询操作错误,没有对应的上游信息,SupplierId=" + card.getSupplierId());
            return -1;
        }
        //这里返回的是列表，应该加一个参数：接口标识串，直接定位到具体的接口信息
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("supplierId", card.getSupplierId());
        dataMap.put("interfaceId", interfaceListList.get(0).getId());
        List<SupplierInterfaceItem> supplierInterfaceItems = supplierInterfaceItemService.getListByMap(dataMap);
        if (CollectionUtils.isEmpty(supplierInterfaceItems)) {
            logger.error("流量卡日流量使用查询操作错误,该上游没有对应的接口实现,SupplierId=" + card.getSupplierId());
            return -1;
        }
        SupplierInterfaceItem item = supplierInterfaceItems.get(0);
        logger.info("流量卡日流量使用查询,调用的上游类" + item.getClassName());
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
        // 当前月用户在我平台的已使用总量
        Integer usedFlow = userFlowUsedDayService.getBeforeUsedFlow(card.getId(), DateUtil.getFristDayOfMonth(), DateUtil.string2Date(DateUtil.dateToString(new Date(), DateUtil.DATE_FORMAT_COMPACT)));
        usedFlow = (usedFlow == null ? 0 : usedFlow);
        if(usedFlow>0){
            supplierReqData.setUsedFlow(usedFlow);
        }
        FlowDailyData flowDailyData = cardFlowDayUsedLogic.deal(supplierReqData);
        if(fee) {
            addDayFlow(card, flowDailyData);
            addMonthUsedFlow(card, flowDailyData, isEarlyMonth);
        }
        return flowDailyData.getDayUsedFlow();
    }


    //如果当天存在记录,不做任何操作
    private void addDayFlow(Card card, FlowDailyData flowDailyData) throws Exception {
        if (flowDailyData == null || flowDailyData.getDayUsedFlow() == null || flowDailyData.getDayUsedFlow() == 0) {
            logger.info("日流量表未增加iccid={}的记录,本次查询之后的使用量为{}", card.getIccid(), flowDailyData.getDayUsedFlow());
            return;
        }
        UserFlowUsedDay userFlowUsedDay = new UserFlowUsedDay();
        Date recordTime = DateUtil.string2Date(flowDailyData.getDay());
        userFlowUsedDay.setRecordTime(recordTime);
        userFlowUsedDay.setCardId(card.getId());
        List<UserFlowUsedDay> userFlowUsedDays = userFlowUsedDayService.getListByPo(userFlowUsedDay);
        if (CollectionUtils.isEmpty(userFlowUsedDays)) {
            userFlowUsedDay.setFlow(flowDailyData.getDayUsedFlow());
            userFlowUsedDay.setCreateTime(new Date());
            userFlowUsedDayService.insert(userFlowUsedDay);
            logger.info("日流量表增加了iccid={}的记录,本次查询之后的使用量为{}", card.getIccid(), flowDailyData.getDayUsedFlow());
        } else {
//            userFlowUsedDay = userFlowUsedDays.get(0);
//            int oldDayFlow = userFlowUsedDay.getFlow();
//            userFlowUsedDay.setFlow(flowDailyData.getDayUsedFlow());
//            userFlowUsedDay.setCreateTime(new Date());
//            userFlowUsedDayService.update(userFlowUsedDay);
//            logger.info("日流量表存在iccid={}的记录,使用量为{},本次查询之后的使用量为{}", card.getIccid(), oldDayFlow, flowDailyData.getDayUsedFlow());
        }
    }

    //如果存在当月的记录,直接返回
    private void addMonthUsedFlow(Card card, FlowDailyData flowDailyData, boolean isEarlyMonth) throws Exception {
        if (flowDailyData == null || flowDailyData.getDayUsedFlow() == null || flowDailyData.getMonthUsedFlow() == 0) {
            logger.info("月流量表未增加iccid={}的记录,本次查询之后的使用量为{}", card.getIccid(), flowDailyData.getDayUsedFlow());
            return;
        }
        UserFlowUsedMonth userFlowUsedMonth = new UserFlowUsedMonth();
        userFlowUsedMonth.setCardId(card.getId());
        if (isEarlyMonth) {
            userFlowUsedMonth.setBillDate(DateUtil.getFirstDayOfBeforeMonth());
        } else {
            userFlowUsedMonth.setBillDate(DateUtil.getFristDayOfMonth());
        }
        List<UserFlowUsedMonth> userFlowUsedMonths = userFlowUsedMonthService.getListByPo(userFlowUsedMonth);
        if (CollectionUtils.isEmpty(userFlowUsedMonths)) {
            userFlowUsedMonth.setCreateTime(new Date());
            userFlowUsedMonth.setActualUse(flowDailyData.getMonthUsedFlow());
            userFlowUsedMonth.setBatchNumber(DateUtil.getCurDateTime(DateUtil.DATE_FORMAT_COMPACT));
            userFlowUsedMonth.setCardState(card.getCardState());
            userFlowUsedMonth.setIccid(card.getIccid());
            userFlowUsedMonth.setSuplierId(card.getSupplierId());
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("cardId", card.getId());
            List<RealnameAuthentication> raList = realnameAuthenticationService.getListByMap(dataMap);
            if (!raList.isEmpty()) {
                userFlowUsedMonth.setPhone(raList.get(0).getPhone());
            }
            userFlowUsedMonthService.insert(userFlowUsedMonth);
            logger.info("月流量表增加iccid={}的记录,本次查询的使用量为:{}", card.getIccid(), flowDailyData.getMonthUsedFlow());
        } else {
            logger.info("月流量表存在iccid={}的记录", card.getIccid());
        }
    }

}
