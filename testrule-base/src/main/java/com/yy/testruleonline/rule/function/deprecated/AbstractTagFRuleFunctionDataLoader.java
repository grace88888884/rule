package com.yy.testruleonline.rule.function.deprecated;

import java.util.Map;
@Deprecated
public abstract class AbstractTagFRuleFunctionDataLoader<T> {
    public abstract T loadData(Map<String, Object> env);

}
