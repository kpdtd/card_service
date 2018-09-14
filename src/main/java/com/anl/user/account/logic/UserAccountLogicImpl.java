package com.anl.user.account.logic;

import com.anl.user.account.dto.ChangeAccountDto;
import com.anl.user.constant.AccountConstants;
import com.anl.user.constant.SystemErrorCode;
import com.anl.user.dto.ActionResult;
import com.anl.user.pay.logic.UserRefundRecordLogic;
import com.anl.user.persistence.po.UserAccount;
import com.anl.user.persistence.po.UserAccountChangeHistory;
import com.anl.user.service.UserAccountChangeHistoryService;
import com.anl.user.service.UserAccountService;
import com.anl.user.service.UserChargeRecordService;
import com.anl.user.service.UserRefundRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
public class UserAccountLogicImpl implements UserAccountLogic {

    @Autowired
    UserAccountService userAccountService;
    @Autowired
    UserAccountChangeHistoryService userAccountChangeHistoryService;
    @Autowired
    UserRefundRecordLogic userRefundRecordLogic;
    @Autowired
    UserChargeRecordService userChargeRecordService;
    @Autowired
    UserRefundRecordService userRefundRecordService;

    /**
     * @param dto
     * @return
     * @throws SQLException
     */
    @Transactional
    @Override
    public ActionResult changeAccount(ChangeAccountDto dto) throws SQLException {
        UserAccount userAccount=userAccountService.getUserAccountByUserId(dto.getUserId());
        UserAccountChangeHistory userAccountChangeHistory = new UserAccountChangeHistory();
        if (userAccount != null) {
            //有帐户的直接更改帐户
            //添加帐户变更日志
            userAccountChangeHistory.setUserId(userAccount.getUserId());
            userAccountChangeHistory.setCreateTime(new Date());
            userAccountChangeHistory.setPaChangeBefore(userAccount.getPrimaryAccount());
            userAccountChangeHistory.setPaChangeAfter(userAccount.getPrimaryAccount());
            userAccountChangeHistory.setSaChangeBefore(userAccount.getSubAccount());
            userAccountChangeHistory.setSaChangeAfter(userAccount.getSubAccount());

            //先判断资账户是否为增加
            if (dto.getSubMoney() > 0) {
                //子帐户增加
                userAccount.setSubAccount(userAccount.getSubAccount() + dto.getSubMoney());
                userAccount.setUpdateTime(new Date());
                userAccountService.update(userAccount);
                userAccountChangeHistory.setSaChangeAfter(userAccount.getSubAccount());
                userAccountChangeHistory.setType(AccountConstants.SA_ACCOUNT_CHANGE_ADD);
                userAccountChangeHistory.setSource(dto.getSource());
                userAccountChangeHistoryService.insert(userAccountChangeHistory);
            } else if (dto.getPrimaryMoney() < 0) {
                //需要扣款
                if ((userAccount.getPrimaryAccount() + userAccount.getSubAccount()) < Math.abs(dto.getPrimaryMoney())) {
                    //子帐户金额＋主帐户金额小于要扣的金额
                    //扣款余额不足
                    return ActionResult.fail(SystemErrorCode.balanceError.getCode());
                }
                if (userAccount.getPrimaryAccount() >= Math.abs(dto.getPrimaryMoney())) {
                    //主账户金额大于要扣除的金额，直接扣除
                    userAccount.setPrimaryAccount(userAccount.getPrimaryAccount() + dto.getPrimaryMoney());
                    userAccount.setUpdateTime(new Date());
                    userAccountService.update(userAccount);
                    userAccountChangeHistory.setPaChangeAfter(userAccount.getPrimaryAccount());
                    userAccountChangeHistory.setType(AccountConstants.PA_ACCOUNT_CHANGE_SUB);
                    userAccountChangeHistory.setSource(dto.getSource());
                    userAccountChangeHistoryService.insert(userAccountChangeHistory);
                } else if (userAccount.getPrimaryAccount() < Math.abs(dto.getPrimaryMoney())) {
                    //主账户金额小于要扣除的金额，将主账户扣除到零，剩余的从子帐户中扣除
                    userAccount.setSubAccount(userAccount.getSubAccount() + userAccount.getPrimaryAccount() + dto.getPrimaryMoney());
                    userAccount.setPrimaryAccount(0);
                    userAccount.setUpdateTime(new Date());
                    userAccountService.update(userAccount);
                    userAccountChangeHistory.setPaChangeAfter(userAccount.getPrimaryAccount());
                    userAccountChangeHistory.setSaChangeAfter(userAccount.getSubAccount());
                    userAccountChangeHistory.setType(AccountConstants.PA_ACCOUNT_CHANGE_SUB);
                    userAccountChangeHistory.setSource(dto.getSource());
                    userAccountChangeHistoryService.insert(userAccountChangeHistory);
                } else {
                    return ActionResult.fail();
                }
            } else if (dto.getSubMoney() < 0) {
                //子账户扣款
                if ((userAccount.getPrimaryAccount() + userAccount.getSubAccount()) < Math.abs(dto.getSubMoney())) {
                    //子帐户金额＋主帐户金额小于要扣的金额
                    //扣款余额不足
                    return ActionResult.fail(SystemErrorCode.balanceError.getCode());
                }
                if (userAccount.getSubAccount() >= Math.abs(dto.getSubMoney())) {
                    //子账户金额大于要扣除的金额，直接扣除
                    userAccount.setSubAccount(userAccount.getSubAccount() + dto.getSubMoney());
                    userAccount.setUpdateTime(new Date());
                    userAccountService.update(userAccount);

//                    userAccountChangeLog.setPaChangeAfter(userAccount.getPrimaryAccount());
                    userAccountChangeHistory.setSaChangeAfter(userAccount.getSubAccount());
                    userAccountChangeHistory.setType(AccountConstants.SA_ACCOUNT_CHANGE_SUB);
                    userAccountChangeHistory.setSource(dto.getSource());
                    userAccountChangeHistoryService.insert(userAccountChangeHistory);
                } else if (userAccount.getSubAccount() < Math.abs(dto.getSubMoney())) {
                    //子账户金额小于要扣除的金额，将子账户扣除到零，剩余的从主帐户中扣除
                    userAccount.setPrimaryAccount(userAccount.getSubAccount() + userAccount.getPrimaryAccount() + dto.getSubMoney());
                    userAccount.setSubAccount(0);
                    userAccount.setUpdateTime(new Date());
                    userAccountService.update(userAccount);
                    userAccountChangeHistory.setPaChangeAfter(userAccount.getPrimaryAccount());
                    userAccountChangeHistory.setSaChangeAfter(userAccount.getSubAccount());
                    userAccountChangeHistory.setType(AccountConstants.SA_ACCOUNT_CHANGE_SUB);
                    userAccountChangeHistory.setSource(dto.getSource());
                    userAccountChangeHistoryService.insert(userAccountChangeHistory);
                } else {
                    return ActionResult.fail();
                }
            } else if (dto.getPrimaryMoney() > 0) {
                //主账户充值
                userAccount.setPrimaryAccount(userAccount.getPrimaryAccount() + dto.getPrimaryMoney());
                userAccount.setUpdateTime(new Date());
                userAccountService.update(userAccount);
                userAccountChangeHistory.setPaChangeAfter(userAccount.getPrimaryAccount());
                userAccountChangeHistory.setType(AccountConstants.PA_ACCOUNT_CHANGE_ADD);
                userAccountChangeHistory.setSource(dto.getSource());
                userAccountChangeHistoryService.insert(userAccountChangeHistory);
            } else {
                return ActionResult.fail();
            }
        } else {
            //没有帐户的新创建一个帐户
            createNewCount(dto);
            //递归调用本身进行扣款
            return changeAccount(dto);
//            return ActionResult.fail(SystemErrorCode.needChargeError.getCode());
        }

        return ActionResult.success(userAccountChangeHistory);
    }

    @Transactional
    public ChangeAccountDto createNewCount(ChangeAccountDto dto) throws SQLException {
        UserAccount account = new UserAccount();
        account.setUserId(dto.getUserId());
        account.setPrimaryAccount(0);
        account.setSubAccount(0);
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        userAccountService.insert(account);
        UserAccountChangeHistory userAccountChangeHistory = new UserAccountChangeHistory();
        userAccountChangeHistory.setCreateTime(new Date());
        userAccountChangeHistory.setUserId(account.getUserId());
        userAccountChangeHistory.setCreateTime(new Date());
        userAccountChangeHistory.setPaChangeBefore(0);
        userAccountChangeHistory.setPaChangeAfter(0);
        userAccountChangeHistory.setSaChangeBefore(0);
        userAccountChangeHistory.setSaChangeAfter(0);
        userAccountChangeHistory.setType(AccountConstants.PA_ACCOUNT_CHANGE_ADD);
        userAccountChangeHistory.setSource(AccountConstants.ACCOUNT_RECHARGE);
        userAccountChangeHistoryService.insert(userAccountChangeHistory);
        return dto;
    }

}
