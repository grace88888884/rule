package com.yy.testruledemo.rule.rulebo;

import com.yy.testruledemo.entity.TReMerPolicy;
import com.yy.testruledemo.entity.TReMerStat;

public class MerTypeBo {
    TReMerPolicy merPolicy =null;
    TReMerStat merStat = null;

    public TReMerPolicy getMerPolicy() {
        return merPolicy;
    }

    public void setMerPolicy(TReMerPolicy merPolicy) {
        this.merPolicy = merPolicy;
    }

    public TReMerStat getMerStat() {
        return merStat;
    }

    public void setMerStat(TReMerStat merStat) {
        this.merStat = merStat;
    }
}
