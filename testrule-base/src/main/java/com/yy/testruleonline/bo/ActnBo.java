package com.yy.testruleonline.bo;

import com.yy.testruleonline.dao.entity.TReActn;
import com.yy.testruleonline.dao.entity.TReTagRng;
import com.yy.testruleonline.dao.entity.TReTag;
import com.yy.testruleonline.enums.ActionType;
import com.yy.testruleonline.rule.annotation.RuleTag;

public class ActnBo {
    private TReActn actn;
    private RuleTag ruleTag;
    private String funName;
    private ActionType actionType;

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public RuleTag getRuleTag() {
        return ruleTag;
    }

    public void setRuleTag(RuleTag ruleTag) {
        this.ruleTag = ruleTag;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public void setActn(TReActn actn) {
        this.actn = actn;
    }


    public TReActn getActn() {
        return actn;
    }

    public void setTReActn(TReActn action) {
        this.actn = action;
    }

  
}
