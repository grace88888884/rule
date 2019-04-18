package com.yy.testruleonline.function;

import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.bo.ConditionDetailBo;
import com.yy.testruleonline.enums.FunctionType;
import com.yy.testruleonline.enums.OperationType;
import com.yy.testruleonline.enums.ParamClassifyType;
import com.yy.testruleonline.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.yy.testruleonline.enums.FunctionType.CONDITION_OPERATION;
import static com.yy.testruleonline.utils.Constants.conditionInput;

@Component
public class ConditionOperationFunction extends AbstractRuleFunction {
    @Override
    public AviatorObject call(Map<String, Object> env) {
        AviatorBoolean result = AviatorBoolean.FALSE;
        ConditionDetailBo conditionDetailBo = (ConditionDetailBo) env.get(Constants.conditionDetailBo);
        OperationType operationType = conditionDetailBo.getConditionDetail().getOperation();
        HashMap<String,String> input = (HashMap<String, String>) env.get(conditionInput);
        
        
        switch (operationType) {
            case EQ:
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
