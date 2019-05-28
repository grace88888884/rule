package com.yy.testrule.common;

import com.yy.testruleonline.enums.ExceptionType;
import com.yy.testruleonline.exceptions.RuleException;

public class MmsException extends RuleException {
    public MmsException( String message) {
        super(ExceptionType.COND_SERVICE_EXCEPTION, message);
    }
}
