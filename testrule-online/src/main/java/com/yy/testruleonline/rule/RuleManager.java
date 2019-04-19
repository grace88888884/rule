package com.yy.testruleonline.rule;

import com.googlecode.aviator.AviatorEvaluator;
import com.yy.testruleonline.bo.ActionDetailBo;
import com.yy.testruleonline.bo.ConditionDetailBo;
import com.yy.testruleonline.bo.ConditionGroupBo;
import com.yy.testruleonline.bo.RuleBo;
import com.yy.testruleonline.entity.*;
import com.yy.testruleonline.enums.RuleRunResult;
import com.yy.testruleonline.mapper.*;
import com.yy.testruleonline.service.*;
import com.yy.testruleonline.utils.Constants;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.yy.testruleonline.utils.Constants.conditionGroupEquation;
import static com.yy.testruleonline.utils.Constants.conditionInput;

@Component
public class RuleManager {
//    @Autowired
//    public IConditionGroupService conditionGroupService; 
    
    @Autowired
    public ConditionGroupMapper conditionGroupMapper;
    
    @Autowired
    public ConditionDetailMapper conditionDetailMapper;
//    @Autowired
//    public IConditionDetailService conditionDetailService;
    @Autowired
    public IRuleService ruleService;
    
    @Autowired
    public ActionDetailMapper actionDetailMapper;
    @Autowired
    public TagRangeMapper tagRangeMapper;

    @Autowired
    public TagMapper tagMapper;


    public Map<String, Object> executeRule(Map<String, String> param) {
        Map<String, Object> resultMap = new HashMap<>();
        String ruleName = param.get("ruleName");
        RuleBo ruleBo = getRuleBoList().get(ruleName);
        boolean isSatisfied = executeCondition(param, ruleBo);
        if (isSatisfied) {
            resultMap = runAction(ruleBo.getActionDetailBo());
            resultMap.put(Constants.resultMap.ruleRunActionResultStr, RuleRunResult.RUN_ACTION);
        } else if (ruleBo.getElseActionDetailBo() != null) {
            resultMap = runAction(ruleBo.getElseActionDetailBo());
            resultMap.put(Constants.resultMap.ruleRunActionResultStr, RuleRunResult.RUN_ELSE_ACTION);
        } else {
            resultMap.put(Constants.resultMap.ruleRunActionResultStr, RuleRunResult.RUN_NOTHINT);
        }
        return resultMap;
    }

    private Map<String, Object> runAction(ActionDetailBo actionDetailBo) {
        Map<String, Object> resultMap = new HashMap<>();
        ActionDetail actionDetail = actionDetailBo.getActionDetail();
        switch (actionDetail.getActionType()) {
            case FUCTION:
                String actionExpress = actionDetail.getFunctionName();
                Object executeResult = AviatorEvaluator.execute(actionExpress);
                resultMap.put(actionExpress, executeResult);
                break;
            case PARAM:
                Tag tag = actionDetailBo.getTag();
                TagRange param = actionDetailBo.getTagRange();
                resultMap.put(tag.getName(), param);
                break;
        }
        return resultMap;
    }

    private boolean executeCondition(Map<String, String> param, RuleBo ruleBo) {
        ConditionGroupBo conditionGroupBo = ruleBo.getConditionGroupBo();
        String expression = conditionGroupEquation;
        Map<String, Object> tagRanges = new HashMap<>();
        tagRanges.put(Constants.conditionGroupBo, conditionGroupBo);
        tagRanges.put(conditionInput, param);
        boolean isSatisfied = (boolean) AviatorEvaluator.execute(expression, tagRanges);
        System.out.println("isSatisfiedCondition:" + isSatisfied + "\n");
        return isSatisfied;
    }


    public Map<String, RuleBo> getRuleBoList() {
        Map<String, RuleBo> ruleBoMap = Constants.ruleBoMap;
        if (ruleBoMap == null) {
            ruleBoMap = refreshRuleBoList();
        }
        return ruleBoMap;
    }

