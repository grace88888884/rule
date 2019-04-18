package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum OperationType implements IEnum {
    EQU("等于"),
    GRT("大于"),
    GRE("大于等于");


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
