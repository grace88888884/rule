package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum RuleRunResult implements IEnum {
    RUN_ACTION( "runAction","并且"),
    RUN_ELSE_ACTION( "runElseAction","或者"),
    RUN_NOTHINT( "runNothing","或者");
    private String desc;
    private String name;

    RuleRunResult( final String name, final String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.name;
    }
    
    @JsonValue
    public String getName() {
        return name;
    }

    public String getDesc() {
        return this.desc;
    }
}
