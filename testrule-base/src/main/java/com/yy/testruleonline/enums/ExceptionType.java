package com.yy.testruleonline.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum  ExceptionType implements IEnum {
    TAG_PARSER_EXCEPTION("标签转换错误"),
    TAG_NOT_FOUND("没有找到相应标签"),
    COND_EXECUTE_EXCEPTION("条件执行错误"),
    COND_SERVICE_EXCEPTION("业务错误"),
    COND_RELATION_EXECUTE_EXCEPTION("条件组合执行错误"),
    COND_RULE_EXECUTE_EXCEPTION("规则执行错误");
    private String desc;

    
    
    ExceptionType(final String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public Serializable getValue() {
        return this.desc;
    }
    
}
