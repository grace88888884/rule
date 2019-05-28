package com.yy.testruleonline.rule.function.cond;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.bo.CondBo;
import com.yy.testruleonline.bo.CondGrpBo;
import com.yy.testruleonline.enums.ExceptionType;
import com.yy.testruleonline.enums.FunctionType;
import com.yy.testruleonline.exceptions.ExceptionUtils;
import com.yy.testruleonline.exceptions.RuleException;
import com.yy.testruleonline.rule.function.AbstractRuleFunction;
import com.yy.testruleonline.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yy.testruleonline.utils.Constants.*;

@Component
public class CondGrpRelationFunction extends AbstractRuleFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject fieldsStrObj) {
        AviatorBoolean returnResult = AviatorBoolean.FALSE;
        try {
            String fieldStr = fieldsStrObj.stringValue(env);
            CondGrpBo parentCondGrpBo = (CondGrpBo) env.get(fieldStr);
            StringBuilder parentExpressionBuilder = new StringBuilder();
            List<CondGrpBo> condGrpBoList = parentCondGrpBo.getCondGrpBoList();
            List<CondBo> condBoList = parentCondGrpBo.getCondBoList();

            Map<String, Object> param = new HashMap<>();
            param.put(context, env.get(context));
            for (int i = 0; i < condBoList.size(); i++) {
                if (parentExpressionBuilder.length() > 0) {
                    parentExpressionBuilder.append(parentCondGrpBo.getCondGrp().getCondRelt().getCode());
                }
                CondBo condBo = condBoList.get(i);
                parentExpressionBuilder.append(conditionOperationEquation).append("('").append(Constants.conditionDetailBo).append(i).append("')");
                param.put(Constants.conditionDetailBo + i, condBo);
            }

            for (int i = 0; i < condGrpBoList.size(); i++) {
                if (parentExpressionBuilder.length() > 0) {
                    parentExpressionBuilder.append(parentCondGrpBo.getCondGrp().getCondRelt().getCode());
                }
                CondGrpBo childCondGrpBo = condGrpBoList.get(i);
                parentExpressionBuilder.append(conditionGroupEquation).append("('").append(Constants.conditionGroupBo).append(i).append("')");
                param.put(Constants.conditionGroupBo + i, childCondGrpBo);
            }

            Boolean result = (Boolean) AviatorEvaluator.execute(parentExpressionBuilder.toString(), param);
            if(param.get(ruleException)!=null&&param.get(ruleException) instanceof ArrayList) {
                ExceptionUtils.addExcption(env, (ArrayList<Exception>)param.get(ruleException));
            }
            returnResult= result ? AviatorBoolean.TRUE : AviatorBoolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof RuleException) {
            } else {
                e = new RuleException(ExceptionType.COND_RELATION_EXECUTE_EXCEPTION,  e);
            }
            ExceptionUtils.addExcption(env, e);
        }
        return returnResult;
    }



    @Override
    public String getFuctionType() {
        return FunctionType.CONDITION_GROUP_RELATION;
    }
}
