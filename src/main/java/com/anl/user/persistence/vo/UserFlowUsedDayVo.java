package com.anl.user.persistence.vo;

import com.anl.user.persistence.po.UserFlowUsedDay;

public class UserFlowUsedDayVo extends UserFlowUsedDay {
    private String recordTimeStr;
    private String flowUnit;
    private String flowStr;

    public String getFlowStr() {
        return flowStr;
    }

    public void setFlowStr(String flowStr) {
        this.flowStr = flowStr;
    }

    public String getFlowUnit() {
        return flowUnit;
    }

    public void setFlowUnit(String flowUnit) {
        this.flowUnit = flowUnit;
    }

    public String getRecordTimeStr() {
        return recordTimeStr;
    }

    public void setRecordTimeStr(String recordTimeStr) {
        this.recordTimeStr = recordTimeStr;
    }
}
