package com.yy.testruleonline.bo;


import com.yy.testruleonline.dao.entity.TReCond;
import com.yy.testruleonline.dao.entity.TReRule;
import com.yy.testruleonline.rule.function.action.IForceAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RuleBo {
    private List<ActnBo> actnBoList=new ArrayList<>();
    private List<ActnBo> elseActnBoList=new ArrayList<>();
    private List<IForceAction> forceActionList=new ArrayList<>();
    private TReRule rule;
    private Map<String, CondBo> condBoMap;

    public Map<String, CondBo> getCondBoMap() {
        return condBoMap;
    }

    public void setCondBoMap(Map<String, CondBo> condBoMap) {
        this.condBoMap = condBoMap;
    }

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
