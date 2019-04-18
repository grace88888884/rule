package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum OperationType implements IEnum {
    EQ("等于"),
    GT("大于"),
    GE("大于等于"),
    LT("小于"),
    LE("小于等于");


    private String desc;

    OperationType(final String desc) {
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
