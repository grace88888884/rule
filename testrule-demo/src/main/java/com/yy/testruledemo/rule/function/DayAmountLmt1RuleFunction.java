package com.yy.testruledemo.rule.function;

import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.yy.testruledemo.entity.TReMerPolicy;
import com.yy.testruledemo.rule.MmsDeclineReason;
import com.yy.testruledemo.rule.enums.SelfDefineFunctionType;
import com.yy.testruledemo.rule.function.loader.MerDataLoader;
import com.yy.testruledemo.rule.rulebo.MerTypeBo;
import com.yy.testruleonline.function.loader.AbstractTagFRuleFunctionDataLoader;
import com.yy.testruleonline.function.AbstractTagFRuleFunction;
import com.yy.testruleonline.utils.RuleHashMap;

import java.math.BigDecimal;
import java.util.Map;

public class DayAmountLmt1RuleFunction extends AbstractTagFRuleFunction<MerTypeBo> {


    @Override
    public void fillResultMap(AviatorBoolean runResult, RuleHashMap<String> resultMap) {
        if(AviatorBoolean.FALSE.equals(runResult)){
            resultMap.putList(MmsDeclineReason.class.getName(),MmsDeclineReason.MER_DAY_AMT_LMT);
        }
    }

    @Override
    public AviatorBoolean runCondition(Map<String, Object> env, MerTypeBo data) {
        TReMerPolicy merPolicy = data.getMerPolicy();
        if (merPolicy != null) {
            BigDecimal dayAmtStat = data.getMerStat().getDayAmtStat()==null?BigDecimal.ZERO:data.getMerStat().getDayAmtStat();
            if(merPolicy.getDayAmtLmt()!=null&&dayAmtStat.compareTo(merPolicy.getDayAmtLmt())>0){
                return AviatorBoolean.TRUE;
            }
        }
        return  AviatorBoolean.FALSE;
    }

    @Override
    public AbstractTagFRuleFunctionDataLoader<MerTypeBo> getDataLoader() {
        return new MerDataLoader();
    }

    @Override
    public String getFuctionType() {
        return SelfDefineFunctionType.DAYAMOUNTLMT1;
    }
}
