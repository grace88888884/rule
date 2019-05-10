package com.yy.testruleonline.function;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.bo.CondBo;
import com.yy.testruleonline.enums.FunctionType;
import com.yy.testruleonline.enums.OperationType;
import com.yy.testruleonline.utils.Parser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
        Object inputParam = Parser.parseInputTagType(input, condBo);
        Object outputParam = Parser.parseOutputTagType(input, condBo);
        if(inputParam!=null &&outputParam!=null) {
            switch (operationType) {
                case EQ:
                    returnResult = inputParam.equals(outputParam) ? AviatorBoolean.TRUE : AviatorBoolean.FALSE;
                    break;
                case GE:
                case GT:
                case LE:
                case LT:
                    String expression = inputParam + "-" + outputParam + operationType.getSign() + "0";
                    boolean resultItem = (boolean) AviatorEvaluator.execute(expression);
                    returnResult = resultItem ? AviatorBoolean.TRUE : AviatorBoolean.FALSE;
                    break;
            }
        }
        return returnResult;
    }

    @Override
    public String getFuctionType() {
        return FunctionType.CONDITION_OPERATION;
    }

}
