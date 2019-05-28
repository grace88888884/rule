package com.yy.testruledemo.action;

import com.yy.testrule.common.enums.MmsDeclineReason;
import com.yy.testrule.common.utils.EnumUtil;
import com.yy.testruledemo.MmsContext;
import com.yy.testruleonline.bo.CondBo;
import com.yy.testruleonline.rule.AbstractRuleManager;
import com.yy.testruleonline.rule.function.action.ICondResultProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public  class CondResultProcessor implements ICondResultProcessor {
    @Autowired
    AbstractRuleManager ruleManager;

    public void processCondDeclineAction(Object context, CondBo condBo) {
        String declineReason = condBo.getCond().getDeclineReason();
        if(declineReason!=null) {
            MmsDeclineReason mmsDeclineReason = EnumUtil.getByCode(declineReason, MmsDeclineReason.class);
            MmsContext mmsContext = (MmsContext) context;
            mmsContext.addDeclineReason(mmsDeclineReason);
        }
    }

    @Override
    public void processCondPassAction(Object context, CondBo condBo) {
        
    }
}
