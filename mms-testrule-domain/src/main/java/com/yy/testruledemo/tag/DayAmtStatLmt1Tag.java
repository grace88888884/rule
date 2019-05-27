package com.yy.testruledemo.tag;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.googlecode.aviator.runtime.type.AviatorDecimal;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testrule.common.data.MmsContextInput;
import com.yy.testrule.common.enums.SelfDefineFunctionType;
import com.yy.testrule.dao.entity.TReMerStat;
import com.yy.testrule.dao.service.impl.TReMerStatServiceImpl;
import com.yy.testruledemo.MmsContext;
import com.yy.testruleonline.rule.function.action.IForceAction;
import com.yy.testruleonline.rule.function.tag.AbstractTagFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DayAmtStatLmt1Tag extends AbstractTagFunction<MmsContext> implements IForceAction<MmsContext> {
    @Autowired
    public TReMerStatServiceImpl merStatService;


    @Override
    public String getFuctionType() {
        return SelfDefineFunctionType.DAY_AMT_STAT_LMT;
    }


    @Override
    public AviatorObject calTagValue(MmsContext context) {
        String merNo = context.getMerNo();
        EntityWrapper<TReMerStat> wrapper = new EntityWrapper<>();
        wrapper.eq("mer_no", merNo);
        TReMerStat merStat = merStatService.selectOne(wrapper);
        if (merStat == null) {
            merStat = new TReMerStat();
        }
        return new AviatorDecimal(merStat.getDayAmtStat());
    }


    @Override
    public boolean doAction(MmsContext context) {
        String merNo = context.getMerNo();
        EntityWrapper<TReMerStat> wrapper = new EntityWrapper<>();
        wrapper.eq("mer_no", merNo);
        TReMerStat merStat = merStatService.selectOne(wrapper);
        merStat.setDayAmtStat(merStat.getDayAmtStat().add(BigDecimal.ONE));
        merStatService.updateById(merStat);
        return false;
    }
}
