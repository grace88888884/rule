package com.yy.testrule.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.yy.testrule.common.utils.CodeEnum;

import java.io.Serializable;

public enum MmsDeclineReason implements  CodeEnum {
    MER_DAY_AMT_LMT ("merDayAmtLmt","日金额限额超限",61, 196,3) ,
    MER_DAY_CNT_LMT ("merDayCntLmt","日次数限额超限",61, 197,2) ,
    MER_BLACKLIST("merBlacklist","黑名单",61, 198,1);

    private String name;
    private String desc;
    private int returnCode;
    private int reasonCode;
    private int order;

    MmsDeclineReason(final String name, final String desc,final int returnCode,final int reasonCode,int order) {
        this.name = name;
        this.desc = desc;
        this.returnCode = returnCode;
        this.reasonCode = reasonCode;
        this.order = order;
    }



    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Object getCode() {
        return this.name;
    }
}
