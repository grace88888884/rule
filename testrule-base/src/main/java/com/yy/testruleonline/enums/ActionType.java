package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum ActionType implements IEnum {
    FUCTION("F", "枚举"),
    DECL_RES("D", "拒绝返回"),
    PARAM("P", "字符");
    private String code;
    private String desc;

    ActionType(final String code, final String desc) {
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
