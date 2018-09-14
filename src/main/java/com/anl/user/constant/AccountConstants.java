package com.anl.user.constant;

/**
 * @author lianzf
 * @Description:
 * @date 18/4/11下午2:13
 */
public class AccountConstants {

    public static final Integer PA_ACCOUNT_CHANGE_ADD = 1; // 主账户加款
    public static final Integer PA_ACCOUNT_CHANGE_SUB = 2; // 主账户减款
    public static final Integer SA_ACCOUNT_CHANGE_ADD = 3; // 子账户加款
    public static final Integer SA_ACCOUNT_CHANGE_SUB = 4; // 子账户减款

    public static final String ACCOUNT_DAY_SUB = "3"; // 日租宝扣减
    public static final String ACCOUNT_MONTH_SUB = "6"; // 月功能费扣减


    public static final String ACCOUNT_RECHARGE = "1";//1-正常充值
    public static final String ACCOUNT_GITF = "2";//2-赠送
    public static final String ACCOUNT_BUY_GOODS = "4";//4-购买流量包
    public static final String ACCOUNT_ARTIFICIAL = "5";//5-人工
    public static final String ACCOUNT_OTHER = "7";//7-其他（逐步补充）
}
