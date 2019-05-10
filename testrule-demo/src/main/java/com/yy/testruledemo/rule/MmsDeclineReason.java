package com.yy.testruledemo.rule;

import com.baomidou.mybatisplus.enums.IEnum;
import com.yy.testruledemo.utils.CodeEnum;

import java.io.Serializable;

public enum MmsDeclineReason implements IEnum , CodeEnum {
    MER_DAY_AMT_LMT ("merDayAmtLmt","",61, 196,3) ,
    MER_DAY_CNT_LMT ("merDayCntLmt","",61, 197,2) ,
    MER_BLACKLIST("merBlacklist","",61, 198,1);

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
    public Serializable getValue() {
        return this.name;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Object getCode() {
        return name;
    }
}
