package com.yy.testruleonline.bo;

import com.yy.testruleonline.entity.ConditionDetail;
import com.yy.testruleonline.entity.ConditionGroup;
import com.yy.testruleonline.entity.Param;
import com.yy.testruleonline.entity.ParamClassify;

public class ConditionDetailBo {
    private ConditionDetail conditionDetail;
    private ParamClassify paramClassify;
    private Param param;

    public ConditionDetail getConditionDetail() {
        return conditionDetail;
    }

    public void setConditionDetail(ConditionDetail conditionDetail) {
        this.conditionDetail = conditionDetail;
    }

    public ParamClassify getParamClassify() {
        return paramClassify;
    }

    public void setParamClassify(ParamClassify paramClassify) {
        this.paramClassify = paramClassify;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }
}
