package com.yy.testruleonline.rule.function.tag;


import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.rule.function.AbstractRuleFunction;
import com.yy.testruleonline.utils.Constants;

import java.util.Map;

public abstract class AbstractTagFunction<T> extends AbstractRuleFunction<T> {


    @Override
    public AviatorObject call(Map<String, Object> env) {
        T t = (T) env.get(Constants.context);
        AviatorObject runResult = calTagValue(t);
        return runResult;
    }


    public abstract AviatorObject calTagValue(T context);

}
