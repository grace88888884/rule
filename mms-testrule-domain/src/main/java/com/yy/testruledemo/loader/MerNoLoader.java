package com.yy.testruledemo.loader;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yy.testrule.dao.entity.TReMerType;
import com.yy.testrule.common.data.MmsContextInput;
import com.yy.testrule.dao.service.impl.TReMerTypeServiceImpl;
import com.yy.testruledemo.MmsContext;
import com.yy.testruleonline.rule.function.loader.AbstractRuleFunctionDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TreeSet;

@Component
public class MerNoLoader extends AbstractRuleFunctionDataLoader<MmsContext> {
    @Autowired
    public TReMerTypeServiceImpl merTypeService;

    @Override
    public String getData(MmsContext mmsContext) {
        Object xxx = mmsContext.getMerNo();
        if (xxx != null) {
            EntityWrapper<TReMerType> wrapper = new EntityWrapper<>();
            wrapper.eq("xxx", xxx);
            TReMerType tReMerType = merTypeService.selectOne(wrapper);
            return "110";
        } else {
            return null;
        }
    }

    @Override
    public String getParamName() {
        return "MerNo";
    }


    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void initFormLoader(TreeSet<AbstractRuleFunctionDataLoader> formLoaderList) {
        
        return;
    }

}
