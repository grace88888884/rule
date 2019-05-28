package com.yy.testruledemo.outersystem.converter;

import com.yy.testrule.common.data.MmsContextOutput;
import com.yy.testruledemo.outersystem.AuthContext;
import com.yy.testruleonline.rule.converter.OutputConverter;
import org.springframework.stereotype.Component;

@Component
public class MmsOutputConverter implements OutputConverter<AuthContext, MmsContextOutput> {

    @Override
    public void convert(MmsContextOutput value, AuthContext output)  {
        output.setOutput1(value.getOutput1());
        output.setOutput2(value.getOutput2());
        output.setDeclineReasons(value.getDeclineReasons());
        output.setMerType(value.getMerType());
    }

    @Override
    public Class<MmsContextOutput> getOutputClass() {
        return MmsContextOutput.class;
    }

}
