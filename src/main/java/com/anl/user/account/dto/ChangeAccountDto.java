package com.anl.user.account.dto;

/**
 * 更改帐户所需基类
 */
public class ChangeAccountDto {


    //帐户绑定的卡id
    private Integer userId;
    //主帐户扣减金额   数值为正时增加帐户，为负时扣减帐户
    private Integer primaryMoney;
    //子帐户扣减金额   数值为正时增加帐户，子帐户不能小于零
    private Integer subMoney;

    public ChangeAccountDto(Integer userId, Integer primaryMoney, Integer subMoney) {
        this.userId = userId;
        this.primaryMoney = primaryMoney;
//        if (subMoney >= 0) {
            this.subMoney = subMoney;
//        } else {
////            throw new RuntimeException("子帐户不能直接操作扣款");
////        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPrimaryMoney() {
        return primaryMoney;
    }

    public void setPrimaryMoney(Integer primaryMoney) {
        this.primaryMoney = primaryMoney;
    }

    public Integer getSubMoney() {
        return subMoney;
    }

    public void setSubMoney(Integer subMoney) {
        if (subMoney >= 0) {
            this.subMoney = subMoney;
        } else {
            throw new RuntimeException("子帐户不能小于零");
        }
    }
}
