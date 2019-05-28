package com.yy.testruleonline.exceptions;

import com.yy.testruleonline.enums.ExceptionType;

public class RuleException extends RuntimeException {
    ExceptionType exceptionType;
    Exception exception;

    public RuleException(ExceptionType exceptionType,String message,Exception exception) {
        super(exceptionType.getDesc()+","+message);
        this.exceptionType = exceptionType;
        this.exception = exception;
    }
    public RuleException(ExceptionType exceptionType,String message) {
        super(exceptionType.getDesc()+","+message);
        this.exceptionType = exceptionType;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public Exception getException() {
        return exception;
    }


    public RuleException(ExceptionType exceptionType, Exception exception) {
        super(exceptionType.getDesc());
        this.exceptionType = exceptionType;
        this.exception = exception;
    }
    public RuleException( ExceptionType exceptionType) {
        super(exceptionType.getDesc());
        this.exceptionType = exceptionType;
    }
}
