package com.yy.testruledemo.converter;

import com.yy.testrule.common.data.MmsContextInput;
import com.yy.testruledemo.MmsContext;
import com.yy.testruleonline.rule.converter.TagConverter;
import org.springframework.stereotype.Component;

@Component("inputConverter")
public class MmsContextInputConverter implements TagConverter<MmsContextInput, MmsContext> {
    @Override
    public MmsContext convert(MmsContextInput value) {
        MmsContext mmsContext = new MmsContext();
        mmsContext.dealWithContextInput(value);
        return mmsContext;
    }
}
