
package com.anl.user.service;

import com.anl.user.constant.AccountConstants;
import com.anl.user.persistence.mapper.UserAccountChangeHistoryMapper;
import com.anl.user.persistence.mapper.UserAccountMapper;
import com.anl.user.persistence.po.Card;
import com.anl.user.persistence.po.User;
import com.anl.user.persistence.po.UserAccount;
import com.anl.user.persistence.po.UserAccountChangeHistory;
import com.anl.user.util.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	public static final org.slf4j.Logger logger = LogFactory.getInstance().getLogger();

	@Autowired
	UserAccountMapper userAccountMapper;
	@Autowired
	UserAccountChangeHistoryMapper userAccountChangeHistoryMapper;

	@Transactional
	@Override
	public int updateBalance(User user, UserAccountChangeHistory userAccountChangeHistory, int money) throws Exception{
		UserAccount userAccount = userAccountMapper.getById(user.getId());
		if(userAccount == null){
			logger.info("该用户还未创建账户就产生了流量，给与停机处理");
			return -1;
		}
		int primaryAccount = userAccount.getPrimaryAccount(); // 主账户余额
		int subAccount = userAccount.getSubAccount(); // 子账户余额
		userAccountChangeHistory.setPaChangeBefore(userAccount.getPrimaryAccount());
		userAccountChangeHistory.setSaChangeBefore(userAccount.getSubAccount());
		logger.info("扣减前主账户余额 {} 分, 子账户余额 {} 分", primaryAccount, subAccount);
		int balance = primaryAccount - money;
		if(balance >= 0){
			primaryAccount = balance;
			userAccount.setPrimaryAccount(primaryAccount);
			userAccountChangeHistory.setType(AccountConstants.PA_ACCOUNT_CHANGE_SUB);
		}else{
			primaryAccount = 0;
			userAccount.setPrimaryAccount(primaryAccount);
			subAccount = subAccount - Math.abs(balance);
			userAccount.setSubAccount(subAccount);
			userAccountChangeHistory.setType(AccountConstants.SA_ACCOUNT_CHANGE_SUB);
			//确保子账户余额>=0,不够扣的时候最终让主账号为负值
			if(subAccount <= 0){
				primaryAccount = subAccount;
				userAccount.setPrimaryAccount(subAccount);
				userAccount.setSubAccount(0);
			}
		}
		userAccountChangeHistory.setMoney(money);
		userAccountChangeHistory.setUserId(user.getId());
		userAccountChangeHistory.setPaChangeAfter(userAccount.getPrimaryAccount());
		userAccountChangeHistory.setSaChangeAfter(userAccount.getSubAccount());
		userAccountChangeHistory.setCreateTime(new Date());
		userAccountChangeHistoryMapper.insert(userAccountChangeHistory);
		userAccount.setUpdateTime(new Date());
		userAccountMapper.update(userAccount);
		logger.info("卡扣减 {} 分后, 主账户余额 {} 分, 子账户余额 {} 分", money, primaryAccount, subAccount);
		return primaryAccount + subAccount;
	}

	@Override
	public List<UserAccount> getListByMoney(Integer money) throws Exception {
		return userAccountMapper.getListByMoney(money);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return userAccountMapper.count(condition);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return userAccountMapper.deleteById(id);
	}

	@Override
	public UserAccount getById(Integer id) throws SQLException {
		return userAccountMapper.getById(id);
	}

	@Override
	public List<UserAccount> getListByPo(UserAccount record) throws SQLException {
		return null;
	}

	@Override
	public List<UserAccount> getListByMap(Map<String, Object> dataMap) throws SQLException {
		return userAccountMapper.getListByMap(dataMap);
	}

	@Override
	public int insert(UserAccount record) throws SQLException {
		return userAccountMapper.insert(record);
	}

	@Override
	public int update(UserAccount record) throws SQLException {
		return userAccountMapper.update(record);
	}
}

