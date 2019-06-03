package com.yy.testruledemo.loader;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.testrule.dao.entity.TReMerType;
import com.yy.testrule.dao.service.impl.TReMerTypeServiceImpl;
import com.yy.testruledemo.MmsContext;
import com.yy.testruleonline.rule.function.loader.AbstractRuleFunctionDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TreeSet;

@Component
public class MerTypeLoader extends AbstractRuleFunctionDataLoader<MmsContext> {
    @Autowired
    public TReMerTypeServiceImpl merTypeService;

    @Override
    public String getData(MmsContext mmsContext) {
        Object merNo = mmsContext.getMmsTagBo().getMerNo();
        if (merNo != null) {
            QueryWrapper<TReMerType> wrapper = new QueryWrapper<>();
            wrapper.eq("mer_no", merNo);
            TReMerType tReMerType = merTypeService.getBaseMapper().selectOne(wrapper);
            return tReMerType!=null?tReMerType.getMerType():null;
        } else {
            return null;
        }
    }

    @Override
    public String getParamName() {
        return "MmsType";
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
