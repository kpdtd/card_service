package com.anl.user.persistence.mapper;

import com.anl.user.persistence.po.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类名: UserAccount
 * 创建日期: 
 * 功能描述: 
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {
   List<UserAccount> getListByMoney (@Param("money") Integer money)throws Exception;

   UserAccount getUserAccountByUserId(@Param("userId")Integer userId);
}