package com.anl.user.pay.logic;


import com.anl.user.persistence.po.UserChargeRecord;
import com.anl.user.persistence.po.UserRefundRecord;

public interface UserRefundRecordLogic {

	 Boolean Refunds(String outTradeNo, Integer money) throws Exception ;
	 Boolean wxRefunds(UserChargeRecord userChargeRecord, Integer money, UserRefundRecord record) throws Exception;
}
