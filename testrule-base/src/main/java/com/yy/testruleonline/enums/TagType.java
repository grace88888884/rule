package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum TagType implements IEnum {
    ENUM("E", "枚举"),
    STRING("S", "字符"),
    FUCTION("F", "函数"),
    CACULATION("C", "计算"),
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

