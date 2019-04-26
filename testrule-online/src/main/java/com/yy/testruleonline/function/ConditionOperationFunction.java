package com.yy.testruleonline.function;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.bo.ConditionDetailBo;
import com.yy.testruleonline.enums.FunctionType;
import com.yy.testruleonline.enums.OperationType;
import com.yy.testruleonline.enums.TagType;
import com.yy.testruleonline.utils.Constants;
import com.yy.testruleonline.utils.Parser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.yy.testruleonline.enums.FunctionType.CONDITION_OPERATION;
import static com.yy.testruleonline.utils.Constants.conditionInput;

@Component
public class ConditionOperationFunction extends AbstractRuleFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject fieldsStrObj) {
        String fieldStr = fieldsStrObj.stringValue(env);
        AviatorBoolean returnResult = AviatorBoolean.FALSE;
        ConditionDetailBo conditionDetailBo = (ConditionDetailBo) env.get(fieldStr);
        OperationType operationType = conditionDetailBo.getConditionDetail().getOperation();
        HashMap<String,String> input = (HashMap<String, String>) env.get(conditionInput);
        TagType type = conditionDetailBo.getTag().getType();
        Object inputParam = Parser.parseParamClassifyType(input, conditionDetailBo, type);
        if (type.equals(TagType.NUM)) {
            String expression = inputParam + "-" + conditionDetailBo.getConditionDetail().getThresholdValue() + operationType.getSign() + "0";
            boolean resultItem = (boolean) AviatorEvaluator.execute(expression);
            returnResult = resultItem ? AviatorBoolean.TRUE : AviatorBoolean.FALSE;

        } else if (OperationType.EQ.equals(operationType) && TagType.ENUM.equals(conditionDetailBo.getTag().getType()) && inputParam.equals(conditionDetailBo.getTagRange().getName())) {
            returnResult = AviatorBoolean.TRUE;
        }
        return returnResult;
    }

    @Override
    public FunctionType getFuctionType() {
        return CONDITION_OPERATION;
    }

}
