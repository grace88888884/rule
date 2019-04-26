package com.yy.testruleonline.rule;

import com.googlecode.aviator.AviatorEvaluator;
import com.yy.testruleonline.bo.ActionDetailBo;
import com.yy.testruleonline.bo.ConditionDetailBo;
import com.yy.testruleonline.bo.ConditionGroupBo;
import com.yy.testruleonline.bo.RuleBo;
import com.yy.testruleonline.entity.*;
import com.yy.testruleonline.enums.RuleRunResult;
import com.yy.testruleonline.mapper.*;
import com.yy.testruleonline.service.IRuleService;
import com.yy.testruleonline.utils.Constants;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.yy.testruleonline.utils.Constants.conditionGroupEquation;
import static com.yy.testruleonline.utils.Constants.conditionInput;

@Component
public class RuleManager {
  
    
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
        final Map<String, Object> resultMap = new HashMap<>();
        String ruleName = param.get("ruleName");
        RuleBo ruleBo = getRuleBoList().get(ruleName);
        boolean isSatisfied = executeCondition(param, ruleBo);
        if (isSatisfied && ruleBo.getActionDetailBoList() != null && ruleBo.getActionDetailBoList().size() > 0) {
            ruleBo.getActionDetailBoList().forEach(t -> {
                Map<String, Object> actionResultMap = runAction(t);
                if (actionResultMap != null && actionResultMap.size() > 0) {
                    resultMap.putAll(actionResultMap);
                }
                resultMap.put(Constants.resultMap.ruleRunActionResultStr, RuleRunResult.RUN_ACTION);
            });

        } else if (ruleBo.getElseActionDetailBoList() != null && ruleBo.getElseActionDetailBoList().size() > 0) {
            ruleBo.getElseActionDetailBoList().forEach(t -> {
                Map<String, Object> actionResultMap = runAction(t);
                if (actionResultMap != null && actionResultMap.size() > 0) {
                    resultMap.putAll(actionResultMap);
                }
                resultMap.put(Constants.resultMap.ruleRunActionResultStr, RuleRunResult.RUN_ELSE_ACTION);
            });
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
        StringBuilder stringBuilder = new StringBuilder().append(conditionGroupEquation).append("('").append(Constants.conditionGroupBo).append("')");
        Map<String, Object> tagRanges = new HashMap<>();
        tagRanges.put(Constants.conditionGroupBo, conditionGroupBo);
        tagRanges.put(conditionInput, param);
        boolean isSatisfied = (boolean) AviatorEvaluator.execute(stringBuilder.toString(), tagRanges);
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
        Set<String> conditionGroupNameList = new HashSet<>();
        Set<String> actionNameList = new HashSet<>();
        Set<String> elseActionNameList = new HashSet<>();
        ruleList.forEach(r -> {
            String conditionGroupName = r.getConditionGroupName();
            String actionNameListStr = r.getActionNameList();
            String elseActionNameListStr = r.getElseActionNameList();
            if(!Strings.isBlank(actionNameListStr)){
                String[] actionNameStrs = actionNameListStr.split("\\|");
                for (int i = 0; i < actionNameStrs.length; i++) {
                    actionNameList.add(actionNameStrs[i]);
                }

            } 
            
            if(!Strings.isBlank(elseActionNameListStr)){
                String[] elseNameStrs = elseActionNameListStr.split("\\|");
                for (int i = 0; i < elseNameStrs.length; i++) {
                    elseActionNameList.add(elseNameStrs[i]);
                }

            }
            conditionGroupNameList.add(conditionGroupName);
        });

        actionNameList.addAll(elseActionNameList);
        List<ConditionGroup> conditionGroups = conditionGroupMapper.selectByConditionGroupNames(conditionGroupNameList);

        List<String> conditionRootNameList = conditionGroups.stream().map(g -> g.getName()).collect(Collectors.toList());
        conditionGroups = conditionGroupMapper.selectByRootConditionGroupName(conditionRootNameList);
        conditionGroups.forEach(t -> {
            String childConditionGroupNameList = t.getChildConditionGroupNameList();
            if (Strings.isNotEmpty(childConditionGroupNameList)) {
                String[] childConditionGroupName = childConditionGroupNameList.split("\\|");
                for (int i = 0; i < childConditionGroupName.length; i++) {
                    conditionGroupNameList.add(childConditionGroupName[i]);
                }
            }
        });
        
        conditionGroups = conditionGroupMapper.selectByConditionGroupNames(conditionGroupNameList);
        Map<String, ConditionGroup> conditionGroupMap = conditionGroups.stream().collect(Collectors.toMap(ConditionGroup::getName, t -> t));


        Set<String> conditionDetailNameAllSet = new HashSet<>();

        conditionGroups.forEach(t -> {
            String conditionDetailNameListStr = t.getConditionDetailNameList();
            Set<String> conditionDetailNameSet = new HashSet<>();
            if (Strings.isNotEmpty(conditionDetailNameListStr)) {
                String[] conditionDetailNames = conditionDetailNameListStr.split("\\|");
                for (int i = 0; i < conditionDetailNames.length; i++) {
                        conditionDetailNameSet.add(conditionDetailNames[i]);
                    conditionDetailNameAllSet.add(conditionDetailNames[i]);
                }
                t.setConditionDetailNameSet(conditionDetailNameSet);
                Set<String> childConditionGroupNameSet = new HashSet<>();
                String childConditionGroupNameList = t.getChildConditionGroupNameList();
                if (Strings.isNotEmpty(childConditionGroupNameList)) {
                    String[] childConditionGroupName = childConditionGroupNameList.split("\\|");
                    for (int i = 0; i < childConditionGroupName.length; i++) {
                        childConditionGroupNameSet.add(childConditionGroupName[i]);
                    }
                    t.setChildConditionGroupNameSet(childConditionGroupNameSet);
                }
            }
        });
        List<ConditionDetail> conditionDetails = conditionDetailMapper.selectByConditionGroupNames(conditionDetailNameAllSet);
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
            List<ActionDetailBo> actionDetailBoList = new ArrayList<>();
            if (r.getActionNameList() != null) {
                String[] actionDetailNames = r.getActionNameList().split("\\|");
                for (int i = 0; i < actionDetailNames.length; i++) {
                    Optional.ofNullable(actionDetailMap.get(actionDetailNames[i])).ifPresent(t -> {
                        ActionDetailBo actionDetailBo = getActionDetailBo(tagMap, paramMap, actionDetailBoList, t);
                        actionDetailBoList.add(actionDetailBo);

                    });
                }

            }

            List<ActionDetailBo> elseActionDetailBoList = new ArrayList<>();
            if (r.getElseActionNameList() != null) {
                String[] actionDetailNames = r.getElseActionNameList().split("\\|");
                for (int i = 0; i < actionDetailNames.length; i++) {
                    Optional.ofNullable(actionDetailMap.get(actionDetailNames[i])).ifPresent(t -> {
                        ActionDetailBo actionDetailBo = getActionDetailBo(tagMap, paramMap, actionDetailBoList, t);
                        elseActionDetailBoList.add(actionDetailBo);
                    });
                }

            }
            
            RuleBo ruleBo = new RuleBo();
            ConditionGroupBo conditionGroupBo = initConditionGroupBo(conditionDetailMap, tagMap, paramMap, conditionGroupMap, r.getConditionGroupName());
            ruleBo.setConditionGroupBo(conditionGroupBo);
            ruleBo.setActionDetailBoList(actionDetailBoList);
            ruleBo.setElseActionDetailBoList(elseActionDetailBoList);
            ruleBoMap.put(r.getName(), ruleBo);
        });

        return ruleBoMap;
    }

    private ActionDetailBo getActionDetailBo(Map<String, Tag> tagMap, Map<String, TagRange> paramMap, List<ActionDetailBo> actionDetailBoList, ActionDetail t) {
        ActionDetailBo actionDetailBo = new ActionDetailBo();
        actionDetailBo.setActionDetail(t);
        Optional.ofNullable(t.getTagName())
                .ifPresent(tagName -> {
                    actionDetailBo.setTag(tagMap.get(tagName));
                    actionDetailBo.setTagRange(paramMap.get(t.getTagValue()));
                });
        actionDetailBoList.add(actionDetailBo);
        return actionDetailBo;
    }

    private ConditionGroupBo initConditionGroupBo(Map<String, ConditionDetail> conditionDetailMap, Map<String, Tag> tagMap, Map<String, TagRange> paramMap, Map<String, ConditionGroup> conditionGroupMap, String conditionGroupName) {
        ConditionGroup conditionGroup = conditionGroupMap.get(conditionGroupName);
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
        if (conditionGroup.getChildConditionGroupNameSet() != null) {
            conditionGroup.getChildConditionGroupNameSet().forEach(t -> {
                ConditionGroupBo childCondtionGroupBo = initConditionGroupBo(conditionDetailMap, tagMap, paramMap, conditionGroupMap, t);
                conditionGroupBo.getConditionGroupBoList().add(childCondtionGroupBo);
            });
        }


        return conditionGroupBo;
    }


//    private void initConditionGroup(Map<String, ConditionDetail> conditionDetailMap, Map<String, Tag> tagMap, Map<String, TagRange> paramMap, ConditionGroup conditionGroup, ConditionGroupBo conditionGroupBo) {
//        conditionGroup.getConditionDetailNameSet().forEach(t -> {
//            ConditionDetail conditionDetail = conditionDetailMap.get(t);
//            Tag tag = tagMap.get(conditionDetail.getTagName());
//            ConditionDetailBo conditionDetailBo = new ConditionDetailBo();
//            conditionDetailBo.setTag(tag);
//            if (conditionDetail.getTagValue() != null) {
//                TagRange tagRange = paramMap.get(conditionDetail.getTagValue());
//                conditionDetailBo.setTagRange(tagRange);
//            }
//            conditionDetailBo.setConditionDetail(conditionDetail);
//            conditionGroupBo.getConditionDetailBoList().add(conditionDetailBo);
//
//        });
//    }

}
