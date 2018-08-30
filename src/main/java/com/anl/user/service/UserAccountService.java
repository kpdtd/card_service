package com.anl.user.service;

import com.anl.user.persistence.po.User;
import com.anl.user.persistence.po.UserAccount;
import com.anl.user.persistence.po.UserAccountChangeHistory;

import java.util.List;

/**
 * 类名: UserAccountService
 * 创建日期:
 * 功能描述:
 */
public interface UserAccountService extends BaseService<UserAccount> {
    int updateBalance(User user, UserAccountChangeHistory userAccountChangeHistory, int money) throws Exception;

    List<UserAccount> getListByMoney(Integer money) throws Exception;
}