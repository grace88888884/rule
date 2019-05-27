package com.yy.testruleonline.exceptions;

import com.yy.testruleonline.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExceptionUtils {
    public static void addExcption(Map<String, Object> map, Exception e) {
        Object o = map.get(Constants.ruleException);
        if (o == null) {
            o = new ArrayList<Exception>();
            map.put(Constants.ruleException, o);

        }
        List<Exception> exceptionList = (ArrayList<Exception>) o;
        exceptionList.add(e);
    }


    public static void addExcption(Map<String, Object> map, List<Exception> eList) {
        Object o = map.get(Constants.ruleException);
        if (o == null) {
            o = new ArrayList<Exception>();
            map.put(Constants.ruleException, o);

        }
        List<Exception> exceptionList = (ArrayList<Exception>) o;
        exceptionList.addAll(eList);
    }

    public static Object parseExceptionString(Object o) {
        List<String> exceptionStrList = new ArrayList<>();
        if(o!=null&& o instanceof List){
            ArrayList<Exception> list = (ArrayList<Exception>) o;
            for (Exception e : list){
                exceptionStrList.add(e.getMessage());
            }
        }
        return exceptionStrList;
        
    }
}
