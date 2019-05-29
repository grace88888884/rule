package com.yy.testruledemo.tag;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.googlecode.aviator.runtime.type.AviatorDecimal;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testrule.common.data.MmsContextInput;
import com.yy.testrule.common.enums.MmsType;
import com.yy.testrule.dao.entity.TReMerPolicy;
import com.yy.testrule.common.enums.SelfDefineFunctionType;
import com.yy.testruledemo.MmsContext;
import com.yy.testruledemo.loader.MerTypeLoader;
import com.yy.testrule.dao.service.impl.TReMerPolicyServiceImpl;
import com.yy.testruleonline.rule.function.tag.AbstractTagFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class DayAmtPolicyLmt1Tag extends AbstractTagFunction<MmsContext> {
    @Autowired
    public TReMerPolicyServiceImpl merPolicyService;

    @Autowired
    public MerTypeLoader merTypeLoader;


    @Override
    public String getFuctionType() {
        return SelfDefineFunctionType.DAY_AMT_POLICY_LMT1;
    }



    @Override
    public AviatorObject calTagValue(MmsContext context) {
        EntityWrapper<TReMerPolicy> wrapper = new EntityWrapper<>();
        String mmsType = context.getMmsTagBo().getMmsType();
        if (mmsType == null) {
            mmsType = merTypeLoader.loadData(context);
        }

        wrapper.eq("mer_type", mmsType);
        TReMerPolicy merPolicy = merPolicyService.selectOne(wrapper);
        return new AviatorDecimal(merPolicy.getDayAmtLmt());
    }
}
