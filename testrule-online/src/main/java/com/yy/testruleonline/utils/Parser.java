package com.yy.testruleonline.utils;

import com.yy.testruleonline.bo.ConditionDetailBo;
import com.yy.testruleonline.enums.TagType;

import java.math.BigDecimal;
import java.util.Map;

public class Parser {
    public static Object parseParamClassifyType(Map<String, String> input, ConditionDetailBo conditionDetailBo, TagType tagType) {
        Object parseResult = null;
        String parseStr = input.get(conditionDetailBo.getTag().getName());
        if(parseStr!=null) {
            switch (tagType) {
                case NUM:
                    parseResult = new BigDecimal(parseStr);
                    break;
                case ENUM:
                    parseResult = Integer.valueOf(parseStr);
                    break;
                case STRING:
                    parseResult = parseStr;
                    break;
                default:
                    break;
            }
        }
        return parseResult;
    }

}
