package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum ParamClassifyType implements IEnum {
    ENUM("E", "枚举"),
    STRING("S", "字符"),
    INT("I", "整型");
    private String code;
    private String desc;

    ParamClassifyType(final String code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }

    @JsonValue
    public String getDesc() {
        return this.desc;
    }
}

