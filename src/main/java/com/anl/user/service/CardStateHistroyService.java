package com.anl.user.service;

import com.anl.user.persistence.po.Card;
import com.anl.user.persistence.po.CardStateHistroy;

/**
 * 类名: CardStateHistroyService
 * 创建日期:
 * 功能描述:
 */
public interface CardStateHistroyService extends BaseService<CardStateHistroy> {

    void insertHistoryByIotCard(Card card, Integer originalState, String operateInfo) throws Exception;
}