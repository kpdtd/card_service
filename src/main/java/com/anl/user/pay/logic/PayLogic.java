package com.anl.user.pay.logic;

import com.anl.user.dto.LogicResult;
import com.anl.user.pay.po.BasePay;

public interface PayLogic {

	public LogicResult entrancePayLogic(BasePay userOrder) throws Exception;


}
