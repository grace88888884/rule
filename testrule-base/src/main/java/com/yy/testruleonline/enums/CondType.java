package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum CondType implements IEnum {
    ENUM("枚举"),
    TAG("标签"),
    NUM("值");


    private String desc;

    CondType(final String desc) {
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

    @Override
    public String toString() {
        return super.toString();
    }
}
