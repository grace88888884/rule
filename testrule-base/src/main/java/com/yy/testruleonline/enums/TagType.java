package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum TagType implements IEnum {
    ENUM("E", "枚举"),
    STRING("S", "字符"),
    FUCTION("F", "函数"),
    CACULATION_NUM("CN", "计算数值"),
    CACULATION_STRING("CS", "计算字符"),
    CACULATION_ENUM("CE", "计算枚举"),
    NUM("N", "数值型");
    private String code;
    private String desc;

    TagType(final String code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }
    public String getDesc() {
        return this.desc;
    }
}

