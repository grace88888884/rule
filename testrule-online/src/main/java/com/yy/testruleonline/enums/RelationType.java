package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum RelationType implements IEnum {
    AND( "并且","&&"),
    OR( "或者","||");
    private String desc;
    private String code;

    RelationType( final String desc,final String code) {
        this.desc = desc;
        this.code = code;
    }

    @Override
    public Serializable getValue() {
        return this.name();
    }

    public String getCode() {
        return code;
    }

    @JsonValue
    public String getDesc() {
        return this.desc;
    }
}
