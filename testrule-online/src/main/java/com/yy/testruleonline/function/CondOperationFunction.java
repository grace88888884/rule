package com.yy.testruleonline.function;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.bo.CondBo;
import com.yy.testruleonline.enums.FunctionType;
import com.yy.testruleonline.enums.OperationType;
import com.yy.testruleonline.enums.TagType;
import com.yy.testruleonline.utils.Parser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.yy.testruleonline.enums.FunctionType.CONDITION_OPERATION;
import static com.yy.testruleonline.utils.Constants.conditionInput;

@Component
public class CondOperationFunction extends AbstractRuleFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject fieldsStrObj) {
        String fieldStr = fieldsStrObj.stringValue(env);
        AviatorBoolean returnResult = AviatorBoolean.FALSE;
        CondBo condBo = (CondBo) env.get(fieldStr);
        OperationType operationType = condBo.getCond().getOperation();
        HashMap<String,String> input = (HashMap<String, String>) env.get(conditionInput);
        TagType type = condBo.getTag().getTagType();
        Object inputParam = Parser.parseParamClassifyType(input, condBo, type);
        if (type.equals(TagType.NUM)) {
            String expression = inputParam + "-" + condBo.getCond().getThrValue() + operationType.getSign() + "0";
            boolean resultItem = (boolean) AviatorEvaluator.execute(expression);
            returnResult = resultItem ? AviatorBoolean.TRUE : AviatorBoolean.FALSE;

        } else if (OperationType.EQ.equals(operationType) && TagType.ENUM.equals(condBo.getTag().getTagType()) && inputParam.equals(condBo.getTagRng().getTagRngName())) {
            returnResult = AviatorBoolean.TRUE;
        }
        return returnResult;
    }

    @Override
    public FunctionType getFuctionType() {
        return CONDITION_OPERATION;
    }

}
