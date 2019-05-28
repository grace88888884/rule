package com.yy.testruleonline.rule.api;

import com.yy.testruleonline.enums.RuleRunResult;

import java.util.List;

public class RuleGeneralResponse<I> {
    String ruleResult;
    List<String> ruleException;
    Boolean isSatisfied =false;
    I result;

    public List<String> getRuleException() {
        return ruleException;
    }

    public void setRuleException(List<String> ruleException) {
        this.ruleException = ruleException;
    }

    public String getRuleResult() {
        return ruleResult;
    }

    public void setRuleResult(String ruleResult) {
        this.ruleResult = ruleResult;
    }

    public Boolean getSatisfied() {
        return isSatisfied;
    }

    public void setSatisfied(Boolean satisfied) {
        isSatisfied = satisfied;
    }

    public I getResult() {
        return result;
    }

    public void setResult(I result) {
        this.result = result;
    }
}
