package com.yy.testruledemo.tag;

import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;
import com.yy.testrule.common.enums.SelfDefineFunctionType;
import com.yy.testrule.dao.service.impl.TReMerPolicyServiceImpl;
import com.yy.testruledemo.MmsContext;
import com.yy.testruledemo.loader.MerTypeLoader;
import com.yy.testruleonline.rule.function.tag.AbstractTagFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MmsTypeTag extends AbstractTagFunction<MmsContext> {
    @Autowired
    public MerTypeLoader merTypeLoader;


    @Override
    public String getFuctionType() {
        return SelfDefineFunctionType.MER_TYPE;
    }


    @Override
    public AviatorObject calTagValue(MmsContext context) {
        String merType = merTypeLoader.loadData(context);
        return new AviatorString(merType);
    }
}
