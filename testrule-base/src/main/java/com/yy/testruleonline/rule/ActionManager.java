package com.yy.testruleonline.rule;

import com.googlecode.aviator.AviatorEvaluator;
import com.yy.testruleonline.bo.ActnBo;
import com.yy.testruleonline.bo.RuleBo;
import com.yy.testruleonline.dao.entity.TReActn;
import com.yy.testruleonline.enums.RuleRunResult;
import com.yy.testruleonline.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ActionManager<T> {
    
    List<String> actionFunList = new ArrayList<>();


    public void runAction(T context,Map<String, Object> responseParam, RuleBo ruleBo, boolean isSatisfied) {
        //执行行动
        if (isSatisfied && (ruleBo.getActnBoList().size() > 0 || ruleBo.getForceActionList().size() > 0)) {
            ruleBo.getActnBoList().forEach(t -> {
                Map<String, Object> actionResultMap = runAction(t);
                if (actionResultMap != null && actionResultMap.size() > 0) {
                    responseParam.putAll(actionResultMap);
                }
                responseParam.put(Constants.resultMap.ruleResult, RuleRunResult.RUN_ACTION);
            });

        

        } else if (ruleBo.getElseActnBoList() != null && ruleBo.getElseActnBoList().size() > 0) {
            ruleBo.getElseActnBoList().forEach(t -> {
                Map<String, Object> actionResultMap = runAction(t);
                if (actionResultMap != null && actionResultMap.size() > 0) {
                    responseParam.putAll(actionResultMap);
                }
                responseParam.put(Constants.resultMap.ruleResult, RuleRunResult.RUN_ELSE_ACTION);
            });
        } else {
            responseParam.put(Constants.resultMap.ruleResult, RuleRunResult.RUN_NOTHING);
        }

        ruleBo.getForceActionList().forEach(t ->
                t.doAction(context,isSatisfied)
        );
    }


    private Map<String, Object> runAction(ActnBo actnBo) {
        Map<String, Object> resultMap = new HashMap<>();
        TReActn TReActn = actnBo.getActn();
        switch (TReActn.getActnType()) {
            case FUCTION:
                String actionExpress = TReActn.getFunName()+"()";
                Object executeResult = AviatorEvaluator.execute(actionExpress);
                resultMap.put(actionExpress, executeResult);
                break;
            case PARAM:
                //todo
//                TReTag TReTag = actnBo.getTag();
//                TReTagRng param = actnBo.getTagRng();
//                resultMap.put(TReTag.getTagName(), param);
                break;
        }
        return resultMap;
    }

    public void addActionFunName(String actionFunName) {
        actionFunList.add(actionFunName);
    }
}
