package com.yy.testruledemo.rule.function.loader;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yy.testruledemo.entity.TReMerPolicy;
import com.yy.testruledemo.entity.TReMerStat;
import com.yy.testruledemo.rule.rulebo.MccContext;
import com.yy.testruledemo.rule.rulebo.MerTypeBo;
import com.yy.testruledemo.service.impl.TReMerPolicyServiceImpl;
import com.yy.testruledemo.service.impl.TReMerStatServiceImpl;
import com.yy.testruleonline.function.loader.AbstractTagFRuleFunctionDataLoader;
import com.yy.testruleonline.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class MerDataLoader extends AbstractTagFRuleFunctionDataLoader<MerTypeBo> {
    @Autowired
    public TReMerPolicyServiceImpl merPolicyService;
    @Autowired
    public TReMerStatServiceImpl merStatService;
    
    @Override
    public MerTypeBo loadData(Map<String, Object> env) {
        MccContext mccContext = (MccContext) env.get(Constants.envMap.context);
        TReMerStat merStat = mccContext.getMerTypeBo().getMerStat();
        TReMerPolicy merPolicy = mccContext.getMerTypeBo().getMerPolicy();
        if (merPolicy == null) {
            EntityWrapper<TReMerPolicy> wrapper = new EntityWrapper<>();
            wrapper.eq("mer_type", "A0");
            merPolicy = merPolicyService.selectOne(wrapper);
            mccContext.getMerTypeBo().setMerPolicy(merPolicy);

        }
        if (merStat == null) {
            EntityWrapper<TReMerStat> wrapper = new EntityWrapper<>();
            wrapper.eq("mer_no", "110");
            merStat = merStatService.selectOne(wrapper);
            if(merStat==null){
                merStat = new TReMerStat();
            }
            mccContext.getMerTypeBo().setMerStat(merStat);
        }

        return mccContext.getMerTypeBo();
    }
}
