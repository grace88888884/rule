package com.yy.testruleonline.rule.annotation;

import com.yy.testruleonline.enums.TagType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RuleTag {
    String tagName();
    TagType tagType();
    String tagDesc();
    Class tagRange() default void.class;
    Class tagFun() default void.class;
}
