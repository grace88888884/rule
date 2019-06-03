package com.yy.testruleonline.rule.api;

public class RuleGeneralRequest<I> {
    String ruleFlowName;
    I request;

    public String getRuleFlowName() {
        return ruleFlowName;
    }

    public void setRuleFlowName(String ruleFlowName) {
        this.ruleFlowName = ruleFlowName;
    }

    public I getRequest() {
        return request;
    }

    public void setRequest(I request) {
        this.request = request;
    }
}