    public Map<String, RuleBo> refreshRuleBoList() {
        List<Rule> ruleList = ruleService.selectList(null);
        List<String> conditionGroupNameList = new ArrayList<>();
        Set<String> actionNameList = new HashSet<>();
        Set<String> elseActionNameListList = new HashSet<>();
        ruleList.forEach(r -> {
            String conditionGroupName = r.getConditionGroupName();
            String actionNameListStr = r.getActionName();
            String elseActionNameList = r.getElseActionNameList();
            conditionGroupNameList.add(conditionGroupName);
            actionNameList.add(actionNameListStr);
            Optional.ofNullable(elseActionNameList).ifPresent(t -> elseActionNameListList.add(t));
        });

        actionNameList.addAll(elseActionNameListList);
        List<ConditionGroup> conditionGroups = conditionGroupMapper.selectByconditionGroupNames(conditionGroupNameList);
        Map<String, ConditionGroup> conditionGroupMap = conditionGroups.stream().collect(Collectors.toMap(ConditionGroup::getName, t -> t));

        Set<String> conditionDetailNameSet = new HashSet<>();
        conditionGroups.forEach(t -> {
            String conditionDetailNameListStr = t.getConditionDetailNameList();
            if (Strings.isNotEmpty(conditionDetailNameListStr)) {
                String[] conditionDetailNames = conditionDetailNameListStr.split("\\|");
                for (int i = 0; i < conditionDetailNames.length; i++) {
                        conditionDetailNameSet.add(conditionDetailNames[i]);
                }
                t.setConditionDetailNameSet(conditionDetailNameSet);
            }
        });
        List<ConditionDetail> conditionDetails = conditionDetailMapper.selectByConditionGroupNames(conditionDetailNameSet);
        Map<String, ConditionDetail> conditionDetailMap = conditionDetails.stream().collect(Collectors.toMap(ConditionDetail::getName, t -> t));
        List<ActionDetail> actionDetails = actionDetailMapper.selectByActionDetailNames(actionNameList);
        Map<String, ActionDetail> actionDetailMap = actionDetails.stream().collect(Collectors.toMap(ActionDetail::getName, t -> t));

        List<String> tagNameList = conditionDetails.stream().map(g -> g.getTagName()).collect(Collectors.toList());
        List<String> tagNameList2 = actionDetails.stream().map(g -> g.getTagName()).collect(Collectors.toList());
        tagNameList.addAll(tagNameList2);
        List<Tag> tags = tagMapper.selectByTagNames(tagNameList);
        Map<String, Tag> tagMap = tags.stream().collect(Collectors.toMap(Tag::getName, t -> t));


        List<String> tagValueList = conditionDetails.stream().map(g -> g.getTagValue()).collect(Collectors.toList());
        List<String> tagValueList2 = actionDetails.stream().map(g -> g.getTagValue()).collect(Collectors.toList());
        tagValueList.addAll(tagValueList2);
        List<TagRange> tagRanges = tagRangeMapper.selectByTagRanges(tagValueList);
        Map<String, TagRange> paramMap = tagRanges.stream().collect(Collectors.toMap(TagRange::getName, t -> t));


        Map<String, RuleBo> ruleBoMap = new HashMap();
        ruleList.forEach(r -> {
            ConditionGroup conditionGroup = conditionGroupMap.get(r.getConditionGroupName());
            ActionDetail actionDetail = actionDetailMap.get(r.getActionName());
            ActionDetail elseActionNameList = actionDetailMap.get(r.getElseActionNameList());
            RuleBo ruleBo = new RuleBo();
            ConditionGroupBo conditionGroupBo = new ConditionGroupBo();
            conditionGroupBo.setConditionGroup(conditionGroup);

            conditionGroup.getConditionDetailNameSet().forEach(t -> {
                ConditionDetail conditionDetail = conditionDetailMap.get(t);
                Tag tag = tagMap.get(conditionDetail.getTagName());
                ConditionDetailBo conditionDetailBo = new ConditionDetailBo();
                conditionDetailBo.setTag(tag);
                if (conditionDetail.getTagValue() != null) {
                    TagRange tagRange = paramMap.get(conditionDetail.getTagValue());
                    conditionDetailBo.setTagRange(tagRange);
                }
                conditionDetailBo.setConditionDetail(conditionDetail);
                conditionGroupBo.getConditionDetailBoList().add(conditionDetailBo);

            });
            ActionDetailBo actionDetailBo = new ActionDetailBo();
            actionDetailBo.setActionDetail(actionDetail);
            Optional.ofNullable(tagMap.get(actionDetail.getTagName()))
                    .ifPresent(t -> actionDetailBo.setTag(tagMap.get(actionDetail.getTagName())));

            Optional.ofNullable(actionDetail.getTagValue())
                    .ifPresent(t -> actionDetailBo.setTagRange(paramMap.get(actionDetail.getTagValue())));


            ruleBo.setConditionGroupBo(conditionGroupBo);
            ruleBo.setActionDetailBo(actionDetailBo);
//            ruleBo.setElseActionDetailBo(actionDetailBo);//todo
            ruleBoMap.put(r.getName(), ruleBo);
        });

        return ruleBoMap;
    }

}
