package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum FunctionType implements IEnum {
    REDIS_COUNT("redisCount", "测试1"),
    FIELDS("fields", "测试1"),
    CONDITION_GROUP_RELATION("conditionGroupRelation", "关系"),
    CONDITION_OPERATION("conditionOperation", "关系"),
    TEST("test", "测试1");
    private String code;
    private String desc;

    FunctionType(final String code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.code;
    }

    public String getCode() {
        return code;
    }

    @JsonValue
    public String getDesc() {
        return this.desc;
    }
}
