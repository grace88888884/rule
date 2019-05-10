package com.yy.testruleonline.function;


import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.function.loader.AbstractTagFRuleFunctionDataLoader;
import com.yy.testruleonline.utils.Constants;
import com.yy.testruleonline.utils.RuleHashMap;

import java.util.Map;

public abstract class AbstractTagFRuleFunction<T> extends AbstractRuleFunction {

    @Override
    public AviatorObject call(Map<String, Object> env) {
        T data = null;
        if (getDataLoader() != null) {
            data = getDataLoader().loadData(env);
        }
        AviatorBoolean runResult = runCondition(env, data);
        RuleHashMap<String> resultMap = (RuleHashMap<String>) env.get(Constants.resultMap.resultMap);
        fillResultMap(runResult, resultMap);
        return runResult;
    }

    public abstract void fillResultMap(AviatorBoolean runResult, RuleHashMap<String> resultMap);

    public abstract AviatorBoolean runCondition(Map<String, Object> env, T data);

    public abstract AbstractTagFRuleFunctionDataLoader<T> getDataLoader();

}
