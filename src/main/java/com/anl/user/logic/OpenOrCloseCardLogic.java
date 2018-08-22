package com.anl.user.logic;

import com.anl.user.persistence.po.Card;

/**
 * Created by yangyiqiang on 2018/8/10.
 */
public interface OpenOrCloseCardLogic {

    //开卡
    public boolean openCard(Card card,String reason) throws Exception;
    //关卡
    public boolean closeCard(Card card,String reason) throws Exception;

}
