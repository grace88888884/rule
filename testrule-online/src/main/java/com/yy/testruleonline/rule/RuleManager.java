package com.yy.testruleonline.rule;

import com.googlecode.aviator.AviatorEvaluator;
import com.yy.testruleonline.bo.*;
import com.yy.testruleonline.entity.*;
import com.yy.testruleonline.enums.RuleRunResult;
import com.yy.testruleonline.enums.TagType;
import com.yy.testruleonline.mapper.*;
import com.yy.testruleonline.service.impl.RuleServiceImpl;
import com.yy.testruleonline.utils.Constants;
import com.yy.testruleonline.utils.RuleHashMap;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.yy.testruleonline.utils.Constants.conditionGroupEquation;

@Component
public class RuleManager {
    
    @Autowired
    public TReCondGrpMapper condGrpMapper;
    
    @Autowired
    public TReCondMapper condMapper;
    @Autowired
    public TReFunMapper funMapper;
    @Autowired
    public RuleServiceImpl ruleService;
    
    @Autowired
    public TReActnMapper actnMapper;
    @Autowired
    public TReTagRngMapper tagRngMapper;

    @Autowired
    public TReTagMapper tagMapper;


    public Map<String, Object> executeRule(Map<String, String> param, String ruleName) {
        final RuleHashMap<String> resultMap = new RuleHashMap<>();
        RuleBo ruleBo = getRuleBoList().get(ruleName);
        if(ruleBo==null){
            resultMap.put(Constants.resultMap.ruleRunActionResultStr,RuleRunResult.RUN_NOTHINT);
            return resultMap;
        }
        boolean isSatisfied = executeCondition(param, ruleBo, resultMap);
        if (isSatisfied && ruleBo.getActnBoList() != null && ruleBo.getActnBoList().size() > 0) {
            ruleBo.getActnBoList().forEach(t -> {
                Map<String, Object> actionResultMap = runAction(t);
                if (actionResultMap != null && actionResultMap.size() > 0) {
                    resultMap.putAll(actionResultMap);
                }
                resultMap.put(Constants.resultMap.ruleRunActionResultStr, RuleRunResult.RUN_ACTION);
            });

        } else if (ruleBo.getElseActnBoList() != null && ruleBo.getElseActnBoList().size() > 0) {
            ruleBo.getElseActnBoList().forEach(t -> {
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

    public Map<String, Object> executeRule(Map<String, String> param) {
        String ruleName = param.get("ruleName");
        return executeRule(param, ruleName);
    }

    private Map<String, Object> runAction(ActnBo actnBo) {
        Map<String, Object> resultMap = new HashMap<>();
        TReActn TReActn = actnBo.getActn();
        switch (TReActn.getActnType()) {
            case FUCTION:
                String actionExpress = TReActn.getFunName();
                Object executeResult = AviatorEvaluator.execute(actionExpress);
                resultMap.put(actionExpress, executeResult);
                break;
            case PARAM:
                TReTag TReTag = actnBo.getTag();
                TReTagRng param = actnBo.getTagRng();
                resultMap.put(TReTag.getTagName(), param);
                break;
        }
        return resultMap;
    }

    private boolean executeCondition(Map<String, String> param, RuleBo ruleBo, RuleHashMap<String> resultMap) {
        CondGrpBo condGrpBo = ruleBo.getCondGrpBo();
        StringBuilder stringBuilder = new StringBuilder().append(conditionGroupEquation).append("('").append(Constants.conditionGroupBo).append("')");
        Map<String, Object> env = new HashMap<>();
        env.put(Constants.conditionGroupBo, condGrpBo);
        env.put(Constants.conditionInput, param);
        env.put(Constants.resultMap.resultMap, resultMap);
        boolean isSatisfied = (boolean) AviatorEvaluator.execute(stringBuilder.toString(), env);
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
        List<TReRule> ruleList = ruleService.selectList(null);
        Set<String> conditionGroupNameList = new HashSet<>();
        Set<String> actionNameList = new HashSet<>();
        Set<String> elseActionNameList = new HashSet<>();
        ruleList.forEach(r -> {
            String conditionGroupName = r.getCondGrpName();
            String actionNameListStr = r.getActnNameList();
            String elseActionNameListStr = r.getElseActnNameList();
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
        List<TReCondGrp> conditionGroups = new ArrayList<>();
        if(conditionGroupNameList.size()>0) {
            conditionGroups = condGrpMapper.selectByConditionGroupNames(conditionGroupNameList);
        }
        List<String> conditionRootNameList = conditionGroups.stream().map(g -> g.getCondGrpName()).collect(Collectors.toList());
        if(conditionRootNameList.size()>0) {
            conditionGroups = condGrpMapper.selectByRootConditionGroupName(conditionRootNameList);
        }
        conditionGroups.forEach(t -> {
            String childConditionGroupNameList = t.getChildCondGrpNameList();
            if (Strings.isNotEmpty(childConditionGroupNameList)) {
                String[] childConditionGroupName = childConditionGroupNameList.split("\\|");
                for (int i = 0; i < childConditionGroupName.length; i++) {
                    conditionGroupNameList.add(childConditionGroupName[i]);
                }
            }
        });
        if(conditionGroupNameList.size()>0) {
            conditionGroups = condGrpMapper.selectByConditionGroupNames(conditionGroupNameList);
        }
        conditionGroups.forEach(t -> {
            String actnNameListStr = t.getActnNameList();
            String elseActnNameListStr = t.getElseActnNameList();
            if (!Strings.isBlank(actnNameListStr)) {
                String[] actnNameList = actnNameListStr.split("\\|");
                for (int i = 0; i < actnNameList.length; i++) {
                    actionNameList.add(actnNameList[i]);
                }
            }
            if (!Strings.isBlank(elseActnNameListStr)) {
                String[] elseActnNameList = elseActnNameListStr.split("\\|");
                for (int i = 0; i < elseActnNameList.length; i++) {
                    actionNameList.add(elseActnNameList[i]);
                }
            }
        });
        
        Map<String, TReCondGrp> conditionGroupMap = conditionGroups.stream().collect(Collectors.toMap(TReCondGrp::getCondGrpName, t -> t));


        Set<String> conditionNameAllSet = new HashSet<>();

        conditionGroups.forEach(t -> {
            String conditionDetailNameListStr = t.getCondNameList();
            Set<String> conditionNameSet = new HashSet<>();
            if (Strings.isNotEmpty(conditionDetailNameListStr)) {
                String[] condNames = conditionDetailNameListStr.split("\\|");
                for (int i = 0; i < condNames.length; i++) {
                    conditionNameSet.add(condNames[i]);
                    conditionNameAllSet.add(condNames[i]);
                }
                t.setCondNameSet(conditionNameSet);
                Set<String> childConditionGroupNameSet = new HashSet<>();
                String childConditionGroupNameList = t.getChildCondGrpNameList();
                if (Strings.isNotEmpty(childConditionGroupNameList)) {
                    String[] childConditionGroupName = childConditionGroupNameList.split("\\|");
                    for (int i = 0; i < childConditionGroupName.length; i++) {
                        childConditionGroupNameSet.add(childConditionGroupName[i]);
                    }
                    t.setChildCondGrpNameSet(childConditionGroupNameSet);
                }
            }
        });
        List<TReCond> conditions = new ArrayList<>();
        if(conditionNameAllSet.size()>0) {
             conditions = condMapper.selectByConditionGroupNames(conditionNameAllSet);
        }


        Map<String, TReCond> conditionMap = conditions.stream().collect(Collectors.toMap(TReCond::getCondName, t -> t));
        List<TReActn> actions = new ArrayList<>();
        if(conditionNameAllSet.size()>0) {
            actions = actnMapper.selectByActionDetailNames(actionNameList);
        }
            Map<String, TReActn> actionMap = actions.stream().collect(Collectors.toMap(TReActn::getActnName, t -> t));

        List<String> tagNameList = conditions.stream().map(g -> g.getTagName()).collect(Collectors.toList());
        List<String> tagNameList1 = conditions.stream().filter(g ->g.getResultTagName()!=null).map(g -> g.getResultTagName()).collect(Collectors.toList());
        List<String> tagNameList2 = actions.stream().map(g -> g.getTagName()).collect(Collectors.toList());
        tagNameList.addAll(tagNameList1);
        tagNameList.addAll(tagNameList2);
        List<TReTag> tags = new ArrayList<>();
        if(tagNameList.size()>0) {
            tags = tagMapper.selectByTagNames(tagNameList);
        }
        Map<String, TReTag> tagMap = tags.stream().collect(Collectors.toMap(TReTag::getTagName, t -> t));

        List<String> funNameList = tags.stream().filter(item -> item.getFunName() != null).map(TReTag::getFunName).collect(Collectors.toList());
        List<TReFun> funList = new ArrayList<>();
        if (funNameList.size() > 0) {
            funList = funMapper.selectByFunNames(funNameList);
        }
        Map<String, TReFun> funMap = funList.stream().collect(Collectors.toMap(TReFun::getFunName, t -> t));

        List<String> tagRangeList = conditions.stream().map(g -> g.getTagRngName()).collect(Collectors.toList());
        List<String> tagRangeList2 = actions.stream().map(g -> g.getTagRngName()).collect(Collectors.toList());
        tagRangeList.addAll(tagRangeList2);
        List<TReTagRng> tagRanges = new ArrayList<>();
        if (tagRangeList.size() > 0) {
            tagRanges = tagRngMapper.selectByTagRanges(tagRangeList);
        }
        Map<String, TReTagRng> tagRangeMap = tagRanges.stream().collect(Collectors.toMap(TReTagRng::getTagRngName, t -> t));


        Map<String, RuleBo> ruleBoMap = new HashMap();
        ruleList.forEach(r -> {
            List<ActnBo> actnBoList = new ArrayList<>();
            if (r.getActnNameList() != null) {
                String[] actionNames = r.getActnNameList().split("\\|");
                for (int i = 0; i < actionNames.length; i++) {
                    Optional.ofNullable(actionMap.get(actionNames[i])).ifPresent(t -> {
                        ActnBo actnBo = getActionDetailBo(tagMap, tagRangeMap,  t);
                        actnBoList.add(actnBo);

                    });
                }
            }

            List<ActnBo> elseActnBoList = new ArrayList<>();
            if (r.getElseActnNameList() != null) {
                String[] actionNames = r.getElseActnNameList().split("\\|");
                for (int i = 0; i < actionNames.length; i++) {
                    Optional.ofNullable(actionMap.get(actionNames[i])).ifPresent(t -> {
                        ActnBo actnBo = getActionDetailBo(tagMap, tagRangeMap,  t);
                        elseActnBoList.add(actnBo);
                    });
                }
            }
            
            RuleBo ruleBo = new RuleBo();
            CondGrpBo condGrpBo = initConditionGroupBo(conditionMap, tagMap, tagRangeMap, conditionGroupMap, actionMap, funMap, r.getCondGrpName());
            ruleBo.setCondGrpBo(condGrpBo);
            ruleBo.setActnBoList(actnBoList);
            ruleBo.setElseActnBoList(elseActnBoList);
            ruleBoMap.put(r.getRuleName(), ruleBo);
        });

        return ruleBoMap;
    }

    private ActnBo getActionDetailBo(Map<String, TReTag> tagMap, Map<String, TReTagRng> paramMap, TReActn t) {
        ActnBo actnBo = new ActnBo();
        actnBo.setTReActn(t);
        Optional.ofNullable(t.getTagName())
                .ifPresent(tagName -> {
                    actnBo.setTag(tagMap.get(tagName));
                    actnBo.setTagRng(paramMap.get(t.getTagRngName()));
                });
        return actnBo;
    }

    private CondGrpBo initConditionGroupBo(Map<String, TReCond> conditionDetailMap, Map<String, TReTag> tagMap, Map<String, TReTagRng> paramMap, Map<String, TReCondGrp> conditionGroupMap, Map<String, TReActn> actionDetailMap, Map<String, TReFun> funMap, String conditionGroupName) {
        TReCondGrp conditionGroup = conditionGroupMap.get(conditionGroupName);
        CondGrpBo condGrpBo = new CondGrpBo();
        if(conditionGroup!=null) {
            condGrpBo.setCondGrp(conditionGroup);
            conditionGroup.getCondNameSet().forEach(t -> {
                TReCond condition = conditionDetailMap.get(t);
                TReTag TReTag = tagMap.get(condition.getTagName());
                CondBo condBo = new CondBo();
                condBo.setTag(TReTag);
                if (condition.getTagRngName() != null) {
                    TReTagRng TReTagRng = paramMap.get(condition.getTagRngName());
                    condBo.setTagRng(TReTagRng);
                }
              
                condBo.setTagBo(initTagBo(condition.getTagName(),tagMap ,funMap));
                condBo.setResultTagBo(initTagBo(condition.getResultTagName(),tagMap ,funMap));

                condBo.setCond(condition);
                condGrpBo.getCondBoList().add(condBo);

            });
            if (conditionGroup.getChildCondGrpNameSet() != null) {
                conditionGroup.getChildCondGrpNameSet().forEach(t -> {
                    CondGrpBo childCondtionGroupBo = initConditionGroupBo(conditionDetailMap, tagMap, paramMap, conditionGroupMap, actionDetailMap, funMap, t);
                    condGrpBo.getCondGrpBoList().add(childCondtionGroupBo);
                });
            }

            if (!Strings.isBlank(conditionGroup.getActnNameList())) {
                String[] actnNameList = conditionGroup.getActnNameList().split("\\|");
                for (int i = 0; i < actnNameList.length; i++) {
                    Optional.ofNullable(actionDetailMap.get(actnNameList[i])).ifPresent(t -> {
                        ActnBo actnBo = getActionDetailBo(tagMap, paramMap, t);
                        condGrpBo.getActnBoList().add(actnBo);

                    });
                }
            }
            if (!Strings.isBlank(conditionGroup.getElseActnNameList())) {
                String[] elseActnNameList = conditionGroup.getElseActnNameList().split("\\|");
                for (int i = 0; i < elseActnNameList.length; i++) {
                    Optional.ofNullable(actionDetailMap.get(elseActnNameList[i])).ifPresent(t -> {
                        ActnBo actnBo = getActionDetailBo(tagMap, paramMap, t);
                        condGrpBo.getElseActnBoList().add(actnBo);

                    });
                }
            }
        }
        
        return condGrpBo;
    }

    private TagBo initTagBo(String tagName, Map<String,TReTag> tagMap,Map<String, TReFun> funMap) {
        TReTag tag = tagMap.get(tagName);
        if(tag==null){
            return null;
        }
        TagBo tagBo = new TagBo();
        tagBo.setTag(tag);
        TagType tagType = tag.getTagType();
        tagBo.setTagType(tagType);
        if(TagType.CACULATION.equals(tagType)) {
            tagBo.setFun(funMap.get(tagBo.getFun()));
        }
        return tagBo;
    }


//    private void initConditionGroup(Map<String, ConditionDetail> conditionDetailMap, Map<String, Tag> tagMap, Map<String, TagRange> paramMap, ConditionGroup conditionGroup, ConditionGroupBo conditionGroupBo) {
//        conditionGroup.getCondNameSet().forEach(t -> {
//            ConditionDetail conditionDetail = conditionDetailMap.get(t);
//            Tag tag = tagMap.get(conditionDetail.getTagName());
//            ConditionDetailBo conditionDetailBo = new ConditionDetailBo();
//            conditionDetailBo.setTag(tag);
//            if (conditionDetail.getTagRngName() != null) {
//                TagRange tagRange = paramMap.get(conditionDetail.getTagRngName());
//                conditionDetailBo.setTagRng(tagRange);
//            }
//            conditionDetailBo.setConditionDetail(conditionDetail);
//            conditionGroupBo.getConditionDetailBoList().add(conditionDetailBo);
//
//        });
//    }

}
