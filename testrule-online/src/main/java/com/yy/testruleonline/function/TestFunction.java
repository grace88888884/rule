package com.yy.testruleonline.function;

import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.enums.FunctionType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TestFunction extends AbstractRuleFunction {


    @Override
    public AviatorObject call(Map<String, Object> env) {
        System.out.println("test success");
        return AviatorBoolean.TRUE;
    }

    @Override
    public FunctionType getFuctionType() {
        return FunctionType.TEST;
    }
}
