package com.yy.testruleonline.function;

import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.bo.ConditionDetailBo;
import com.yy.testruleonline.enums.FunctionType;
import com.yy.testruleonline.enums.OperationType;
import com.yy.testruleonline.enums.ParamClassifyType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.yy.testruleonline.enums.FunctionType.CONDITION_OPERATION;
@Component
public class ConditionOperationFunction extends AbstractRuleFunction {
    @Override
    public AviatorObject call(Map<String, Object> env) {
        AviatorBoolean result = AviatorBoolean.FALSE;
        ConditionDetailBo conditionDetailBo = (ConditionDetailBo) env.get("conditionDetailBo");
        OperationType operationType = conditionDetailBo.getConditionDetail().getOperation();
        HashMap<String,String> input = (HashMap<String, String>) env.get("input");
        switch (operationType) {
            case EQU:
                ParamClassifyType type = conditionDetailBo.getParamClassify().getType();
                if(ParamClassifyType.ENUM.equals(type)){
                    if (Integer.parseInt( input.get(conditionDetailBo.getParamClassify().getName())) == conditionDetailBo.getParam().getId()) {
                        result = AviatorBoolean.TRUE;
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
        return CONDITION_OPERATION;
    }

}
