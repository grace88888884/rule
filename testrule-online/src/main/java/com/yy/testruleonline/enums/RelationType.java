package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum RelationType implements IEnum {
    AND( "并且"),
    OR( "或者");
    private String desc;

    RelationType( final String desc) {
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.name();
    }

    @JsonValue
    public String getDesc() {
        return this.desc;
    }
}
