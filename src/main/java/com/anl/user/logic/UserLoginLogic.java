package com.anl.user.logic;


import com.anl.user.dto.ActionResult;

public interface UserLoginLogic {

	ActionResult userLogin(String validationCode, String mobile,String openid) throws Exception;
	ActionResult userBanding(String validationCode, String mobile, Integer cardId, String openid) throws Exception;

}
