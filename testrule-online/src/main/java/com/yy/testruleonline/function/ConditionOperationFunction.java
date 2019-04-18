package com.yy.testruleonline.function;

import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.bo.ConditionDetailBo;
import com.yy.testruleonline.enums.FunctionType;
import com.yy.testruleonline.enums.OperationType;
import com.yy.testruleonline.enums.TagType;
import com.yy.testruleonline.utils.Constants;
import com.yy.testruleonline.utils.Parser;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.yy.testruleonline.enums.FunctionType.CONDITION_OPERATION;
import static com.yy.testruleonline.utils.Constants.conditionInput;

@Component
public class ConditionOperationFunction extends AbstractRuleFunction {
    @Override
    public AviatorObject call(Map<String, Object> env) {
        AviatorBoolean returnResult = AviatorBoolean.FALSE;
        ConditionDetailBo conditionDetailBo = (ConditionDetailBo) env.get(Constants.conditionDetailBo);
        OperationType operationType = conditionDetailBo.getConditionDetail().getOperation();
        HashMap<String,String> input = (HashMap<String, String>) env.get(conditionInput);
        TagType type = conditionDetailBo.getTag().getType();
        Object inputParam = Parser.parseParamClassifyType(input, conditionDetailBo, type);
        Integer compareNum = null;
        if (type.equals(TagType.NUM)) {
            compareNum = ((BigDecimal) inputParam).compareTo(conditionDetailBo.getConditionDetail().getThresholdValue());
        }
        if (inputParam != null) {
            switch (operationType) {
                case EQ:
                    if (TagType.ENUM.equals(conditionDetailBo.getTag().getType())&&inputParam.equals(conditionDetailBo.getTagRange().getId())) {
                        returnResult = AviatorBoolean.TRUE;
                    }else if (compareNum != null && compareNum == 0) {
                        returnResult = AviatorBoolean.TRUE;
                    }
                    break;
                case GE:
                    if (compareNum != null && compareNum >= 0) {
                        returnResult = AviatorBoolean.TRUE;
                    }
                    break;
                case GT:
                    if (compareNum != null && compareNum > 0) {
                        returnResult = AviatorBoolean.TRUE;
                    }
                    break;
                case LE:
                    if (compareNum != null && compareNum <= 0) {
                        returnResult = AviatorBoolean.TRUE;
                    }
                    break;
                case LT:
                    if (compareNum != null && compareNum < 0) {
                        returnResult = AviatorBoolean.TRUE;
                    }
                    break;
                default:
                    break;
            }
        }
        return returnResult;
    }

    @Override
    public FunctionType getFuctionType() {
        return CONDITION_OPERATION;
    }

}
