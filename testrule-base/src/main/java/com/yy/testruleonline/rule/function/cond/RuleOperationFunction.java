package com.yy.testruleonline.rule.function.cond;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.bo.CondBo;
import com.yy.testruleonline.enums.ExceptionType;
import com.yy.testruleonline.enums.FunctionType;
import com.yy.testruleonline.exceptions.ExceptionUtils;
import com.yy.testruleonline.exceptions.RuleException;
import com.yy.testruleonline.rule.function.AbstractRuleFunction;
import com.yy.testruleonline.rule.service.RuleFlowInitService;
import com.yy.testruleonline.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yy.testruleonline.utils.Constants.context;

@Component
public class RuleOperationFunction extends AbstractRuleFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject fieldsStrObj) {
        AviatorBoolean returnResult = AviatorBoolean.FALSE;
        try {
            String ruleOperation = (String) env.get(Constants.ruleOperation);
            Map<String, CondBo> condBoMap = (Map<String, CondBo>) env.get(Constants.condBoMap);

            Map<String, Object> param = new HashMap<>();
            param.put(context, env.get(context));
            List<String> conditionNameList = new ArrayList<>();
            RuleFlowInitService.initConditionNameSet(ruleOperation,conditionNameList);
            for (int i = 0; i < conditionNameList.size(); i++) {
                StringBuilder parentExpressionBuilder = new StringBuilder();
                CondBo condBo = condBoMap.get(conditionNameList.get(i));
                ruleOperation = ruleOperation.replace(conditionNameList.get(i), parentExpressionBuilder.append(FunctionType.CONDITION_OPERATION).append("('").append(conditionNameList.get(i)).append("')"));
                param.put(conditionNameList.get(i), condBo);
            }
            Boolean result = (Boolean) AviatorEvaluator.execute(ruleOperation, param);
            returnResult= result ? AviatorBoolean.TRUE : AviatorBoolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getCause() instanceof RuleException) {
                RuleException cause = (RuleException) e.getCause();
                throw cause;
            } else if (e instanceof RuleException) {
                RuleException cause = (RuleException) e;
                throw cause;
            } else {
                e = new RuleException(ExceptionType.COND_RELATION_EXECUTE_EXCEPTION,  e);
                ExceptionUtils.addExcption(env, e);
            }
        }
        return returnResult;
    }



    @Override
    public String getFuctionType() {
        return FunctionType.RULE_OPERATION;
    }
}
