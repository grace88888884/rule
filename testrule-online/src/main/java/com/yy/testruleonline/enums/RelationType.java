package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum RelationType implements IEnum {
    AND("A", "并且"),
    OR("O", "或者");
    private String code;
    private String desc;

    RelationType(final String code, final String desc) {
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
