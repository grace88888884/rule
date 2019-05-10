package com.yy.testruledemo.rule.function;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.googlecode.aviator.runtime.type.AviatorDecimal;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruledemo.entity.TReMerPolicy;
import com.yy.testruledemo.rule.enums.SelfDefineFunctionType;
import com.yy.testruledemo.service.impl.TReMerPolicyServiceImpl;
import com.yy.testruleonline.function.AbstractTagCRuleFunction;
import com.yy.testruleonline.function.annotations.Tags;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Tags({"mer_type"})
public class DayAmtPolicyLmt1RuleFunction extends AbstractTagCRuleFunction {
    @Autowired
    public TReMerPolicyServiceImpl merPolicyService;


    @Override
    public String getFuctionType() {
        return SelfDefineFunctionType.DAY_AMT_POLICY_LMT1;
    }

    @Override
    public AviatorObject calTagValue(Map<String, Object> env) {
        EntityWrapper<TReMerPolicy> wrapper = new EntityWrapper<>();
        wrapper.eq("mer_type", "A0");
        TReMerPolicy merPolicy = merPolicyService.selectOne(wrapper);
        return new AviatorDecimal(merPolicy.getDayAmtLmt());

    }
}
