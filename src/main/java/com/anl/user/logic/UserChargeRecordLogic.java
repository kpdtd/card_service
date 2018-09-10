package com.anl.user.logic;

import com.anl.user.dto.ActionResult;
import com.anl.user.persistence.po.UserChargeRecord;

public interface UserChargeRecordLogic {

    ActionResult creatOrder(UserChargeRecord userChargeRecord) throws Exception;

    UserChargeRecord updateUserChargeRecordState(String outTradeNo, int state)throws Exception;
}
