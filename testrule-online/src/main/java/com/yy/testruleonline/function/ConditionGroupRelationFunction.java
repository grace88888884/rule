package com.yy.testruleonline.function;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.bo.ConditionDetailBo;
import com.yy.testruleonline.bo.ConditionGroupBo;
import com.yy.testruleonline.enums.FunctionType;
import com.yy.testruleonline.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yy.testruleonline.enums.FunctionType.CONDITION_GROUP_RELATION;
import static com.yy.testruleonline.utils.Constants.*;

@Component
public class ConditionGroupRelationFunction extends AbstractRuleFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject fieldsStrObj) {
        String fieldStr = fieldsStrObj.stringValue(env);
        ConditionGroupBo parentConditionGroupBo = (ConditionGroupBo) env.get(fieldStr);
        HashMap<String,Object> input = (HashMap<String,Object>) env.get(conditionInput);
        StringBuilder parentExpressionBuilder = new StringBuilder();
        List<ConditionGroupBo> conditionGroupBoList = parentConditionGroupBo.getConditionGroupBoList();
        List<ConditionDetailBo> conditionDetailBoList = parentConditionGroupBo.getConditionDetailBoList();

        Map<String, Object> tagRanges = new HashMap<>();
        tagRanges.put(conditionInput, input);
        for (int i = 0; i < conditionDetailBoList.size(); i++) {
            if(parentExpressionBuilder.length()>0){
                parentExpressionBuilder.append(parentConditionGroupBo.getConditionGroup().getConditionRelation().getCode());
            }
            ConditionDetailBo conditionDetailBo = conditionDetailBoList.get(i);
            parentExpressionBuilder.append(conditionOperationEquation).append("('").append(Constants.conditionDetailBo).append(i).append("')");
            tagRanges.put(Constants.conditionDetailBo + i, conditionDetailBo);
        }

        for (int i = 0; i < conditionGroupBoList.size(); i++) {
            if(parentExpressionBuilder.length()>0){
                parentExpressionBuilder.append(parentConditionGroupBo.getConditionGroup().getConditionRelation().getCode());
            }
            ConditionGroupBo childConditionGroupBo = conditionGroupBoList.get(i);
            parentExpressionBuilder.append(conditionGroupEquation).append("('").append(Constants.conditionGroupBo).append(i).append("')");
            tagRanges.put(Constants.conditionGroupBo + i, childConditionGroupBo);
        }

        Boolean result = (Boolean)AviatorEvaluator.execute(parentExpressionBuilder.toString(), tagRanges);
        return result?AviatorBoolean.TRUE:AviatorBoolean.FALSE;
    }



    @Override
    public FunctionType getFuctionType() {
        return CONDITION_GROUP_RELATION;
    }
}
