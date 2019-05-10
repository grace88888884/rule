package com.yy.testruleonline.function;


import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.function.annotations.Tags;

import java.util.Map;

public abstract class AbstractTagCRuleFunction extends AbstractRuleFunction {

    @Override
    public AviatorObject call(Map<String, Object> env) {
        Tags tags = this.getClass().getAnnotation(Tags.class);
        if(tags!=null) {
            String[] values = tags.value();
        }
        AviatorObject runResult = calTagValue(env);
        return runResult;
    }


    public abstract AviatorObject calTagValue(Map<String, Object> env);

}
