package com.yy.testruleonline.function;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.bo.ConditionDetailBo;
import com.yy.testruleonline.bo.ConditionGroupBo;
import com.yy.testruleonline.enums.FunctionType;
import com.yy.testruleonline.enums.RelationType;
import com.yy.testruleonline.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.yy.testruleonline.enums.FunctionType.CONDITION_GROUP_RELATION;
import static com.yy.testruleonline.utils.Constants.conditionInput;
import static com.yy.testruleonline.utils.Constants.conditionOperationEquation;

@Component
public class ConditionGroupRelationFunction extends AbstractRuleFunction {
    @Override
    public AviatorObject call(Map<String, Object> env) {
        AviatorBoolean result = AviatorBoolean.TRUE;
        ConditionGroupBo conditionGroupBo = (ConditionGroupBo) env.get(Constants.conditionGroupBo);
        HashMap<String,Object> input = (HashMap<String,Object>) env.get(conditionInput);
        RelationType conditionRelation = conditionGroupBo.getConditionGroup().getConditionRelation();
        switch (conditionRelation) {
            case OR:
                break;
            case AND:
                for(ConditionDetailBo item : conditionGroupBo.getConditionDetailBoList()){
                    String expression = conditionOperationEquation;
                    Map<String, Object> params = new HashMap<>();
                    params.put(Constants.conditionDetailBo, item);
                    params.put(conditionInput, input);
                    boolean resultItem = (boolean) AviatorEvaluator.execute(expression, params);
                    if(!resultItem){
                        result = AviatorBoolean.FALSE;
                        break;
                    }

                }
                break;
            default:
                break;
        }
        return result;
    }



    @Override
    public FunctionType getFuctionType() {
        return CONDITION_GROUP_RELATION;
    }
}
