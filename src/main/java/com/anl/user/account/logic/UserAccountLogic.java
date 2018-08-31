package com.anl.user.account.logic;

import com.anl.user.account.dto.ChangeAccountDto;
import com.anl.user.dto.ActionResult;

import java.sql.SQLException;

public interface UserAccountLogic {
    public ActionResult changeAccount(ChangeAccountDto dto) throws SQLException;
}
