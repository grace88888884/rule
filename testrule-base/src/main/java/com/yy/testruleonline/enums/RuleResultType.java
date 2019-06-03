package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum RuleResultType implements IEnum {
    SEQ("S", "顺序访问"),
    PARAM("P", "并行验证");
    private String code;
    private String desc;

    RuleResultType(final String code, final String desc) {
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
