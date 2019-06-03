package com.yy.testruleonline.rule;

import com.googlecode.aviator.AviatorEvaluator;
import com.yy.testruleonline.bo.CondGrpBo;
import com.yy.testruleonline.bo.RuleBo;
import com.yy.testruleonline.bo.RuleFlowBo;
import com.yy.testruleonline.dao.service.impl.RuleServiceImpl;
import com.yy.testruleonline.enums.ExceptionType;
import com.yy.testruleonline.enums.RuleRunResult;
import com.yy.testruleonline.exceptions.ExceptionUtils;
import com.yy.testruleonline.exceptions.RuleException;
import com.yy.testruleonline.rule.converter.TagConverter;
import com.yy.testruleonline.rule.service.RuleFlowInitService;
import com.yy.testruleonline.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yy.testruleonline.utils.Constants.conditionGroupEquation;

public abstract class AbstractRuleManager<R, T> {
    private ThreadLocal<T> contextThreadLocal = new ThreadLocal<>();

    @Autowired
    @Qualifier("inputConverter")
    private TagConverter inputConverter;

    @Autowired
    @Qualifier("outputConverter")
    private TagConverter outputConverter;

    @Autowired
    private ActionManager<T> actionManager;


 
    @Autowired
    public RuleServiceImpl ruleService;
    

    @Autowired
    public RuleFlowInitService<T> ruleFlowInitService;

    public abstract Class<T> getContext();

    /**
     * 执行规则
     *
     * @param input
     * @param ruleFlowName
     * @return
     */
    public Map<String, Object> executeRuleFlow(R input, String ruleFlowName) {
        Map<String, Object> responseParam = new HashMap<>();

        try {
            //将输入转换为上下文
            T context = (T) inputConverter.convert(input);
            contextThreadLocal.set(context);

            //执行规则
            responseParam = doExecuteRuleFlow(context, ruleFlowName, responseParam);

            //转换上下文至输出
            R output = (R) outputConverter.convert(context);

            responseParam.put(Constants.resultMap.result, output);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            contextThreadLocal.remove();
        }
        return responseParam;
    }

    private Map<String, Object> doExecuteRuleFlow(T context, String ruleName, Map<String, Object> responseParam) {
        RuleFlowBo ruleFlowBo = getRuleFlowBoList().get(ruleName);
        boolean isRuleFlowSatisfied = true;
        //若找不到规则流
        if (ruleFlowBo == null) {
            responseParam.put(Constants.resultMap.ruleResult, RuleRunResult.NO_RULE);
            responseParam.put(Constants.resultMap.isSatisfied, isRuleFlowSatisfied);
            return responseParam;
        }
        List<RuleBo> ruleList = ruleFlowBo.getRuleList();
        boolean isSatisfied ;
        switch (ruleFlowBo.getRuleFlow().getRuleRltType()) {
            case SEQ:
                for (RuleBo ruleBo : ruleList) {
                    isSatisfied = doExecuteRule(context, ruleBo, responseParam);
                    if(!isSatisfied){
                        isRuleFlowSatisfied =false;
                        break;
                    }
                }
                break;
            case PARALLEL:
                for (RuleBo ruleBo : ruleList) {
                    isSatisfied = doExecuteRule(context, ruleBo, responseParam);
                    if(!isSatisfied){
                        isRuleFlowSatisfied =false;
                    }
                }
                break;
            default:
                break;

        }
        responseParam.put(Constants.resultMap.isSatisfied, isRuleFlowSatisfied);
        return responseParam;
    }

    private boolean doExecuteRule(T context, RuleBo ruleBo, Map<String, Object> responseParam) {
        boolean isSatisfied = false;
        //规则
        CondGrpBo condGrpBo = ruleBo.getCondGrpBo();
        Map<String, Object> env = new HashMap<>();
        env.put(Constants.conditionGroupBo, condGrpBo);
        env.put(Constants.context, context);
        try {
            StringBuilder stringBuilder = new StringBuilder().append(conditionGroupEquation).append("('").append(Constants.conditionGroupBo).append("')");
         
            isSatisfied = (boolean) AviatorEvaluator.execute(stringBuilder.toString(), env);
            System.out.println("isSatisfiedCondition:" + isSatisfied + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Exception exception;
            if (e.getCause() instanceof RuleException) {
                 exception = (RuleException) e.getCause();
            } else if (e instanceof RuleException) {
                exception =  e;
            } else { 
                exception = new RuleException(ExceptionType.COND_RULE_EXECUTE_EXCEPTION,ruleBo.getRule().getRuleName(),e);
            }
            ExceptionUtils.addExcption(env,exception);
        }

        responseParam.put(Constants.resultMap.ruleException, ExceptionUtils.parseExceptionString(env.get(Constants.ruleException)));

        //行动
        actionManager.runAction(context, responseParam, ruleBo, isSatisfied);
        return isSatisfied;

    }

    /**
     * 获取ruleList对象
     * @return
     */
    public Map<String, RuleFlowBo> getRuleFlowBoList() {
        Map<String, RuleFlowBo> ruleBoMap = Constants.ruleFlowBoMap;
        if (ruleBoMap == null) {
            Constants.ruleFlowBoMap = ruleFlowInitService.refreshRuleFlowBoList(getContext());
        }
        return Constants.ruleFlowBoMap;
    }

    public Map<String, RuleFlowBo> refreshRuleFlowBoList() {
        Map<String, RuleFlowBo> ruleBoMap = ruleFlowInitService.refreshRuleFlowBoList(getContext());
        return ruleBoMap;

    }


    public ThreadLocal<T> getContextThreadLocal() {
        return contextThreadLocal;
    }


}
