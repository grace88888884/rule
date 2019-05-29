package com.yy.testruleonline.rule;

import com.yy.testruleonline.rule.annotation.RuleTag;
import com.yy.testruleonline.rule.annotation.RuleTagCollection;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
@Component
public class TagManager<T> {
    public Map<String, RuleTag> scanTagList(Class<T> contextClass) {
        Map<String, RuleTag> ruleTagMap = new HashMap<>();
        Field[] fields = contextClass.getDeclaredFields();
        for (Field field : fields) {
            Class<?> declaringClass = field.getType();
            if(declaringClass.isAnnotationPresent(RuleTagCollection.class)){
                Field[] ruleTagCollectionFields = declaringClass.getDeclaredFields();
                for(Field ruleTagCollectionField:ruleTagCollectionFields){
                    if (ruleTagCollectionField.isAnnotationPresent(RuleTag.class)) {
                        RuleTag ruleTag = ruleTagCollectionField.getAnnotation(RuleTag.class);
                        ruleTagMap.put(ruleTag.tagName(), ruleTag);
                    }
                }
            }
        }
        return ruleTagMap;
    }
    
    
    

}
