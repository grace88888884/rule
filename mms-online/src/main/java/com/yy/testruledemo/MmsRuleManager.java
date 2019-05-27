package com.yy.testruledemo;

import com.yy.testrule.common.data.MmsContextInput;
import com.yy.testruleonline.rule.AbstractRuleManager;
import org.springframework.stereotype.Component;

@Component
public class MmsRuleManager extends AbstractRuleManager<MmsContextInput, MmsContext> {

    @Override
    public Class<MmsContext> getContext() {
        return MmsContext.class;
    }
}
