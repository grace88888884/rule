package com.yy.testruleonline.rule.function.action;


import com.yy.testruleonline.rule.ActionManager;
import com.yy.testruleonline.rule.function.AbstractRuleFunction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class AbstractActionFunction<T> extends AbstractRuleFunction {
    @Autowired
    ActionManager<T> actionManager;
    
    @PostConstruct
    public void initActionFunName(){
        actionManager.addActionFunName(getFuctionType());
    }
   
}
