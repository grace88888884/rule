package com.yy.testruleonline.utils;

import com.googlecode.aviator.AviatorEvaluator;
import com.yy.testruleonline.bo.CondBo;
import com.yy.testruleonline.enums.TagType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Parser {
    public static Object parseInputTagType(Map<String, String> input, CondBo condBo) {
        Object parseResult = null;
        TagType tagType = condBo.getTagBo().getTagType();
        String parseStr = input.get(condBo.getTag().getTagName());
            switch (tagType) {
                case NUM:
                    if (parseStr != null) {
                        parseResult = new BigDecimal(parseStr);
                    }
                    break;
                case ENUM:
                case STRING:
                    parseResult = parseStr;
                    break;
                case CACULATION:
                    Map<String, Object> map = new HashMap<>();
                    String funName = condBo.getTagBo().getTag().getFunName();
                    Iterator<Map.Entry<String, String>> iterator = input.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> next = iterator.next();
                        map.put(next.getKey(), next.getValue());
                    }
                    parseResult = AviatorEvaluator.execute(funName + "()", map);
                default:
                    break;
        }
        return parseResult;
    }


    public static Object parseOutputTagType(Map<String, String> input, CondBo condBo) {
        Object parseResult = null;
        TagType tagType = condBo.getResultTagBo().getTagType();
        switch (tagType) {
            case NUM:
                parseResult = condBo.getCond().getThrValue();
                break;
            case ENUM:
            case STRING:
                parseResult = condBo.getCond().getTagRngName();
                break;
            case CACULATION:
                Map<String, Object> map = new HashMap<>();
                String funName = condBo.getResultTagBo().getTag().getFunName();
                Iterator<Map.Entry<String, String>> iterator = input.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    map.put(next.getKey(), next.getValue());
                }
                parseResult = AviatorEvaluator.execute(funName + "()", map);
            default:
                break;
        }
        return parseResult;
    }
}
