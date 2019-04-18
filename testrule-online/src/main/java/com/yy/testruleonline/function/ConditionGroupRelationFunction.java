package com.yy.testruleonline.function;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;
import com.yy.testruleonline.bo.ConditionDetailBo;
import com.yy.testruleonline.bo.ConditionGroupBo;
import com.yy.testruleonline.enums.FunctionType;
import com.yy.testruleonline.enums.RelationType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.yy.testruleonline.enums.FunctionType.CONDITION_GROUP_RELATION;
import static com.yy.testruleonline.enums.FunctionType.CONDITION_OPERATION;

@Component
public class ConditionGroupRelationFunction extends AbstractRuleFunction {
    @Override
    public AviatorObject call(Map<String, Object> env) {
        AviatorBoolean result = AviatorBoolean.TRUE;
        ConditionGroupBo conditionGroupBo = (ConditionGroupBo) env.get("conditionGroupBo");
        HashMap<String,Object> input = (HashMap<String,Object>) env.get("input");
        RelationType conditionRelation = conditionGroupBo.getConditionGroup().getConditionRelation();
        switch (conditionRelation) {
            case OR:
                break;
            case AND:
                for(ConditionDetailBo item : conditionGroupBo.getConditionDetailBoList()){
                    String expression = "conditionOperation()";
                    Map<String, Object> params = new HashMap<>();
                    params.put("conditionDetailBo", item);
                    params.put("input", input);
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
