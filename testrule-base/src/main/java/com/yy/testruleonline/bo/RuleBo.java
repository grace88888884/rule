package com.yy.testruleonline.bo;


import com.yy.testruleonline.dao.entity.TReRule;
import com.yy.testruleonline.rule.function.action.IForceAction;

import java.util.ArrayList;
import java.util.List;

public class RuleBo {
    private List<ActnBo> actnBoList=new ArrayList<>();
    private List<ActnBo> elseActnBoList=new ArrayList<>();
    private CondGrpBo condGrpBo;
    List<IForceAction> forceActionList=new ArrayList<>();
    private TReRule rule;

    public TReRule getRule() {
        return rule;
    }

    public void setRule(TReRule rule) {
        this.rule = rule;
    }

    public List<ActnBo> getActnBoList() {
        return actnBoList;
    }

    public void setActnBoList(List<ActnBo> actnBoList) {
        this.actnBoList = actnBoList;
    }

    public List<ActnBo> getElseActnBoList() {
        return elseActnBoList;
    }

    public void setElseActnBoList(List<ActnBo> elseActnBoList) {
        this.elseActnBoList = elseActnBoList;
    }

    public CondGrpBo getCondGrpBo() {
        return condGrpBo;
    }

    public void setCondGrpBo(CondGrpBo condGrpBo) {
        this.condGrpBo = condGrpBo;
    }

    public List<IForceAction> getForceActionList() {
        return forceActionList;
    }

    public void setForceActionList(List<IForceAction> forceActionList) {
        this.forceActionList = forceActionList;
    }

//    public List<TagBo> getInputTagList() {
//        return inputTagList;
//    }

//    public void setInputTagList(List<TagBo> inputTagList) {
//        this.inputTagList = inputTagList;
//    }
}
