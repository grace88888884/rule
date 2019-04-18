package com.yy.testruleonline.bo;

import com.yy.testruleonline.entity.ActionDetail;
import com.yy.testruleonline.entity.ConditionGroup;
import com.yy.testruleonline.entity.Param;
import com.yy.testruleonline.entity.ParamClassify;

public class ActionDetailBo {
    private ActionDetail actionDetail;
    private ParamClassify paramClassify;
    private Param param;

    public ActionDetail getActionDetail() {
        return actionDetail;
    }

    public void setActionDetail(ActionDetail actionDetail) {
        this.actionDetail = actionDetail;
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
