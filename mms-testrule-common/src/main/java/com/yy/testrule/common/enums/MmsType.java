package com.yy.testrule.common.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

public enum MmsType implements IEnum {
    A0("0级"),
    A1("1级"),
    A2("2级"),
    A3("3级"),
    A4("4级"),
    A5("5级"),
    A6("6级"),
    A7("7级"),
    A8("8级"),
    A9("9级"),
    A10("10级"),
    B1("白名单商户"),
    B2("黑名单商户");

    private String desc;

    public String getDesc() {
        return desc;
    }

    MmsType(final String desc) {
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.name();
    }

    }
