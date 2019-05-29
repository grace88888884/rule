package com.yy.testruleonline.rule.annotation;

import com.yy.testruleonline.enums.TagType;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RuleTagCollection {
}
