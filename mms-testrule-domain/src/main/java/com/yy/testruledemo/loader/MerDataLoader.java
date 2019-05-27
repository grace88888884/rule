//package com.yy.testruledemo.loader;
//
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.yy.testrule.dao.entity.TReMerPolicy;
//import com.yy.testrule.dao.entity.TReMerStat;
//import com.yy.testruledemo.MmsContext;
//import com.yy.testrule.common.rulebo.MerTypeBo;
//import com.yy.testrule.dao.service.impl.TReMerPolicyServiceImpl;
//import com.yy.testrule.dao.service.impl.TReMerStatServiceImpl;
//import com.yy.testruleonline.rule.function.deprecated.AbstractTagFRuleFunctionDataLoader;
//import com.yy.testruleonline.utils.Constants;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//public class MerDataLoader extends AbstractTagFRuleFunctionDataLoader<MerTypeBo> {
//    @Autowired
//    public TReMerPolicyServiceImpl merPolicyService;
//    @Autowired
//    public TReMerStatServiceImpl merStatService;
//    
//    @Override
//    public MerTypeBo loadData(Map<String, Object> env) {
//        MmsContext mmsContext = (MmsContext) env.get(Constants.envMap.context);
//        TReMerStat merStat = mmsContext.getMerTypeBo().getMerStat();
//        TReMerPolicy merPolicy = mmsContext.getMerTypeBo().getMerPolicy();
//        if (merPolicy == null) {
//            EntityWrapper<TReMerPolicy> wrapper = new EntityWrapper<>();
//            wrapper.eq("mer_type", "A0");
//            merPolicy = merPolicyService.selectOne(wrapper);
//            mmsContext.getMerTypeBo().setMerPolicy(merPolicy);
//
//        }
//        if (merStat == null) {
//            EntityWrapper<TReMerStat> wrapper = new EntityWrapper<>();
//            wrapper.eq("mer_no", "110");
//            merStat = merStatService.selectOne(wrapper);
//            if(merStat==null){
//                merStat = new TReMerStat();
//            }
//            mmsContext.getMerTypeBo().setMerStat(merStat);
//        }
//
//        return mmsContext.getMerTypeBo();
//    }
//
//   
//
//
//}
