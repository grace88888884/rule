package com.yy.testruleonline.rule.api;

import com.yy.testruleonline.enums.RuleRunResult;

public class RuleGeneralResponse<I> {
    String responseCode;
    Boolean isSatisfied;
    I result;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
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
