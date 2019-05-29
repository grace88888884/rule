package com.yy.testruledemo.converter;

import com.yy.testrule.common.data.MmsContextOutput;
import com.yy.testruledemo.MmsContext;
import com.yy.testruleonline.rule.converter.TagConverter;
import org.springframework.stereotype.Component;

@Component("outputConverter")
public class MmsContextOutputConverter implements TagConverter<MmsContext, MmsContextOutput> {
  

    @Override
    public MmsContextOutput convert(MmsContext value) {
        MmsContextOutput mmsContextOutput = new MmsContextOutput();
        mmsContextOutput.setDeclineReasons(value.getDeclineReasonList());
        mmsContextOutput.setMerType(value.getMmsTagBo().getMmsType());
        return mmsContextOutput;
    }
}
