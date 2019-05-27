package com.yy.testruleonline.rule.function.loader;


import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.TreeSet;

public abstract class AbstractRuleFunctionDataLoader<T> {
    TreeSet<AbstractRuleFunctionDataLoader> formLoaderList = new TreeSet<>(new Comparator<AbstractRuleFunctionDataLoader>() {
        @Override
        public int compare(AbstractRuleFunctionDataLoader o1, AbstractRuleFunctionDataLoader o2) {
            return o2.getOrder() - o1.getOrder();
        }
    });

    @PostConstruct
    public void initFormLoader() {
        initFormLoader(formLoaderList);
    }

    public String loadData(T context) {
        String data = getData(context);
        if (data == null) {
            for (AbstractRuleFunctionDataLoader ruleFunctionDataLoader : formLoaderList) {
                String param = ruleFunctionDataLoader.getData(context);
                if (param != null) {
                    try {
                        Method method = context.getClass().getMethod("set" + ruleFunctionDataLoader.getParamName(), String.class);
                        method.invoke(context,param);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    data = getData(context);
                    return data;
                }
            }
        }
        return data;
    }


    public abstract String getData(T env);

    public abstract String getParamName();


    public abstract int getOrder();

    public abstract void initFormLoader(TreeSet<AbstractRuleFunctionDataLoader> formLoaderList);

}
