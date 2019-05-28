package com.yy.testruleonline.utils;

import com.googlecode.aviator.AviatorEvaluator;
import com.yy.testruleonline.bo.CondBo;
import com.yy.testruleonline.bo.TagBo;
import com.yy.testruleonline.enums.CondType;
import com.yy.testruleonline.enums.ExceptionType;
import com.yy.testruleonline.enums.TagType;
import com.yy.testruleonline.exceptions.RuleException;
import com.yy.testruleonline.rule.annotation.RuleTag;
import com.yy.testruleonline.rule.function.tag.AbstractTagFunction;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.yy.testruleonline.utils.Constants.context;

public class TagValueParser {
    

    public static Object parseInputTagType(Map<String, Object> env, CondBo condBo) {
        return parseTagType(env, condBo.getLeftTagBo());
    }


    public static Object parseOutputTagType(Map<String, Object> env, CondBo condBo) {
        Object parseResult = null;
        CondType condType = condBo.getCondType();
        switch (condType) {
            case ENUM:
                parseResult = condBo.getCond().getTagRngName();
                break;
            case NUM:
                parseResult = condBo.getCond().getThrValue();
                break;
            case TAG:
                parseResult = parseTagType(env, condBo.getRightTagBo());
                break;

            default:
                break;
        }
        return parseResult;
    }

   
    private static Object parseTagType(Map<String, Object> env, TagBo tagBo) {
        try {
            Object input = env.get(context);
            Object parseResult = null;
            TagType tagType = tagBo.getTagType();
            Field[] fields = input.getClass().getDeclaredFields();
            Field tagField = null;
            for (Field field : fields) {
                if (field.isAnnotationPresent(RuleTag.class) && tagBo.getRuleTag().tagName().equals(field.getAnnotation(RuleTag.class).tagName())) {
                    field.setAccessible(true);

                    parseResult = field.get(input);
                        tagField = field;
                   
                    break;
                }
            }
//        String parseStr = input.get(tagBo.getRuleTag().tagName());
            switch (tagType) {
                case NUM:
                case ENUM:
                case STRING:
                    if (parseResult != null) {
                        tagField.set(input,parseResult);
                    }
                    break;
                case CACULATION:
                    if(parseResult==null) {
                        Class funClass = tagBo.getRuleTag().tagFun();
                        Object bean = SpringBeanFactory.getBean(funClass);
                        String funName = null;
                        if (bean != null) {
                            AbstractTagFunction fun = (AbstractTagFunction) bean;
                            funName = fun.getFuctionType();
                        }

                        if (funName != null) {
                            parseResult = AviatorEvaluator.execute(funName + "()", env);
                                tagField.set(input,parseResult);

                        }
                    }
                default:
                    break;
            }
            return parseResult;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuleException(ExceptionType.TAG_PARSER_EXCEPTION,tagBo.getRuleTag().tagName(), e);
        }
        
    }

    public static Map<String, String> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, String> map = new HashMap<String, String>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), (String) field.get(obj));
        }

        return map;
    }

}
