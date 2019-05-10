package com.yy.testruledemo.rule.function;

import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruledemo.rule.enums.SelfDefineFunctionType;
import com.yy.testruleonline.function.AbstractRuleFunction;
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
    public String getFuctionType() {
        return SelfDefineFunctionType.DAYAMOUNTLMT1;
    }
}
