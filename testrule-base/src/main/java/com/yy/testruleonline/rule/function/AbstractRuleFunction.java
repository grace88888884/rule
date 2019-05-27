package com.yy.testruleonline.rule.function;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;

import javax.annotation.PostConstruct;

public abstract class AbstractRuleFunction<T> extends AbstractFunction {
    
    @PostConstruct
    public void init(){
        AviatorEvaluator.addFunction(this);
    }
    
    @Override
    public String getName() {
        return getFuctionType();
    }

    abstract public String getFuctionType();

}
