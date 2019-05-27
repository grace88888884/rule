package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum  TagAnnotationType implements IEnum {
    ENUM("E", "枚举"),
    STRING("S", "字符"),
    CACULATION("C", "数据库"),
    NUM("N", "数值型");

    private String code;
    private String desc;

    TagAnnotationType(final String code, final String desc) {
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
