package com.yy.testruleonline.rule.function.cond;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yy.testruleonline.bo.CondBo;
import com.yy.testruleonline.enums.ExceptionType;
import com.yy.testruleonline.enums.FunctionType;
import com.yy.testruleonline.enums.OperationType;
import com.yy.testruleonline.exceptions.RuleException;
import com.yy.testruleonline.rule.function.AbstractRuleFunction;
import com.yy.testruleonline.rule.function.action.ICondResultProcessor;
import com.yy.testruleonline.utils.TagValueParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.yy.testruleonline.utils.Constants.context;


@Component
public class CondOperationFunction extends AbstractRuleFunction {
    
    @Autowired
    ICondResultProcessor condResultProcessor;
    
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject fieldsStrObj) {
        String fieldStr = fieldsStrObj.stringValue(env);
        AviatorBoolean returnResult = AviatorBoolean.FALSE;
        CondBo condBo = (CondBo) env.get(fieldStr);
        OperationType operationType = condBo.getCond().getOperation();
        try {
            Object inputParam = TagValueParser.parseInputTagType(env, condBo);
            Object outputParam = TagValueParser.parseOutputTagType(env, condBo);
            if(inputParam!=null &&outputParam!=null) {
                switch (operationType) {
                    case EQ:
                        returnResult = inputParam.equals(outputParam) ? AviatorBoolean.TRUE : AviatorBoolean.FALSE;
                        break;
                    case NEQ:
                        returnResult = inputParam.equals(outputParam) ? AviatorBoolean.FALSE : AviatorBoolean.TRUE;
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
            if(AviatorBoolean.TRUE.equals(returnResult)){
                condResultProcessor.processCondPassAction(env.get(context),condBo);
            }else {
                condResultProcessor.processCondDeclineAction(env.get(context),condBo);
            }
        }catch (Exception e){
            e.printStackTrace();
            if (e.getCause() instanceof RuleException) {
                RuleException cause = (RuleException) e.getCause();
                throw cause;
            } else if (e instanceof RuleException) {
                RuleException cause = (RuleException) e;
                throw  cause;
            } else {
                throw new RuleException(ExceptionType.COND_EXECUTE_EXCEPTION, condBo.getCond().getCondName(), e);
            }
        }
        return returnResult;
    }

    @Override
    public String getFuctionType() {
        return FunctionType.CONDITION_OPERATION;
    }

}
