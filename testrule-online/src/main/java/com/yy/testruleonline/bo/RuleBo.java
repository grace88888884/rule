package com.yy.testruleonline.bo;


import java.util.List;

public class RuleBo {
    private List<ActionDetailBo> actionDetailBoList;
    private List<ActionDetailBo> elseActionDetailBoList;
    private ConditionGroupBo conditionGroupBo;

    public List<ActionDetailBo> getActionDetailBoList() {
        return actionDetailBoList;
    }

    public void setActionDetailBoList(List<ActionDetailBo> actionDetailBoList) {
        this.actionDetailBoList = actionDetailBoList;
    }

    public List<ActionDetailBo> getElseActionDetailBoList() {
        return elseActionDetailBoList;
    }

    public void setElseActionDetailBoList(List<ActionDetailBo> elseActionDetailBoList) {
        this.elseActionDetailBoList = elseActionDetailBoList;
    }

    public ConditionGroupBo getConditionGroupBo() {
        return conditionGroupBo;
    }

    public void setConditionGroupBo(ConditionGroupBo conditionGroupBo) {
        this.conditionGroupBo = conditionGroupBo;
    }

    
}
