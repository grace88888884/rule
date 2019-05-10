package com.yy.testruledemo.rule.function;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.googlecode.aviator.runtime.type.AviatorDecimal;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruledemo.entity.TReMerStat;
import com.yy.testruledemo.rule.enums.SelfDefineFunctionType;
import com.yy.testruledemo.service.impl.TReMerStatServiceImpl;
import com.yy.testruleonline.function.AbstractTagCRuleFunction;
import com.yy.testruleonline.function.annotations.Tags;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Tags({"mer_no"})
public class DayAmtStatLmt1RuleFunction extends AbstractTagCRuleFunction {
    @Autowired
    public TReMerStatServiceImpl merStatService;


    @Override
    public String getFuctionType() {
        return SelfDefineFunctionType.DAY_AMT_STAT_LMT;
    }

    @Override
    public AviatorObject calTagValue(Map<String, Object> env) {
        
        EntityWrapper<TReMerStat> wrapper = new EntityWrapper<>();
        wrapper.eq("mer_no", "110");
        TReMerStat merStat = merStatService.selectOne(wrapper);
        if (merStat == null) {
            merStat = new TReMerStat();
        }
        return new AviatorDecimal(merStat.getDayAmtStat());
    }
}
