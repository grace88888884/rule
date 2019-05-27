package com.yy.testruleonline.rule;

import com.googlecode.aviator.AviatorEvaluator;
import com.yy.testruleonline.bo.CondGrpBo;
import com.yy.testruleonline.bo.RuleBo;
import com.yy.testruleonline.dao.service.impl.RuleServiceImpl;
import com.yy.testruleonline.enums.RuleRunResult;
import com.yy.testruleonline.rule.converter.TagConverter;
import com.yy.testruleonline.rule.service.RuleInitService;
import com.yy.testruleonline.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
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
    public RuleInitService<T> ruleInitService;

    public abstract Class<T> getContext();

    /**
     * 执行规则
     *
     * @param input
     * @param ruleName
     * @return
     */
    public Map<String, Object> executeRule(R input, String ruleName) {
        Map<String, Object> responseParam = new HashMap<>();

        try {
            //将输入转换为上下文
            T context = (T) inputConverter.convert(input);
            contextThreadLocal.set(context);

            //执行规则
            responseParam = doExecuteRule(context, ruleName, responseParam);

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

    private Map<String, Object> doExecuteRule(T context, String ruleName, Map<String, Object> responseParam) {
        RuleBo ruleBo = getRuleBoList().get(ruleName);
        boolean isSatisfied = false;
        //若找不到规则
        if(ruleBo== null) {
            responseParam.put(Constants.resultMap.responseCode, RuleRunResult.NO_RULE);
            responseParam.put(Constants.resultMap.isSatisfied, isSatisfied);
            return responseParam;
        }

        //正式执行规则
        isSatisfied = doExecuteConditionGrp(context, ruleBo);
        responseParam.put(Constants.resultMap.isSatisfied, isSatisfied);
        actionManager.runAction(context, responseParam, ruleBo, isSatisfied);

        return responseParam;
    }

    private boolean doExecuteConditionGrp(T context, RuleBo ruleBo) {
        CondGrpBo condGrpBo = ruleBo.getCondGrpBo();
        StringBuilder stringBuilder = new StringBuilder().append(conditionGroupEquation).append("('").append(Constants.conditionGroupBo).append("')");
        Map<String, Object> env = new HashMap<>();
        env.put(Constants.conditionGroupBo, condGrpBo);
        env.put(Constants.context, context);
        boolean isSatisfied = (boolean) AviatorEvaluator.execute(stringBuilder.toString(), env);
        System.out.println("isSatisfiedCondition:" + isSatisfied + "\n");
        return isSatisfied;
    }

    /**
     * 获取ruleList对象
     * @return
     */
    public Map<String, RuleBo> getRuleBoList() {
        Map<String, RuleBo> ruleBoMap = Constants.ruleBoMap;
        if (ruleBoMap == null) {
            ruleBoMap = ruleInitService.refreshRuleBoList(getContext());
        }
        return ruleBoMap;
    }

    public Map<String, RuleBo> refreshRuleBoList() {
        Map<String, RuleBo> ruleBoMap = ruleInitService.refreshRuleBoList(getContext());
        return ruleBoMap;

    }


    public ThreadLocal<T> getContextThreadLocal() {
        return contextThreadLocal;
    }


}
