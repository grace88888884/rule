package com.yy.testruleonline.function.loader;

import java.util.Map;

public abstract class AbstractTagFRuleFunctionDataLoader<T> {
    public abstract T loadData(Map<String, Object> env);

}
