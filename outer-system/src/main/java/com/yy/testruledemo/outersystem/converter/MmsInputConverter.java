package com.yy.testruledemo.outersystem.converter;

import com.yy.testrule.common.data.MmsContextInput;
import com.yy.testruledemo.outersystem.AuthContext;
import com.yy.testruleonline.rule.converter.InputConverter;
import org.springframework.stereotype.Component;

@Component
public class MmsInputConverter implements InputConverter<AuthContext,MmsContextInput> {
    @Override
    public MmsContextInput convert(AuthContext value)  {
        MmsContextInput mmsContextInput = new MmsContextInput();
        mmsContextInput.setMerNo(value.getMerNo());
        mmsContextInput.setMmsType(value.getMerType());
        return mmsContextInput;
    }

}
