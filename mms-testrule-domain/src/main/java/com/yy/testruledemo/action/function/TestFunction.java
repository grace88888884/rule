package com.yy.testruledemo.action.function;

import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testrule.common.enums.SelfDefineFunctionType;
import com.yy.testruledemo.MmsContext;
import com.yy.testruleonline.rule.function.action.AbstractActionFunction;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TestFunction extends AbstractActionFunction< MmsContext> {


    @Override
    public AviatorObject call(Map<String, Object> env) {
        System.out.println("test success");
        return AviatorBoolean.TRUE;
    }

    @Override
    public String getFuctionType() {
        return SelfDefineFunctionType.TEST;
    }
}
