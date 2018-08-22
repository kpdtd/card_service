package com.anl.user.logic;

import com.anl.user.persistence.po.Card;

/**
 * Created by yangyiqiang on 2018/8/10.
 */
public interface DayFlowSearchLogic {

    //日流量实时查询
    public int dayFlowTermSearch(Card card,boolean fee) throws Exception;

}
