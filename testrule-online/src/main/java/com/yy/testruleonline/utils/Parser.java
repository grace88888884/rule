package com.yy.testruleonline.utils;

import com.yy.testruleonline.bo.CondBo;
import com.yy.testruleonline.enums.TagType;

import java.math.BigDecimal;
import java.util.Map;

public class Parser {
    public static Object parseParamClassifyType(Map<String, String> input, CondBo condBo, TagType tagType) {
        Object parseResult = null;
        String parseStr = input.get(condBo.getTag().getTagName());
        if(parseStr!=null) {
            switch (tagType) {
                case NUM:
                    parseResult = new BigDecimal(parseStr);
                    break;
                case ENUM:
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
