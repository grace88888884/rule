package com.yy.testruleonline.bo;


import com.yy.testruleonline.dao.entity.TReRule;
import com.yy.testruleonline.dao.entity.TReRuleFlow;
import com.yy.testruleonline.rule.function.action.IForceAction;

import java.util.ArrayList;
import java.util.List;

public class RuleFlowBo {
    private List<RuleBo> ruleList=new ArrayList<>();
    private TReRuleFlow ruleFlow;

    public List<RuleBo> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<RuleBo> ruleList) {
        this.ruleList = ruleList;
    }

    public TReRuleFlow getRuleFlow() {
        return ruleFlow;
    }

    public void setRuleFlow(TReRuleFlow ruleFlow) {
        this.ruleFlow = ruleFlow;
    }
}
