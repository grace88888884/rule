package com.yy.testruleonline.exceptions;

import com.yy.testruleonline.enums.ExceptionType;

public class RuleException extends RuntimeException {
    ExceptionType exceptionType;

    public RuleException(ExceptionType exceptionType,String message) {
        super(exceptionType.getDesc()+","+message);
        this.exceptionType = exceptionType;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public RuleException( ExceptionType exceptionType) {
        super(exceptionType.getDesc());
        this.exceptionType = exceptionType;
    }
}
