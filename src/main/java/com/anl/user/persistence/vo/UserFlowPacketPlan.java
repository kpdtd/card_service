package com.anl.user.persistence.vo;

import com.anl.user.persistence.po.UserFlowPacket;

/**
 * Created by yangyiqiang on 2018/8/28.
 */
public class UserFlowPacketPlan extends UserFlowPacket{
    private Integer useSort;

    public Integer getUseSort() {
        return useSort;
    }

    public void setUseSort(Integer useSort) {
        this.useSort = useSort;
    }
}
