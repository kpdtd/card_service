package com.anl.user.logic;

import com.anl.user.constant.UserState;
import com.anl.user.persistence.po.Card;
import com.anl.user.persistence.po.User;
import com.anl.user.service.CardService;
import com.anl.user.service.UserService;
import com.anl.user.util.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserLogicImpl implements UserLogic {

    private static final Logger logger = LogFactory.getInstance().getLogger();
    @Autowired
    OpenOrCloseCardLogic openOrCloseCardLogic;

    @Autowired
    UserService userService;
    @Autowired
    CardService cardService;

    /**
     * 对停机用户进行开卡操作,开卡完成后修改用户状态为正常充值状态
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public boolean userOpenCard(int userId, String reason) throws Exception {
        User user = userService.getById(userId);
        if (user != null && user.getState().equals(UserState.OFF_USER)) {
            Card card = cardService.getById(user.getCardId());
            if (openOrCloseCardLogic.openCard(card, reason)) {
                user.setState(UserState.CHARGE_USER);
                user.setUpdateTime(new Date());
                userService.update(user);
                return true;
            }
        }
        return false;
    }
}
