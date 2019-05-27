package com.yy.testruleonline.rule.api;

public class RuleGeneralRequest<I> {
    String ruleName;
    I request;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public I getRequest() {
        return request;
    }

    public void setRequest(I request) {
        this.request = request;
    }
}
