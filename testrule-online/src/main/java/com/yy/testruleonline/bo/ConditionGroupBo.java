package com.yy.testruleonline.bo;

import com.yy.testruleonline.entity.ConditionGroup;

import java.util.ArrayList;
import java.util.List;

public class ConditionGroupBo {
    private List<ConditionDetailBo> conditionDetailBoList = new ArrayList<>();
    private List<ConditionGroupBo> conditionGroupBoList = new ArrayList<>();
    private ConditionGroup conditionGroup;

    public List<ConditionGroupBo> getConditionGroupBoList() {
        return conditionGroupBoList;
    }

    public void setConditionGroupBoList(List<ConditionGroupBo> conditionGroupBoList) {
        this.conditionGroupBoList = conditionGroupBoList;
    }

    public List<ConditionDetailBo> getConditionDetailBoList() {
        return conditionDetailBoList;
    }

    public void setConditionDetailBoList(List<ConditionDetailBo> conditionDetailBoList) {
        this.conditionDetailBoList = conditionDetailBoList;
    }

    public ConditionGroup getConditionGroup() {
        return conditionGroup;
    }

    public void setConditionGroup(ConditionGroup conditionGroup) {
        this.conditionGroup = conditionGroup;
    }
}
