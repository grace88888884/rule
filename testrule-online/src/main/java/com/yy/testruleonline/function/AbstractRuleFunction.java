package com.yy.testruleonline.function;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.enums.FunctionType;

import javax.annotation.PostConstruct;
import java.util.Map;

public abstract class AbstractRuleFunction extends AbstractFunction {
    
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
